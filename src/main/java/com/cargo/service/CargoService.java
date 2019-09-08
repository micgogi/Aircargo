package com.cargo.service;

import org.springframework.stereotype.Service;

import com.cargo.entity.Customer;
import com.cargo.exception.CustomerAlreadyExistsException;
import com.cargo.exception.CustomerNotFoundException;


public interface CargoService {
	
	boolean saveCustomer(Customer Customer) throws CustomerAlreadyExistsException;

	Customer findByEmailIdAndPassword(String emailId, String password) throws CustomerNotFoundException;
}
