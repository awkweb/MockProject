package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.java.messenger.BlockBroker;
import com.java.messenger.Messenger;
import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.OrderManager;

@Controller
@SessionAttributes(value = {"blockBlotterError", "blockBlotterSuccess", "blockBlotterMessage", "role"})
public class TraderBlockBlotterViewController {

	static int counter = 0;

	@Autowired
	private BlockManager blockManager;

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/block-blotter")
	public String loadEmptyModelBean(HttpSession session, Model model) {
		User user = (User) session.getAttribute("authenticatedUser");
		model.addAttribute("role", "trader");
		List<Block> blocks = blockManager.getBlocksForUserWithStatus(user, "new");
		List<Block> filteredBlocks = new ArrayList<Block>();
		for (Block block : blocks) {
			List<Order> orders = orderManager.getOrdersForBlock(block);
			if (!orders.isEmpty()) {
				block.setOrders(orders);
				filteredBlocks.add(block);
			} else {
				blockManager.setStatusForBlockWithBlockId(block.getBlockId(), "cancelled");
			}
		}

		manageAlertForSessionAndModelWithName(session, model, "blockBlotterError");
		manageAlertForSessionAndModelWithName(session, model, "blockBlotterSuccess");

		user.setBlocks(filteredBlocks);
		return "block-blotter";
	}

	@RequestMapping(value = "/remove-orders", method = RequestMethod.POST)
	public String removeOrders(@RequestBody String json, Model model) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		List<String> orderIds = new ArrayList<String>();

		for(String id : filteredJson) {
			orderIds.add(id.substring(1, id.length() - 1));
		}
		List<Boolean> orderRemoveResults = new ArrayList<Boolean>();
		for (String id : orderIds) {
			Boolean result = orderManager.removeOrderFromBlockWithOrderId(id);
			orderRemoveResults.add(result);
		}
				
		if (!orderRemoveResults.contains(false)) {
			model.addAttribute("blockBlotterSuccess", true);
			model.addAttribute("blockBlotterMessage", "Success! Order(s) were removed!");
		} else {
			model.addAttribute("blockBlotterError", true);
			model.addAttribute("blockBlotterMessage", "Error removing order(s).");
		}
		counter = 0;
		
		return "block-blotter";
	}

	@RequestMapping(value = "/cancel-block", method = RequestMethod.POST)
	public String cancelBlock(@RequestBody String json, Model model) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		String blockId = filteredJson[0].substring(1, filteredJson[0].length() - 1);
		
		Boolean result = blockManager.setStatusForBlockWithBlockId(blockId, "cancelled");
		if (result) {
			model.addAttribute("blockBlotterSuccess", true);
			model.addAttribute("blockBlotterMessage", "Success! Block was cancelled!");
		} else {
			model.addAttribute("blockBlotterError", true);
			model.addAttribute("blockBlotterMessage", "Error cancelling block.");
		}
		counter = 0;
		
		Block block = blockManager.getBlockWithId(blockId);

		List<Order> orders = block.getOrders();
		for (Order order : orders) {
			orderManager.removeOrderFromBlockWithOrderId(order.getOrderId());
		}
		
		return "block-blotter";
	}

	@RequestMapping(value = "/send-block", method = RequestMethod.POST)
	public String sendBlock(@RequestBody String json) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		String blockId = filteredJson[0].substring(1, filteredJson[0].length() - 1);

		Block block = blockManager.getBlockWithId(blockId);
		BlockBroker blockBroker = new BlockBroker(block.getBlockId(), block.getExecutedQty(),
				block.getLimitPrice(), block.getOpenQty(), block.getStatus(), block.getStopPrice(),
				block.getTimestamp(), block.getTotalQty());

		Messenger messenger;
		try {
			messenger = new Messenger();
			messenger.send(blockBroker);
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (JMSException e1) {
			e1.printStackTrace();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}

		blockManager.setStatusForBlockWithBlockId(block.getBlockId(), "sent for execution");
		// Create Executeblock for block

		return "block-blotter";
	}

	public static void manageAlertForSessionAndModelWithName(HttpSession session, Model model,
			String name) {
		Object sessionCheck;
		boolean flag;
		sessionCheck = session.getAttribute(name);
		if (sessionCheck != null) {
			try {
				flag = (boolean) session.getAttribute(name);
				if (flag) {
					if (counter >= 1) {
						model.addAttribute(name, false);
						counter = 0;
					} else {
						counter++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
