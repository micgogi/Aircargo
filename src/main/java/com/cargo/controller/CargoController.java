package com.cargo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cargo.entity.Customer;
import com.cargo.service.CargoService;
import com.cargo.service.SecurityTokenGenerator;


import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/")
@CrossOrigin()
public class CargoController {

	

	@Autowired
	private SecurityTokenGenerator tokenGenerator;
	
	@Autowired
	private CargoService cargoService;
	
	public String getToken(final HttpServletRequest request) {
		  final String authHeader = request.getHeader("authorization"); 
		  final String token = authHeader.substring(7); 
		  String email =Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		  return email;
		
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Customer customer) {
		try {
			cargoService.saveCustomer(customer);
			
			
			return new ResponseEntity<String>("Customer registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Customer loginDetail) {

		try {

			if (null == loginDetail.getEmailId()|| null == loginDetail.getPassword()) {
				throw new Exception("Email Id or Password canot be empty.");
			}
			Customer customer = cargoService.findByEmailIdAndPassword(loginDetail.getEmailId(), loginDetail.getPassword());
			Map<String, String> map = tokenGenerator.generateToken(customer);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	
	}
