package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.messenger.BlockBroker;
import com.java.messenger.Messenger;
import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.OrderManager;

@Controller
public class TraderBlockBlotterViewController {

	@Autowired
	private BlockManager blockManager;

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/block-blotter")
	public String loadEmptyModelBean(HttpSession session) {
		User user = (User) session.getAttribute("authenticatedUser");
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
		user.setBlocks(filteredBlocks);
		return "block-blotter";
	}

	@RequestMapping(value = "/remove-orders", method = RequestMethod.POST)
	public String removeOrders(@RequestBody String json) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		List<String> orderIds = new ArrayList<String>();
		
		for(String id : filteredJson) {
			orderIds.add(id.substring(1, id.length() - 1));
		}
		for (String id : orderIds) {
			orderManager.removeOrderFromBlockWithOrderId(id);
		}
		return "block-blotter";
	}
	
	@RequestMapping(value = "/cancel-block", method = RequestMethod.POST)
	public String cancelBlock(@RequestBody String json) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		List<String> blockId = new ArrayList<String>();
		
		for(String id : filteredJson) {
			blockId.add(id.substring(1, id.length() - 1));
		}
		
		List<Block> blocks = new ArrayList<Block>();
		for (String id : blockId) {
			blockManager.setStatusForBlockWithBlockId(id, "cancelled");
			Block block = blockManager.getBlockWithId(id);
			blocks.add(block);
		}
		
		for (Block block : blocks) {
			List<Order> orders = block.getOrders();
			for (Order order : orders) {
				orderManager.removeOrderFromBlockWithOrderId(order.getOrderId());
			}
		}
		return "block-blotter";
	}
	
	@RequestMapping(value = "/send-block", method = RequestMethod.POST)
	public String sendBlock(@RequestBody String json) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		System.out.println("filteredJson: " + filteredJson);
		List<String> blockId = new ArrayList<String>();
		
		for(String id : filteredJson) {
			System.out.println("id.substring(1, id.length() - 1): " + id.substring(1, id.length() - 1));
			blockId.add(id.substring(1, id.length() - 1));
		}
		
		Block block = blockManager.getBlockWithId(blockId.get(0));
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

}
