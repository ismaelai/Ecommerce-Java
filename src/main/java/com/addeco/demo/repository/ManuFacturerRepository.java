package com.addeco.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.addeco.demo.entity.Manufacturer;

@Repository
public interface ManuFacturerRepository extends JpaRepository<Manufacturer, Long> {

}
