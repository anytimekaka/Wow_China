package com.link.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.link.model.CommonResponse;

@Controller
public class DemoController {

	@Value("${uploadFile.dir}")
	private String uploadFilDir;

	@RequestMapping(value = "/demoftl.action", method = RequestMethod.GET)
	public String demoftl(Model model) {
		model.addAttribute("hello", "hello world");
		return "demo";
	}

	@RequestMapping(value = "/demo.action", method = RequestMethod.GET)
	public @ResponseBody  CommonResponse demo(HttpServletRequest request,
			Model model) {
		CommonResponse resp = new CommonResponse();
		System.out.println(this.uploadFilDir);
		return resp;
	}

}
