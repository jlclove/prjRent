package com.dyrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dyrent.model.HouseMessage;
import com.dyrent.service.HouseMessageService;

@Controller
public class HomeController {
	@Autowired
	private HouseMessageService houseMessageService;

	@RequestMapping("/")
	protected String index(Model model,
			@ModelAttribute HouseMessage houseMessage) {
		model.addAttribute("houseMessage", houseMessage);
		model.addAttribute("messageList",
				houseMessageService.getListPage(houseMessage));
		model.addAttribute("totalCount",
				houseMessageService.getListCount(houseMessage));
		return "/house/more";
	}
}
