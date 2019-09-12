package com.cargo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cargo.entity.Consignment;
import com.cargo.entity.Customer;
import com.cargo.entity.Item;
import com.cargo.service.CargoService;
import com.cargo.service.SecurityTokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;







@RunWith(SpringRunner.class)
@WebMvcTest(CargoController.class)
public class CargoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private CargoController cargoController;

	@MockBean
	private CargoService cargoService;
	
	
	
	@MockBean
	private SecurityTokenGenerator tokenGenerator;

	private Customer customer;
	
	private Item item;

	private ObjectMapper objectMapper;
	
	private Consignment consignment;

	@Before
	public void setup() throws ParseException {
		MockitoAnnotations.initMocks(this);
		
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(cargoController).build();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		customer =new Customer();
		customer.setCustomerName("Myvin");
		customer.setEmailId("myvinbarboza@gmail.com");
		customer.setMobileNo(123456789);
		customer.setPassword("password");
		customer.setAddress("Gachibowlo");
		
		
		consignment=new Consignment();
		consignment.setDateOfShipment(sdf.parse("1996-04-02"));
		consignment.setDestination("Pakistan");
		consignment.setServiceType("Express");
		consignment.setShipmentAddress("MUIJKJH");
		consignment.setSource("India");
		consignment.setTotalCost(123);

		item=new Item();
		item.setItemCategory("fragile good");
		item.setItemName("hola glass");
		item.setWeight(45);
		
			
	}
	
	@Test
	public void testSave() throws Exception {
		when(cargoService.saveCustomer(customer)).thenReturn(true);
		mockMvc.perform(post("/save").content(objectMapper.writeValueAsString(customer))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());
//	verify(cargoService, times(1)).saveCustomer(customer);
//	verifyNoMoreInteractions(cargoService);
	

	}
	
	@Test
	public void testLoginUser() throws Exception {
		when(cargoService.findByEmailIdAndPassword(customer.getEmailId(), customer.getPassword())).thenReturn(customer);

		mockMvc.perform(post("/login").content(objectMapper.writeValueAsString(customer))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());

		verify(cargoService, times(1)).findByEmailIdAndPassword(customer.getEmailId(), customer.getPassword());
		verifyNoMoreInteractions(cargoService);
	}
	
	@Test
	public void testCustomer() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJteXZpbmJhcmJvemFAZ21haWwuY29tIiwiaWF0IjoxNTY4MDk4MjU4fQ.f_mFj8ZPxjlnb6F2gm3CoUEc1t1ypedmlXwEvfmSB-Yoc_o8RK7KmkzYXtttKQGr";

		when(cargoService.findByEmailId("myvinbarboza@gmail.com")).thenReturn(customer);
		mockMvc.perform(get("/customer").header("authorization","Bearer "+token).contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());
		verify(cargoService, times(1)).findByEmailId("myvinbarboza@gmail.com");
		verifyNoMoreInteractions(cargoService);
	}
	
	@Test
	public void testAddConsignment() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJteXZpbmJhcmJvemFAZ21haWwuY29tIiwiaWF0IjoxNTY4MDk4MjU4fQ.f_mFj8ZPxjlnb6F2gm3CoUEc1t1ypedmlXwEvfmSB-Yoc_o8RK7KmkzYXtttKQGr";
		when(cargoService.findByEmailId("myvinbarboza@gmail.com")).thenReturn(customer);
		when(cargoService.addConsignmnet(consignment, customer)).thenReturn(true);
		mockMvc.perform(post("/addConsignment").header("authorization","Bearer "+token).content(objectMapper.writeValueAsString(consignment)).contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());
//		verify(cargoService, times(1)).findByEmailId("myvinbarboza@gmail.com");
//		verify(cargoService, times(1)).addConsignmnet(consignment, customer);
//		verifyNoMoreInteractions(cargoService);
	}
	
	@Test
	public void testListConsignment() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJteXZpbmJhcmJvemFAZ21haWwuY29tIiwiaWF0IjoxNTY4MDk4MjU4fQ.f_mFj8ZPxjlnb6F2gm3CoUEc1t1ypedmlXwEvfmSB-Yoc_o8RK7KmkzYXtttKQGr";
		when(cargoService.findByEmailId("myvinbarboza@gmail.com")).thenReturn(customer);
		List<Consignment> list = new ArrayList<>();
		when(cargoService.listConsignment(1)).thenReturn(list);
		mockMvc.perform(get("/listConsignment").header("authorization","Bearer "+token).contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());
//		verify(cargoService, times(1)).findByEmailId("myvinbarboza@gmail.com");
//		verify(cargoService, times(1)).listConsignment(1);
//		verifyNoMoreInteractions(cargoService);
	}
	
	@Test
	public void testAddItem() throws Exception {
		
		when(cargoService.addItem(1, item)).thenReturn(true);
		mockMvc.perform(post("/addItem/1").content(objectMapper.writeValueAsString(item)).contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());

	}
	
	@Test
	public void listItem() throws Exception {
		List<Item> list = new ArrayList<>();
		when(cargoService.listItem(1)).thenReturn(list);
		mockMvc.perform(post("/addItem/1").content(objectMapper.writeValueAsString(item)).contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());

	}
	

}
