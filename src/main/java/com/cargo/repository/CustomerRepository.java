package com.cargo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cargo.entity.Customer;




@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {



	Customer findByEmailId(String emailId);

	Customer findByEmailIdAndPassword(String emailId, String password);
	
	
}
