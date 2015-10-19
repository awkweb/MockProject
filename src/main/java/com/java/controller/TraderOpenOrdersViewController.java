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

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.OrderManager;

@Controller
public class TraderOpenOrdersViewController {

	@Autowired
	private BlockManager blockManager;

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/open-orders")
	public String openOrders(HttpSession session, Model model){
		User user = (User) session.getAttribute("authenticatedUser");
		List<Order> orders = orderManager.getOpenOrdersforUser(user);
		List<Block> proposedBlocks = orderManager.getProposedBlocksWithOrders(orders, user);

		session.setAttribute("proposedBlocks", proposedBlocks);
		return "open-orders";
	}

	@RequestMapping(value = "/create-block",method=RequestMethod.POST, consumes="application/json")
	public String createBlock(@RequestBody String myArray,HttpSession session) {
		List<Integer> idlist = new ArrayList<Integer>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){

			idlist.add(Integer.parseInt(id.substring(1,id.length()-1)));
		}

		List<Order> selected4Block = orderManager.findOrdersWithIds(idlist);

		Block newBlock = new Block(selected4Block.get(0).getSymbol(),
				selected4Block.get(0).getSide(), "new", selected4Block.get(0).getUser2(),selected4Block);
		System.out.println(newBlock);
		blockManager.saveBlock(newBlock);
		for(Order order : selected4Block){
			order.setBlock(newBlock);
			orderManager.updateOrder(order);
		}
		return "block-blotter";
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
	public String blockSelectedSoAdd(@RequestBody String blockID, HttpSession session) {
		
		System.out.println("Block Selected : "+ blockID);
		return "open-orders";
	}
	

}
