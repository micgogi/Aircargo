package com.brajesh.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brajesh.cargo.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	
}
