package com.addeco.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;






@Entity
@Table(name="manufacturer")
public class Manufacturer implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "cif")
	private String cif;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "direction")
	private String direction;
	
	@Column(name = "num_empleados")
	private Integer numEmployees;
	
	
	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
	private List<Product> products= new ArrayList<Product>();



	
	public Manufacturer() {
		
		super();
	}


	public Manufacturer(String cif, String name, String direction, Integer numEmployees) {
	
		this.cif = cif;
		this.name = name;
		this.direction = direction;
		this.numEmployees = numEmployees;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public Integer getNumEmployees() {
		return numEmployees;
	}


	public void setNumEmployees(Integer numEmployees) {
		this.numEmployees = numEmployees;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", cif=" + cif + ", name=" + name + ", direction=" + direction
				+ ", numEmployees=" + numEmployees + "]";
	}


	


	

	
	
}
