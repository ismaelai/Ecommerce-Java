package com.addeco.demo.repository;






import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addeco.demo.entity.Product;




@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
List<Product> findAllByCustomersId(Long id);
	
List<Product> findAllByManufacturerId(Long id);

List<Product> findAllByManufacturerIdOrManufacturerIdIsNull(Long id);

List<Product> findAllByManufacturerIdIsNull();

	
}
