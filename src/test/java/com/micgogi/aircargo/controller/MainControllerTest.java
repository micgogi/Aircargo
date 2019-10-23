/**
 * 
 */
package com.micgogi.aircargo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.micgogi.aircargo.AircargoApplication;
import com.micgogi.aircargo.entity.Consignment;
import com.micgogi.aircargo.entity.Customer;
import com.micgogi.aircargo.entity.ResponseMessage;

/**
 * @author micgogi MainControllerTest.java
 *
 *         5:49:03 PMSep 12, 2019
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AircargoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	@Autowired
	TestRestTemplate testRestTemplate;
	Customer customer;

	@Before
	public void consignData() {
		customer = new Customer();
		customer.setCustomerName("rahul gogyani");
		customer.setEmailId("micgogi4@gmail.com");
		customer.setCustomerName("rahul");
		customer.setAddress("dlf hyderabad");
		customer.setMobileNo("9876543210");
		customer.setPassword("micgogi");

	}

	@Test
	public void firstTest() throws Exception {

		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signup", customer,
				ResponseMessage.class);
		assertThat(response.getBody().getMessage(),
				containsString("Message: Yahoo! Yours Details are saved successFully"));
}

	@Test
	public void secondTest() throws Exception {

		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signup", customer,
				ResponseMessage.class);
		assertThat(response.getBody().getMessage(), containsString("Fail->EMail is already registered"));
	}

	@Test
	public void thirdTest() throws Exception {
		Customer customer1 = new Customer();
		customer1.setEmailId("micgogi4@gmail.com");
		customer1.setPassword("micgogi");
		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signin", customer1,
				ResponseMessage.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

	}

	@Test
	public void fourthTest() throws Exception {
		String accessToken = "eyJhbGciOiJIUzUxMiJ9."
				+ "eyJzdWIiOiJyYWh1bC5nb2d5YW5pQGdtYWlsLmNvbSIsImlhdCI6MTU2ODI5MzcyOSwiZXhwIjoxNTY4MzgwMTI5fQ."
				+ "I6_e5WVGLHnbb1lAQ4ukOp6YAp1be_fNbq9nYZ9XVmBU8t7CHAoX3HOOUHrUUX_qMv3-SAs6Z44n0EB1omeeoQ";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+accessToken);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		ResponseEntity<Object[]> response = testRestTemplate.exchange("/api/auth/getConsignment",HttpMethod.GET,
				httpEntity,Object[].class);
		LOGGER.info("Response->{}",response.getBody());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
		//assertThat(response.getBody(),equalTo(true));

	}

}
