package com.poseidon.pta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RestController for / endpoint
 *
 */
@Controller
public class HomeController
{
	/**
	 * Mapping for /
	 *
	 * Returns home url string
	 *
	 * @param model Model object to hold data loaded from repo
	 * @return url string
	 */
	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	/**
	 * Mapping for /admin/home
	 *
	 * Redirects to bidList/list
	 *
	 * @param model Model object to hold data loaded from repo
	 * @return url string
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}


}
