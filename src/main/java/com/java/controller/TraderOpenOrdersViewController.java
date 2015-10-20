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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.OrderManager;

@Controller
@SessionAttributes(value = { "openOrdersError", "openOrdersSuccess", "openOrdersMessage" })
public class TraderOpenOrdersViewController {
	
	static int counter = 0;

	@Autowired
	private BlockManager blockManager;

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/open-orders")
	public String openOrders(HttpSession session, Model model){
		User user = (User) session.getAttribute("authenticatedUser");
		List<Order> orders = orderManager.getOpenOrdersforUser(user);
		List<Block> proposedBlocks = orderManager.getProposedBlocksWithOrders(orders, user);
		
		manageAlertForSessionAndModelWithName(session, model,
				"openOrdersError");
		manageAlertForSessionAndModelWithName(session, model,
				"openOrdersSuccess");
		
		session.setAttribute("proposedBlocks", proposedBlocks);
		return "open-orders";
	}

	@RequestMapping(value = "/create-block", method=RequestMethod.POST, consumes="application/json")
	public String createBlock(@RequestBody String myArray, HttpSession session,
			Model model) {

		List<Integer> idlist = new ArrayList<Integer>();
		for (String id : myArray.substring(1, myArray.length() - 1).split(",")) {

			idlist.add(Integer.parseInt(id.substring(1, id.length() - 1)));
		}

		List<Order> selected4Block = orderManager.findOrdersWithIds(idlist);

		boolean canCreateBlock = orderManager.canAddToBlock(selected4Block);
		if (canCreateBlock == true) {
			Block newBlock = new Block(selected4Block.get(0).getSymbol(),
					selected4Block.get(0).getSide(), "new", selected4Block.get(
							0).getUser2(), selected4Block);
			blockManager.saveBlock(newBlock);
			for (Order order : selected4Block) {
				order.setBlock(newBlock);
				orderManager.updateOrder(order);
			}
			model.addAttribute("openOrdersSuccess", true);
			model.addAttribute("openOrdersMessage", "Success! New block created.");
		} else {
			model.addAttribute("openOrdersError", true);
			model.addAttribute("openOrdersMessage", "Error. Cannot create block with orders with different sides and/or symbols.");
		}
		counter = 0;

		return "open-orders";
	}

	@RequestMapping(value = "/add-block",method=RequestMethod.POST, consumes="application/json")
	public String addToBlock(@RequestBody String myArray,HttpSession session) {
		List<Integer> idlist = new ArrayList<Integer>();
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){

			idlist.add(Integer.parseInt(id.substring(1,id.length()-1)));
		}
		System.out.println(idlist);
		Order order = orderManager.getOrderWithId(""+idlist.get(0));
		List<Block> blocklist = blockManager.getBlocksForOrder(order);

		System.out.println(blocklist.get(0).getOrders());


		System.out.println(blocklist);
		session.setAttribute("selectedorderlist", idlist);
		session.setAttribute("addlist", blocklist);

		return "select-block";
	}

	@RequestMapping(value ="/select-block")
	public String popupBlocks(HttpSession session) {
		System.out.println("Switching to select Blocks");
		return "select-block";
	}

	//This actually adds the orders to an existing block that is selected
	@RequestMapping(value ="/block-selected")
	public String blockSelectedSoAdd(@RequestBody String blockID, HttpSession session, Model model) {
		String selectedBlockID = blockID.substring(1, blockID.length()-1);

		Block selectedBlock = blockManager.getBlockWithId(selectedBlockID);
		List<Integer> orderids2Add = (ArrayList<Integer>) session.getAttribute("selectedorderlist");
		for(Integer orderid : orderids2Add){
			Order order = orderManager.getOrderWithId(""+orderid);
			selectedBlock.addOrder(order);
			orderManager.updateOrder(order);
		}
		blockManager.updateBlock(selectedBlock);
		Boolean result = blockManager.setQtyForBlockWithBlockId(selectedBlockID, selectedBlock.calculateTotalQty());
		
		if (result) {
			model.addAttribute("openOrdersSuccess", true);
			model.addAttribute("openOrdersMessage", "Success! Order(s) added to block.");
		} else {
			model.addAttribute("openOrdersError", true);
			model.addAttribute("openOrdersMessage", "Error adding order(s) to block.");
		}
		counter = 0;

		return "open-orders";
	}

	public static void manageAlertForSessionAndModelWithName(
			HttpSession session, Model model, String name) {
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
