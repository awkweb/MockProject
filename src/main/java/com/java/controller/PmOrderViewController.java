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
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.OrderViewManager;


@Controller
public class PmOrderViewController {

	@Autowired
	private OrderViewManager orderViewManager;

	@RequestMapping(value="/order-view")
	public ModelAndView LoadDashBoard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("authenticatedUser");
		List<Order> pmorderlist=new ArrayList<Order>();
		pmorderlist= orderViewManager.getPmOrderDetails(user.getUserId(), "New");
		model.addAttribute("pmorderlist", pmorderlist);
		ModelAndView model1 = new ModelAndView("orderView");
		model1.addObject("pmorderlist", pmorderlist);

		return model1;
	}

	@RequestMapping(value = "/sendorderbutton",method=RequestMethod.POST, consumes="application/json")
	public String updateneworder(@RequestBody String myArray,HttpSession session) {
		List<String> idlist = new ArrayList<String>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){

			idlist.add(id.substring(1,id.length()-1));
		}
		System.out.println(idlist);
		orderViewManager.updateorder(idlist);

		return "orderView";
	}

	@RequestMapping(value = "/cancelorderbutton",method=RequestMethod.POST, consumes="application/json")
	public String updatecancelorder(@RequestBody String myArray,HttpSession session) {
		List<String> idlist = new ArrayList<String>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){

			idlist.add(id.substring(1,id.length()-1));
		}
		System.out.println(idlist);
		orderViewManager.updateordercancel(idlist);

		return "orderView";
	}

	@RequestMapping(value = "/cancelorderbutton2",method=RequestMethod.POST, consumes="application/json")
	public String updatecancelorder2(@RequestBody String myArray,HttpSession session) {
		List<String> idlist = new ArrayList<String>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){

			idlist.add(id.substring(1,id.length()-1));
		}
		System.out.println(idlist);
		orderViewManager.updateordercancel(idlist);

		return "pmopenorder";
	}

	/*	@RequestMapping(value = "/editorderbutton",method=RequestMethod.POST, consumes="application/json")
	public ModelAndView editorder(@RequestBody String myArray,HttpSession session) {
		List<String> idlist = new ArrayList<String>();
		System.out.println("array="+myArray);
		for(String id : myArray.substring(1,myArray.length()-1).split(",")){

			idlist.add(id.substring(1,id.length()-1));
		}
		ModelAndView model1 = new ModelAndView("PMEditOrder_form");
		model1.addObject("idlist", idlist);

		return model1;

	}*/

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


	@RequestMapping(value="/PmOpenOrderview")
	public ModelAndView LoadOpenDashBoard(Model model) {
		List<Order> pmorderlist=new ArrayList<Order>();
		pmorderlist= orderViewManager.getPmOrderDetails("1", "Open");
		model.addAttribute("pmorderlist", pmorderlist);
		ModelAndView model1 = new ModelAndView("pmopenorder");
		model1.addObject("pmorderlist", pmorderlist);
		return model1;
	}

	@RequestMapping(value="/PmCancelledOrderview")
	public ModelAndView LoadCancelledDashBoard(Model model) {
		List<Order> pmorderlist=new ArrayList<Order>();
		pmorderlist= orderViewManager.getPmOrderDetails("1", "Cancelled");
		model.addAttribute("pmorderlist", pmorderlist);
		ModelAndView model1 = new ModelAndView("pmcancelledorder");
		model1.addObject("pmorderlist", pmorderlist);
		return model1;
	}

}
