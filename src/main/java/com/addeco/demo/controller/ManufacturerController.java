package com.addeco.demo.controller;

import java.util.List;
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
import com.addeco.demo.entity.Manufacturer;
import com.addeco.demo.repository.ManuFacturerRepository;
import com.addeco.demo.repository.ProductRepository;
import com.addeco.demo.entity.Product;

@Controller
public class ManufacturerController {

	@Autowired
	ManuFacturerRepository repository;
	@Autowired
	ProductRepository productrepo;
	
	@GetMapping("/manufacturers")
	public String listManufacturers(Model model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if(customer != null)
			model.addAttribute("customer", customer);
		model.addAttribute("manufacturers",repository.findAll());
		return "store-list";
		
	}
	
	@GetMapping("/manufacturers/{id}/view")
	public String viewManufacturer(@PathVariable Long id, Model model) {
		if (id == null) {
			return "redirect:/manufacturers";
		}
		Optional<Manufacturer> manOpt = repository.findById(id);
		if (manOpt.isPresent()) {
			model.addAttribute("manufacturer", manOpt.get());
			return "store-view";
		}
		return "redirect:/manufacturers";
	}
	
	@GetMapping("/manufacturers/new")
	public String showForm(Model model) {
		model.addAttribute("manufacturer", new Manufacturer());
		model.addAttribute("products", productrepo.findAllByManufacturerIdIsNull());

		return "store-edit";
	}
	
	@PostMapping("/manufacturers")
	public String saveProduct(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
	
		
		
		for (Product product : manufacturer.getProducts()) 
			product.setManufacturer(manufacturer);
		
		repository.save(manufacturer);
		return "redirect:/manufacturers";
	}
	
	@GetMapping("/manufacturers/{id}/edit")
	public String loadForm(@PathVariable Long id, Model model) {
		if(id == null)
			return "redirect:/manufacturers";
		
		Optional<Manufacturer> manOpt = repository.findById(id);
		if (manOpt.isPresent()) {
			model.addAttribute("manufacturer", manOpt.get());
			model.addAttribute("products", productrepo.findAllByManufacturerIdOrManufacturerIdIsNull(id));
			return "store-edit";
		}
		model.addAttribute("NOTIFICATION", "No existe el fabricante solicitado.");
		model.addAttribute("manufacturers", repository.findAll());
		return "store-list";
	}
	
	
	
	@GetMapping("/manufacturers/{id}/delete")
	public String delete(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/manufacturers";
	}
	
	@GetMapping("/manufacturers/delete/all")
	public String deleteAll() {
		repository.deleteAll();
		return "redirect:/manufacturers";
	}
	
}