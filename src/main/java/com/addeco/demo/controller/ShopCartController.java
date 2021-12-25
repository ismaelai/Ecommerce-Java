package com.addeco.demo.controller;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.addeco.demo.entity.Customer;
import com.addeco.demo.entity.Product;
import com.addeco.demo.entity.ShopCart;
import com.addeco.demo.repository.CustomerRepository;
import com.addeco.demo.repository.ProductRepository;
import com.addeco.demo.repository.ShopCartRepository;







@Controller
public class ShopCartController {


	
	@Autowired
CustomerRepository customerrepo;
	
	@Autowired
ShopCartRepository shopcartrepo;
	
	@Autowired
ProductRepository	productrepo;
	
	
	
		@GetMapping("/shopcarts/{id}/addproduct")
		public String addToShopCart(@PathVariable Long id, Model model, HttpSession session) {
			
			ShopCart shopcart = (ShopCart) session.getAttribute("shopcart");
			if (shopcart == null) {
				
				shopcart = new ShopCart();
			}
			
			
			Optional<Product> productOpt = productrepo.findById(id);
			if (productOpt.isPresent()) { // If product exists then add it to shopcart
				Product product = productOpt.get();
				
				
				if (!shopcart.getProducts().contains(product)) {
					shopcart.getProducts().add(product);
				}
			}
			
			session.setAttribute("shopcart", shopcart);
			
			model.addAttribute("shopcart_items", shopcart.getProducts().size());
			model.addAttribute("products", productrepo.findAll());

			return "redirect:/shopcart";
	}
	
	
		
      @GetMapping("/shopcarts/{id}/deleteproduct")
      public String deleteToShopCart(@PathVariable long id,Model model,HttpSession session) {
    	  ShopCart shopcart=(ShopCart)session.getAttribute("shopcart");
    	  if(shopcart==null)
    		  return "redirect:/products";
    	  
    	  Optional<Product> productopt=productrepo.findById(id);
    	  if(productopt.isPresent()) {
    		  Product product=productopt.get();
         if(shopcart.getProducts().contains(product)) {
        	 shopcart.getProducts().remove(product);
        	 
         }
    		  
    	  }
    	  
    	  session.setAttribute("shopcart",shopcart);
    	  model.addAttribute("totalPrice",calculateTotalPrice(shopcart));
    	  model.addAttribute("shopcart_items",shopcart.getProducts().size());
          model.addAttribute("shopcart", shopcart);
    	  model.addAttribute("products",productrepo.findAll());
    	  return "shopcart";
    	  
      }
      
		
      @GetMapping("/shopcart")
      public String seeShopcart(Model model,HttpSession session) {
    	  ShopCart shopcart=(ShopCart)session.getAttribute("shopcart");
    	  if(shopcart==null) {
    		  
    		  shopcart=new ShopCart();
    	  }
    	  model.addAttribute("totalPrice",calculateTotalPrice(shopcart));
    	  model.addAttribute("shopcart_items",shopcart.getProducts().size());
    	  model.addAttribute("shopcart",shopcart);
    	  return "shopcart";
    	  
      }
      
      private double calculateTotalPrice(ShopCart shopcart) {
    	  if(shopcart==null||shopcart.getProducts()==null) {
    		  return 0;
    	  }
    	  double totalPrice=0;
    	  for(Product product:shopcart.getProducts()) {
    		  if(product.getPrice()!=null)
    			  totalPrice+=product.pricequantity();
    		  
    	  }
    	  return totalPrice;
    	  
      }
      
      @GetMapping("/shopcarts/checkout")
      public String checkout(Model model,HttpSession session) {
    	  ShopCart shopcart=(ShopCart)session.getAttribute("shopcart");
    	  Customer customer=(Customer)session.getAttribute("customer");
    	  if(shopcart==null||customer==null)
    		  return "redirect:/products";
    	  
    	  Optional<Customer> customerDBOpt=customerrepo.findById(customer.getId());
    	  if(!customerDBOpt.isPresent())
    		  return "redirect:/products";
    	  
    	  shopcart.setCustomers(customerDBOpt.get());
    	  
    	  shopcartrepo.save(shopcart);
    	  session.removeAttribute("shopcart");
    	  return "redirect:/welcome";
    	
      }
      
      @GetMapping("/welcome")
      public String checkout(Model model) {
    		
    	  return "shopcart-checkout";
    	
      }
      
      
      @GetMapping("/shopcarts/list")
      public String showallshopcart(Model model,HttpSession session) {
    	  Customer customer=(Customer)session.getAttribute("customer");
    	  model.addAttribute("customer",customer);
    	  model.addAttribute("usercart",shopcartrepo.findAllByCustomerId(customer.getId()));
    	  
    	  return "redirect:/orders";
    	  
      }
		
      @GetMapping("/orders")
      public String showorders(Model model,HttpSession session) {
    	  Customer customer=(Customer)session.getAttribute("customer");
    	  model.addAttribute("customer",customer);
    	  model.addAttribute("usercart",shopcartrepo.findAllByCustomerId(customer.getId()));
    	  return "shopcart-list";
    	  
      }
      
      
      
		
}