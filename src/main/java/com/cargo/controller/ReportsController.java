package com.cargo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.cargo.entity.Consignment;

import com.cargo.service.CargoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/")
@CrossOrigin()
public class ReportsController {

	@Autowired
	private CargoService cargoService;



	@PostMapping(path = "/airport")
	@ResponseBody
	public ResponseEntity<?> listReportAirport(HttpEntity<String> httpEntity) throws ParseException {
		System.out.println("--->>>");
		String from,to,source;
		
		String json = httpEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode Obj = mapper.readTree(json);
			from = Obj.get("from").asText();
			to = Obj.get("to").asText();
			source = Obj.get("airport").asText();
			System.out.println(from+"*"+to+"*"+source);
			

				List<Consignment> consignment = cargoService.listByAirport(from, to, source);
				return new ResponseEntity<List<Consignment>>(consignment, HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		
		}
	
	@PostMapping(path = "/duration")
	@ResponseBody
	public ResponseEntity<?> listReportDuration(HttpEntity<String> httpEntity) throws ParseException {
		System.out.println("--->>>");
		String from,to;
		String json = httpEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		try {
				JsonNode Obj = mapper.readTree(json);
				from = Obj.get("from").asText();
				to = Obj.get("to").asText();
				List<Consignment> consignment = cargoService.listByDuration(from, to);
				return new ResponseEntity<List<Consignment>>(consignment, HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		
		}
	
	@PostMapping(path = "/revenue")
	@ResponseBody
	public ResponseEntity<?> listRevenue(HttpEntity<String> httpEntity) throws ParseException {
		System.out.println("--->>>");
		String from,to;
		String json = httpEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		try {
				JsonNode Obj = mapper.readTree(json);
				from = Obj.get("from").asText();
				to = Obj.get("to").asText();
				List<?> revenue = cargoService.listByRevenue(from, to);
				return new ResponseEntity<List>(revenue, HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		
		}

	}



