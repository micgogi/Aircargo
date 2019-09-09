package com.micgogi.aircargo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micgogi.aircargo.entity.Customer;
import com.micgogi.aircargo.entity.JwtResponse;
import com.micgogi.aircargo.entity.ResponseMessage;
import com.micgogi.aircargo.security.JwtProvider;
import com.micgogi.aircargo.service.CustomerService;

@RestController
@RequestMapping("api/auth")
public class MainController {

	@Autowired
	CustomerService customerService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signup")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
		if (customerService.existsByEmail(customer.getEmailId())) {
			return new ResponseEntity<>(new ResponseMessage("Fail->EMail is already registered"),
					HttpStatus.BAD_REQUEST);
		}
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customerService.save(customer);
		return new ResponseEntity<>(new ResponseMessage("Message: Yahoo! Yours Details are saved successFully"),
				HttpStatus.OK);

	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody Customer customer){
		
		Authentication authentication = authenticationManager.authenticate(new 
				UsernamePasswordAuthenticationToken(customer.getEmailId(),customer.getPassword()));
		System.out.println(customer.getEmailId()+" "+customer.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities()));
	}
}
