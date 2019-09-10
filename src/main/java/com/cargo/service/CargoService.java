package com.cargo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cargo.entity.Consignment;
import com.cargo.entity.Customer;
import com.cargo.exception.CustomerAlreadyExistsException;
import com.cargo.exception.CustomerNotFoundException;


public interface CargoService {
	
	boolean saveCustomer(Customer Customer) throws CustomerAlreadyExistsException;

	Customer findByEmailIdAndPassword(String emailId, String password) throws CustomerNotFoundException;
	
	Customer findByEmailId(String emailId) throws CustomerNotFoundException;

	List<Consignment> listConsignment(int accountNo);
	
	boolean addConsignmnet(Consignment consignment,Customer customer);
}
