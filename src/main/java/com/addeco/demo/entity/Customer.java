package com.addeco.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.Table;





@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	private String name;
	private String address;
	private String email;
	private String password;
	private String phoneNumber;
	private String username;

	@ManyToMany
	@JoinTable(name = "customer_product",
	joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private List<Product> products = new ArrayList<>();

	@OneToMany(mappedBy="customer")
	private List<ShopCart> shopcarts = new ArrayList<>();

	
	
	public Customer() {
	}

	
	
	public Customer( String name, String address, String email, String password, String phoneNumber,
			String username, List<Product> products) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.products = products;
	}



	public Customer(String name, String address, String email, String password, String phoneNumber, String username) {

		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.username = username;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ShopCart> getShopcarts() {
		return shopcarts;
	}

	public void setShopcarts(List<ShopCart> shopcarts) {
		this.shopcarts = shopcarts;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", password="
				+ password + ", phoneNumber=" + phoneNumber + ", username=" + username + ", products=" + products
				+ ", shopcarts=" + shopcarts + "]";
	}
	
	

	
	



}
