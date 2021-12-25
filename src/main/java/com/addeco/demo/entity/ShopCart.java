package com.addeco.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;





@Entity
@Table(name = "shopcart")
public class ShopCart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
    
	

	@ManyToMany
	@JoinTable(name = "shopcart_product",
	joinColumns = @JoinColumn(name = "shopcart_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private List<Product> products = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	
	public String date() {
		
		 LocalDateTime myDateObj = LocalDateTime.now();  
		    System.out.println("Before Formatting: " + myDateObj);  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");  
		    
		    String formattedDate = myDateObj.format(myFormatObj);  
		    System.out.println("After Formatting: " + formattedDate);  
		    return formattedDate;
	}
	public List<Product> getProducts() {
		return products;
	}



	public void setProducts(List<Product> products) {
		this.products = products;
	}



	public ShopCart() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}







	public Customer getCustomers() {
		return customer;
	}



	public void setCustomers(Customer customer) {
		this.customer = customer;
	}





	
	
	

	
	  @Override public String toString() { return "ShopCart [id=" + id +
	  ", products=" + products + ", customer=" + customer + "]"; }
	 
	
	

	
	
	

    






}
