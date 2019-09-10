package com.cargo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cargo.entity.Customer;




@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {



	Customer findByEmailId(String emailId);

//	@Query("Select user from User user where user.userId = (?1) and user.password = (?2)")
//	User validate(String userId, String password);
//
	Customer findByEmailIdAndPassword(String emailId, String password);
	
	
}
