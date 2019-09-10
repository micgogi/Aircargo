package com.micgogi.aircargo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micgogi.aircargo.entity.Consignment;
import com.micgogi.aircargo.entity.Customer;
import com.micgogi.aircargo.entity.Item;
import com.micgogi.aircargo.entity.JwtResponse;
import com.micgogi.aircargo.entity.ResponseMessage;
import com.micgogi.aircargo.security.JwtProvider;
import com.micgogi.aircargo.service.ConsignmentServiceImpl;
import com.micgogi.aircargo.service.CustomerService;
import com.micgogi.aircargo.service.ItemServiceImpl;

import io.jsonwebtoken.Jwts;

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
	@Autowired
	ConsignmentServiceImpl consignmentService;
	@Autowired
	ItemServiceImpl itemService;

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
	public ResponseEntity<?> authenticateCustomer(@RequestBody Customer customer) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(customer.getEmailId(), customer.getPassword()));
		System.out.println(customer.getEmailId() + " " + customer.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/bookCosignment")
	public ResponseEntity<?> bookCosignment(@RequestBody Consignment consignment, HttpServletRequest request,
			HttpServletResponse response) {
		
		System.out.println("inconsingment");
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String emailId = Jwts.parser().setSigningKey("rahul_gogyani").parseClaimsJws(token).getBody().getSubject();
		try {
			Customer customer = customerService.getCustomerByEmail(emailId);
			consignment.setCustomer(customer);
			consignmentService.addConsignemnt(consignment);
			
			
			
		}catch(Exception e) {
			
		}
		return new ResponseEntity<>(emailId, HttpStatus.OK);

	}
	@GetMapping("/getConsignment")
	public ResponseEntity<List<Consignment>> getAllConsignment(HttpServletRequest request,
			HttpServletResponse response){
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String emailId = Jwts.parser().setSigningKey("rahul_gogyani").parseClaimsJws(token).getBody().getSubject();
		Customer customer = customerService.getCustomerByEmail(emailId);
		System.out.println(customer.getAccountNo());
		List<Consignment> consignmentList = consignmentService.getAllConsignmentByCustomer(customer.getAccountNo());
		return new ResponseEntity<List<Consignment>>(consignmentList,HttpStatus.OK);
	}
	@PostMapping("/saveItem/{consignmentId}")
	public ResponseEntity<Boolean> saveItem(HttpServletRequest request ,@RequestBody Item item, @PathVariable("consignmentId") Integer id){
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		Consignment consignment = consignmentService.findById(id);
		item.setConsignemnt(consignment);
		
		itemService.saveItem(item);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		
	}
	@GetMapping("getAllItems/{consignmentId}")
	public ResponseEntity<List<Item>> getAllConsignment(HttpServletRequest request, @PathVariable("consignmentId") Integer id){
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		List<Item> itemList = itemService.getAllItemByConsignment(id);
		return new ResponseEntity<List<Item>>(itemList,HttpStatus.OK);
	}

}
