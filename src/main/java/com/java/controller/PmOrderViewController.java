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
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.OrderManager;
import com.java.service.OrderViewManager;

@Controller
@SessionAttributes(value = {"orderViewError", "orderViewSuccess", "orderViewMessage"})
public class PmOrderViewController {
	
	static int counter = 0;
	
	@Autowired
	private OrderManager orderManager;
	
	@Autowired
	private OrderViewManager orderViewManager;

	@RequestMapping(value="/order-view")
	public String LoadDashBoard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("authenticatedUser");
		List<Order> newOrders = orderViewManager.getPmOrderDetails(user.getUserId(), "New");
		List<Order> openOrders = orderViewManager.getPmOrderDetails(user.getUserId(), "Open");
		List<Order> cancelledOrders = orderViewManager.getPmOrderDetails(user.getUserId(), "Cancelled");
		
		// Ideally just get the orders and switch in order-view, i.e. user.getOrders()
		model.addAttribute("newOrders", newOrders);
		model.addAttribute("openOrders", openOrders);
		model.addAttribute("cancelledOrders", cancelledOrders);
		
		manageAlertForSessionAndModelWithName(session, model, "orderViewError");
		manageAlertForSessionAndModelWithName(session, model, "orderViewSuccess");
		
		return "order-view";
	}

	@RequestMapping(value = "/cancel-order",method=RequestMethod.POST, consumes="application/json")
	public String updatecancelorder(@RequestBody String myArray, HttpSession session, Model model) {
		List<String> idlist = new ArrayList<String>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){
			idlist.add(id.substring(1,id.length()-1));
		}
		
		List<Boolean> results = new ArrayList<Boolean>();
		for (String id : idlist) {
			orderViewManager.updateOrderToStatus(id, "Cancelled");
		}
		
		for (String id : idlist) {
			orderManager.removeOrderFromBlockWithOrderId(id);
		}
		
		if (!results.contains(false)) {
			model.addAttribute("orderViewSuccess", true);
			model.addAttribute("orderViewMessage", "Success! Order(s) were cancelled!");
		} else {
			model.addAttribute("orderViewError", true);
			model.addAttribute("orderViewMessage", "Error cancelling order(s).");
		}
		counter = 0;
		
		return "order-view";
	}
	
	@RequestMapping(value = "/sendorderbutton", method=RequestMethod.POST, consumes="application/json")
	public String updateneworder(@RequestBody String myArray, HttpSession session, Model model) {
		List<String> idlist = new ArrayList<String>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){
			idlist.add(id.substring(1,id.length()-1));
		}
		
		List<Boolean> results = new ArrayList<Boolean>();
		for (String id : idlist) {
			orderViewManager.updateOrderToStatus(id, "Open");
		}
		
		for (String id : idlist) {
			orderManager.removeOrderFromBlockWithOrderId(id);
		}
		
		if (!results.contains(false)) {
			model.addAttribute("orderViewSuccess", true);
			model.addAttribute("orderViewMessage", "Success! Order(s) were sent to trader!");
		} else {
			model.addAttribute("orderViewError", true);
			model.addAttribute("orderViewMessage", "Error sending order(s).");
		}
		counter = 0;

		return "order-view";
	}

	@RequestMapping(value = "/editorderbutton2",method=RequestMethod.POST, consumes="application/json")
	public ModelAndView editorder2(@RequestBody String myArray,HttpSession session) {
		List<String> idlist = new ArrayList<String>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){

			idlist.add(id.substring(1,id.length()-1));
		}
		ModelAndView model1 = new ModelAndView("PMAmendOrder_form");
		model1.addObject("idlist", idlist);

		return model1;
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
