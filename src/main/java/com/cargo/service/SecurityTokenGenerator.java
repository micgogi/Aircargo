package com.cargo.service;

import java.util.Map;

import com.cargo.entity.Customer;






public interface SecurityTokenGenerator {

	Map<String, String> generateToken(Customer customer);
}
