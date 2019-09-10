package com.cargo.controller;


import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cargo.entity.Consignment;
import com.cargo.entity.Customer;
import com.cargo.entity.Item;
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

	@PostMapping("/save")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer,HttpServletRequest request, HttpServletResponse response) {
		try {
		
			cargoService.saveCustomer(customer);
			
			
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
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
	
	@GetMapping("/customer")
	public ResponseEntity<?> getCustomer(HttpServletRequest request){
	
		try {
			Customer customer=cargoService.findByEmailId(this.getToken(request));
			
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/addConsignment")
	public ResponseEntity<?> addConsignmnet(@RequestBody Consignment consignment,HttpServletRequest request){
		
		try {
			Customer customer =cargoService.findByEmailId(this.getToken(request));
			boolean listCustomer =cargoService.addConsignmnet(consignment, customer);
						
			return new ResponseEntity<Boolean>(listCustomer, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/listConsignment")
	public ResponseEntity<?> getConsignment(HttpServletRequest request, HttpServletResponse response){
	
		try {
			Customer customer=cargoService.findByEmailId(this.getToken(request));
			List<Consignment> consignment=cargoService.listConsignment(customer.getAccountNo());
			
			
			return new ResponseEntity<List<Consignment>>(consignment, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/addItem/{consignmentId}")
	public ResponseEntity<?> addConsignmnet(@RequestBody Item item,@PathVariable int consignmentId){
		System.out.println(consignmentId);
		try {
			boolean check=cargoService.addItem(consignmentId, item);
						
			return new ResponseEntity<>(check, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/listItems/{consignmentId}")
	public ResponseEntity<?> getConsignment(@PathVariable int consignmentId){
	
		try {
			
			List<Item> Item=cargoService.listItem(consignmentId);
			
			
			return new ResponseEntity<List<Item>>(Item, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	

	
	}
