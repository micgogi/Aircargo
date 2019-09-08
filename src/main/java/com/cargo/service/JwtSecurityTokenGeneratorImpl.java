
package com.cargo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cargo.entity.Customer;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generateToken(Customer customer) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(customer.getEmailId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS384, "secretkey").compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "Customer successfully logged in");
		return map;
	}


	
}
