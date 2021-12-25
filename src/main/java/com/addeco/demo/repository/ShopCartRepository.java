package com.addeco.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addeco.demo.entity.ShopCart;

public interface ShopCartRepository extends JpaRepository<ShopCart, Long> {

	List<ShopCart> findAllByCustomerId(Long id);

}
