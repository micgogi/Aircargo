package com.micgogi.aircargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.micgogi.aircargo.entity.Customer;
import com.micgogi.aircargo.entity.Role;
import com.micgogi.aircargo.repository.CustomerRepository;
import com.micgogi.aircargo.repository.RoleRepository;

public class CustomerService implements UserDetailsService {
	
	@Autowired 
	CustomerRepository customerRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	public boolean save(Customer customer) {
		customerRepo.save(customer);
		return true;
	}
	public Customer getCustomerByEmail(String email) {
		return customerRepo.findByEmail(email);
	}
	public List<Role> getAllRole(){
		return roleRepo.findAll();
	}
	public Role getRoleById(int roleId) {
		return roleRepo.findById(roleId);
	}
	public Boolean existsByEmail(String email) {
		if(customerRepo.existsByEmail(email)){
			return true;
		}else{
			return false;
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
