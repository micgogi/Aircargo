package com.micgogi.aircargo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.micgogi.aircargo.entity.Customer;
import com.micgogi.aircargo.entity.Role;
import com.micgogi.aircargo.repository.CustomerRepository;
import com.micgogi.aircargo.repository.RoleRepository;

@Service
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
		return customerRepo.findByEmailId(email);
	}
	public List<Role> getAllRole(){
		return roleRepo.findAll();
	}
	public Role getRoleById(int roleId) {
		return roleRepo.findById(roleId);
	}
	public Boolean existsByEmail(String email) {
		if(customerRepo.existsByEmailId(email)){
			return true;
		}else{
			return false;
		}

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = customerRepo.findByEmailId(email);
		
		if(customer==null) {
			throw new RuntimeException("No User Found");
		}
		System.out.println(customer.getEmailId()+" "+customer.getMobileNo());
			
		
		
		Role role = roleRepo.findById(customer.getRole());
		List<GrantedAuthority> authorties = new ArrayList<GrantedAuthority>();
		authorties.add(new SimpleGrantedAuthority(role.getRoleName()));
		User user1 = new User(customer.getEmailId(),customer.getPassword(),authorties);
		return user1;

		
	}

}
