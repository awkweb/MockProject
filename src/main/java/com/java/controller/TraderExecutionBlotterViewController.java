package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TraderExecutionBlotterViewController {

	@RequestMapping(value = "/execution-blotter")
	public String executionBlotter() {
		return "execution-blotter";
	}

}
