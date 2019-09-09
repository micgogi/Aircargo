package com.micgogi.aircargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micgogi.aircargo.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	Customer findByEmailId(String email);
	Boolean existsByEmailId(String email);
	
}
