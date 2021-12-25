package com.addeco.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addeco.demo.entity.Customer;
import com.addeco.demo.entity.Manufacturer;
import com.addeco.demo.entity.Product;
import com.addeco.demo.entity.ShopCart;
import com.addeco.demo.repository.CustomerRepository;
import com.addeco.demo.repository.ManuFacturerRepository;
import com.addeco.demo.repository.ProductRepository;
import com.addeco.demo.repository.ShopCartRepository;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customrepository;

	@Autowired
	ManuFacturerRepository manurepository;

	@Autowired
	ProductRepository productrepositoy;

	@Autowired
	ShopCartRepository shopcartrepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Manufacturer Amazon=new Manufacturer("84277777","apple","cl madrid plaza",1000);
		Manufacturer Ebay=new Manufacturer("842733304","Samsung","cl madrid plaza tres",3000);
		manurepository.save(Amazon);
		manurepository.save(Ebay);
		Product product1 = new Product("Iphone 12", "new versions with 8 ram camera 64px", 3, 500.05);
		
		Product product2 = new Product("Iphone 11", "new versions with 6 ram camera 256px", 2, 400.0,Amazon);
		Product product3 = new Product("Iphone 10", "new versions with 8 ram camera 148px", 2, 450.0,Ebay);
		Product product4 = new Product("Iphone 9", "new versions with 4 ram camera 48px", 2, 300.0,Ebay);

		List<Product> products = Arrays.asList(product1, product2,product3,product4);
		
		productrepositoy.saveAll(products);
		Customer customer1 = new Customer("ayman", "cl rio escudo", "a@a", "1234", "633258741", "aymansalem",products);
		customrepository.save(customer1);

		
        
		

	}
	


}
