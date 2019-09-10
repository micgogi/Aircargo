package com.cargo.service;




import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cargo.entity.Consignment;
import com.cargo.entity.Customer;
import com.cargo.entity.Item;
import com.cargo.exception.CustomerAlreadyExistsException;
import com.cargo.exception.CustomerNotFoundException;
import com.cargo.repository.ConsignmentRepository;
import com.cargo.repository.CustomerRepository;
import com.cargo.repository.ItemRepository;


@Service
public class CargoServiceImpl implements CargoService{
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ConsignmentRepository consignmentRepo;

	@Autowired
	private ItemRepository itemRepo;

	@Override
	public boolean saveCustomer(Customer customer) throws CustomerAlreadyExistsException {
		Customer existingUser = customerRepo.findByEmailId(customer.getEmailId());
		if (existingUser!=null) {
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



	@Override
	public Customer findByEmailId(String emailId) throws CustomerNotFoundException {
		Customer customer = customerRepo.findByEmailId(emailId);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer not found by email id");
		}
		
		return customer;
		
		
	}
	
	

	@Override
	public List<Consignment> listConsignment(int accountNo) {
		// TODO Auto-generated method stub
		List<Consignment> listConsignment = consignmentRepo.findListConsignment(accountNo);
		return listConsignment;
	}



	@Override
	public boolean addConsignmnet(Consignment consignment,Customer customer) {
		consignment.setCustomer(customer);
		consignmentRepo.save(consignment);
		return true;
		
	}



	@Override
	public boolean addItem(int consignmentId,Item item) {
		Consignment consignment=consignmentRepo.findById(consignmentId);
		item.setConsignment(consignment);
		itemRepo.save(item);
		return true;
	}



	@Override
	public List<Item> listItem(int consignmentId) {
		
		List<Item>item=itemRepo.findItemList(consignmentId);
		item.forEach(System.out::println);
		return item;
	}
	
	

}