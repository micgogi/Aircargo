/**
 * 
 */
package com.micgogi.aircargo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micgogi.aircargo.entity.Consignment;
import com.micgogi.aircargo.service.ConsignmentServiceImpl;

/**
 * @author micgogi AdminController.java
 *
 *         2:34:46 PMSep 11, 2019
 * 
 */
@RestController
@RequestMapping("api/auth/")
public class AdminController {
	@Autowired
	ConsignmentServiceImpl consignemntService;

	@PostMapping("getReportsByAirport")
	public ResponseEntity<?> getReportByAirport(HttpEntity<String> httpEntity, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		String startDate, endDate, source;
		String json = httpEntity.getBody();
		List<Consignment> consignemntListByDateAndSource = null;
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonData;
		try {
			jsonData = objectMapper.readTree(json);
			startDate = jsonData.get("start").asText();
			endDate = jsonData.get("end").asText();
			source = jsonData.get("airport").asText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endDate);
			consignemntListByDateAndSource = consignemntService.getAllReportsBYDate(source, start, end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

		return new ResponseEntity<List<Consignment>>(consignemntListByDateAndSource, HttpStatus.OK);

	}

	@PostMapping("getReportByDate")
	public ResponseEntity<?> getReportByDate(HttpEntity<String> httpEntity, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		List<Consignment> consignmentList;

		String startDate = new String();
		String endDate = new String();

		String json = httpEntity.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode actualObj = objectMapper.readTree(json);

			startDate = actualObj.get("from").asText();
			endDate = actualObj.get("to").asText();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endDate);

			consignmentList = consignemntService.getAllReports(start, end);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(consignmentList, HttpStatus.OK);
	}

	@PostMapping("getRevenue")
	public ResponseEntity<?> getConsigmentRevenue(HttpEntity<String> httpEntity, HttpServletRequest request,
			HttpServletResponse response) {

		String start = new String();
		String end = new String();

		String json = httpEntity.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		List<?> consignmentList;

		JsonNode actualObj;
		try {
			actualObj = objectMapper.readTree(json);
			start = actualObj.get("from").asText();
			end = actualObj.get("to").asText();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);

			consignmentList = consignemntService.getAllReportsByRevenue(startDate, endDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(consignmentList, HttpStatus.OK);

	}
	@PostMapping("getAllAirports")
	public ResponseEntity<?> getAllAirports(HttpServletRequest request){
		try {
			return new ResponseEntity<>(consignemntService.getAllAirports(),HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
	}

}
