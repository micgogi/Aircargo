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

//	@PostMapping(path = "/date")
//	@ResponseBody
//	public ResponseEntity<?> getReportByDate(HttpEntity<String> httpEntity) throws ParseException {
//		System.out.println("--->>>>>>>>>>>>>>>>>>>>>>>>>control is here");
//		ResponseEntity responseEntity = null;
//
//		String fromDate = new String();
//		String toDate = new String();
//
//		String json = httpEntity.getBody();
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			JsonNode actualObj = mapper.readTree(json);
//
//			fromDate = actualObj.get("from").asText();
//			toDate = actualObj.get("to").asText();
//
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//			Date from = formatter.parse(fromDate);
//			Date to = formatter.parse(toDate);
//
//			List<Consignment> dateReportData;
//			try {
//				dateReportData = consignmentDAO.getReportByDate(from, to);
//				responseEntity = new ResponseEntity<List<Consignment>>(dateReportData, HttpStatus.OK);
//			} catch (NoSuchReportExistsException e) {
//				responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//			}
//			System.out.println(responseEntity.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return responseEntity;
//	}
//	
//	@ResponseBody
//	@PostMapping(path = "/revenue")
//	public ResponseEntity<?> getReportByRevenue(HttpEntity<String> httpEntity) throws ParseException {
//		ResponseEntity responseEntity = null;
//
//		String fromDate = new String();
//		String toDate = new String();
//
//		String json = httpEntity.getBody();
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			JsonNode actualObj = mapper.readTree(json);
//
//			fromDate = actualObj.get("from").asText();
//			toDate = actualObj.get("to").asText();
//
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//			Date from = formatter.parse(fromDate);
//			Date to = formatter.parse(toDate);
//
//			List<?> dateReportData;
//			try {
//				dateReportData = consignmentDAO.getReportByRevenue(from, to);
//				responseEntity = new ResponseEntity<List<?>>(dateReportData, HttpStatus.OK);
//			} catch (NoSuchReportExistsException e) {
//				responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//			}
//			System.out.println(responseEntity.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return responseEntity;
//	}
//	
//	@GetMapping(path = "/airportNames")
//	public ResponseEntity<?> getAirportNames(){
//		ResponseEntity responseEntity;
//		List<String> airports;
//		try {
//			airports = consignmentDAO.getAirportNames();
//			System.out.println("Airports: "+airports);
//			responseEntity = new ResponseEntity<List<String>>(airports, HttpStatus.OK);
//		} catch (NoCityExistsException e) {
//			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
//		return responseEntity;
//		
//	}

