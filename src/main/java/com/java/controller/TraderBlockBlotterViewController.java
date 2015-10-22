package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.java.pojo.Block;
import com.java.pojo.Executeblock;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.ExecuteBlockManager;
import com.java.service.OrderManager;

@Controller
@SessionAttributes(value = {"blockBlotterError", "blockBlotterSuccess", "blockBlotterMessage", "role"})
public class TraderBlockBlotterViewController {

	static int counter = 0;

	@Autowired
	private BlockManager blockManager;

	@Autowired
	private OrderManager orderManager;
	
	@Autowired
	private ExecuteBlockManager executeBlockManager;

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
		System.out.println(orderIds);
		String blockid = orderManager.getOrderWithId(orderIds.get(0)).getBlock().getBlockId();
		
		List<Boolean> orderRemoveResults = new ArrayList<Boolean>();
		for (String id : orderIds) {
			Boolean result = orderManager.removeOrderFromBlockWithOrderId(id);
			orderRemoveResults.add(result);
		}
		
		Block block =blockManager.getBlockWithId(blockid);
		for(Order order : block.getOrders()){
			System.out.println("OrderID="+order.getOrderId());
		}

		blockManager.setQtyForBlockWithBlockId(blockid,block.calculateTotalQty());
		blockManager.setQtyForBlockWithBlockId(blockid, block.calculateTotalQty());
		
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

	@RequestMapping(value= "/edit-block", method = RequestMethod.POST)
	public String editBlock(@RequestParam("id") String id, @RequestParam("type") String type,
			@RequestParam("price") float price, Model model) {
		Boolean result = orderManager.updateOrderPriceForIdAndType(id, type, price);
		if (result) {
			model.addAttribute("blockBlotterSuccess", true);
			model.addAttribute("blockBlotterMessage", "Success! " + type + " price updated.");
		} else {
			model.addAttribute("blockBlotterError", true);
			model.addAttribute("blockBlotterMessage", "Error updating order.");
		}
		counter = 0;
		return "redirect:/block-blotter";
	}

	@RequestMapping(value = "/send-block", method = RequestMethod.POST)
	public String sendBlock(@RequestBody String json, Model model) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		String blockId = filteredJson[0].substring(1, filteredJson[0].length() - 1);

		Block block = blockManager.getBlockWithId(blockId);

		Boolean result = blockManager.setStatusForBlockWithBlockId(block.getBlockId(), "sent for execution");
		Executeblock executeblock = new Executeblock(0, block.getExecutedQty(), block.getOpenQty(), block.getSide(),
				"Sent for execution", 0.0f, 0.0f, null, block, block.getOrders().get(0), block.getOrders().get(0).getSecurity());
		executeBlockManager.saveExecuteblock(executeblock);
		
		if (result) {
			model.addAttribute("blockBlotterSuccess", true);
			model.addAttribute("blockBlotterMessage", "Success! Block sent for execution.");
		} else {
			model.addAttribute("blockBlotterError", true);
			model.addAttribute("blockBlotterMessage", "Error sending block.");
		}
		counter = 0;

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
