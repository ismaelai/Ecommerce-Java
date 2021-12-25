package com.addeco.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.addeco.demo.entity.Customer;

import com.addeco.demo.repository.CustomerRepository;




@Controller
public class CustomerController {
	
	
	@Autowired
	private CustomerRepository customerrepo;

  @GetMapping("/customers")
  public String findCustomers(Model model, HttpSession session){
	  Customer customer = (Customer) session.getAttribute("customer");
		if(customer != null)
			model.addAttribute("customer", customer);
    model.addAttribute("customers", customerrepo.findAll());
    return "customer-list";
  }
  
  
  

	@GetMapping("/customers/{id}/view")
	public String viewCustomer(@PathVariable Long id, Model model) {
		Optional<Customer> customerOpt = customerrepo.findById(id);
		
		  if (!customerOpt.isPresent()) { model.addAttribute("error",
		  "ID product not found."); model.addAttribute("customers",
		  customerrepo.findAll()); return "customer-list"; }
		 
		model.addAttribute("customer", customerOpt.get());
		return "customer-viewtest";
	}
	
	@GetMapping("/customers/new")
	public String newCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-edit";
	}
	
	@PostMapping("/customers")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerrepo.save(customer);
		return "redirect:/customers";
	}
	
	
	@GetMapping("/customers/{id}/edit")
	public String editCustomer(@PathVariable Long id, Model model) {
		if(id == null) // 1. se comprueba que el id no sea nulo
			return "redirect:/customers";
		
		Optional<Customer> customerOpt = customerrepo.findById(id);
		if (customerOpt.isPresent()) { 
			model.addAttribute("customer", customerOpt.get());
	
			
			return "customer-edit";
		}
		model.addAttribute("error", "No existe el customer solicitado");
		return "redirect:/customers";
		
	}
	
	
	
	@GetMapping("/customers/{id}/delete")
	public String deleteCustomer(@PathVariable Long id) {
		customerrepo.deleteById(id);
		return "redirect:/customers";
	}
	
	@GetMapping("/customers/delete")
	public String deleteCustomers() {
		customerrepo.deleteAll();
		return "redirect:/customers";
	}
	
}