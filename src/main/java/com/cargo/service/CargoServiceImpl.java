package com.cargo.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cargo.entity.Customer;
import com.cargo.exception.CustomerAlreadyExistsException;
import com.cargo.exception.CustomerNotFoundException;
import com.cargo.repository.CustomerRepository;


@Service
public class CargoServiceImpl implements CargoService{
	
	@Autowired
	private CustomerRepository customerRepo;



	@Override
	public boolean saveCustomer(Customer customer) throws CustomerAlreadyExistsException {
		Optional<Customer> existingUser = customerRepo.findByEmailId(customer.getEmailId());
		if (existingUser.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer with email already exists");
		}
		customerRepo.save(customer);
		return true;
	}



	@Override
	public Customer findByEmailIdAndPassword(String emailId, String password) throws CustomerNotFoundException {
		Customer customer = customerRepo.findByEmailIdAndPassword(emailId, password);
		if (null == customer) {
			throw new CustomerNotFoundException("EmailId and Password mismatch");
		}
		return customer;
	}

}
