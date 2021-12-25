package com.addeco.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import com.addeco.demo.repository.CustomerRepository;
import com.addeco.demo.repository.ProductRepository;
import com.addeco.demo.repository.ShopCartRepository;

@Controller
public class LogoutController {
	
	@Autowired
CustomerRepository customerrepo;
	
	@Autowired
ShopCartRepository shopcartrepo;
	
	@Autowired
ProductRepository	productrepo;
	
	

		 
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/login";
		}
	}
