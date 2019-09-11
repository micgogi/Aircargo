package com.brajesh.cargo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.brajesh.cargo.entity.Consignment;
import com.brajesh.cargo.entity.Item;
import com.brajesh.cargo.service.ConsignmentService;
import com.brajesh.cargo.service.ItemService;
import io.jsonwebtoken.Jwts;

public class CargoController {

	@Autowired
	private ConsignmentService consignmentService;

	@Autowired
	private ItemService itemService;

	@PostMapping("/bookCosignment")
	public ResponseEntity<?> bookCosignment(@RequestBody Consignment consignment,
		
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("inconsingment");
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		String roleId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getId();
		try {
			if(roleId.equals("1")) {
			consignment.setCustomerId(userId);
			consignmentService.addConsignemnt(consignment);
			}
			else 
			{
				return new ResponseEntity<String>("Access Denied!",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {

		}
		return new ResponseEntity<>(userId, HttpStatus.OK);

	}

	@GetMapping("/getConsignment")
	public ResponseEntity<?> getAllConsignment(HttpServletRequest request,
			HttpServletResponse response) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		String roleId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getId();
		System.out.println(userId);
		if(roleId.equals("1")) {
			List<Consignment> consignmentList = consignmentService.getAllConsignmentByCustomer(Integer.parseInt(userId));
			return new ResponseEntity<List<Consignment>>(consignmentList, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Access Denied!", HttpStatus.BAD_REQUEST);	
	}

	@PostMapping("/saveItem/{consignmentId}")
	public ResponseEntity<Boolean> saveItem(HttpServletRequest request, @RequestBody Item item,
			@PathVariable("consignmentId") Integer id) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		Consignment consignment = consignmentService.findById(id);
		item.setConsignemnt(consignment);

		itemService.saveItem(item);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);

	}

	@GetMapping("getAllItems/{consignmentId}")
	public ResponseEntity<List<Item>> getAllConsignment(HttpServletRequest request,
			@PathVariable("consignmentId") Integer id) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		List<Item> itemList = itemService.getAllItemByConsignment(id);
		return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
	}
	
}
