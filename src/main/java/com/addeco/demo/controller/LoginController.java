package com.addeco.demo.controller;




import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.addeco.demo.entity.Customer;
import com.addeco.demo.repository.CustomerRepository;






@Controller
public class LoginController {
	
	@Autowired
CustomerRepository customerrepo;
	
	

	
	@GetMapping({"/login","/"})
	public String login(Model model) {
		model.addAttribute("customer", new Customer());	
		
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("customer") Customer userForm, HttpSession session,Model model) {
		System.out.println(userForm);
		Optional<Customer> customerDB = customerrepo.findByEmailAndPassword(userForm.getEmail(), userForm.getPassword());
		if (customerDB.isPresent()) {
			session.setAttribute("customer", customerDB.get());
		
			return "redirect:/products";
		}else {
			
			model.addAttribute("errors", "email doesnt found!! .");
			
			
			System.out.println("isnt found");
			return "redirect:/login";
		}
		
	}
	
	
	
}
