package com.capgemini.customer.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.controller.CustomerController;
import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.service.CustomerService;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	@InjectMocks
	private CustomerController customerController;
	private MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	
	@Test
	public void testAddcustomer() throws Exception
	{
		when(customerService.addCustomer(Mockito.isA(Customer.class))).thenReturn(new Customer(12346, "Sam",25, "abc", 999));
		mockMvc.perform(post("/customers")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"customerId\":12346,\"customerName\":\"Sam\",\"customerAge\":25,\"customerAddress\":\"abc\",\"customerPhoneNumber\":9990}")
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerId").exists())
        .andExpect(jsonPath("$.customerName").exists())
        .andExpect(jsonPath("$.customerAge").exists())
        .andExpect(jsonPath("$.customerPhoneNumber").exists())
        .andExpect(jsonPath("$.customerAddress").exists())
        .andExpect(jsonPath("$.customerId").value(12346))
        .andExpect(jsonPath("$.customerName").value("Sam"))
        .andExpect(jsonPath("$.customerAge").value(25))
        .andExpect(jsonPath("$.customerAddress").value("abc"))
        .andExpect(jsonPath("$.customerPhoneNumber").value(999))
        .andDo(print());		   
	
	}
	
	@Test
	public void testFindCustomerById()throws Exception
	{
		when(customerService.findCustomerById(12345)).thenReturn(new Customer(12345, "Sam",25, "abc", 999));
		mockMvc.perform(get("/customers/12345"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerId").exists())
        .andExpect(jsonPath("$.customerName").exists())
        .andExpect(jsonPath("$.customerAge").exists())
        .andExpect(jsonPath("$.customerPhoneNumber").exists())
        .andExpect(jsonPath("$.customerAddress").exists())
        .andExpect(jsonPath("$.customerId").value(12345))
        .andExpect(jsonPath("$.customerName").value("Sam"))
        .andExpect(jsonPath("$.customerAge").value(25))
        .andExpect(jsonPath("$.customerAddress").value("abc"))
        .andExpect(jsonPath("$.customerPhoneNumber").value(999))
        .andDo(print());		              
	}
	
	/*@Test
	public void testFindCustomerByIdNotFound()throws Exception
	{
		when(customerService.findCustomerById(12346)).thenReturn(new Customer(12346, "Sam",25, "abc", 999));

	
	}*/
	
	@Test
	public void testUpdatecustomer() throws Exception
	{
		String content="{\"customerId\":12345,\"customerName\":\"Sam\",\"customerAge\":25,\"customerAddress\":\"abc\",\"customerPhoneNumber\":999}";
		when(customerService.updateCustomer(Mockito.isA(Customer.class))).thenReturn(new Customer(12345, "Sam",25, "abc", 999));
		when(customerService.findCustomerById(12345)).thenReturn(new Customer(12345, "Sam",25, "abc", 999));
		mockMvc.perform(put("/customers")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerId").exists())
        .andExpect(jsonPath("$.customerName").exists())
        .andExpect(jsonPath("$.customerAge").exists())
        .andExpect(jsonPath("$.customerPhoneNumber").exists())
        .andExpect(jsonPath("$.customerAddress").exists())
        .andExpect(jsonPath("$.customerId").value(12345))
        .andExpect(jsonPath("$.customerName").value("Sam"))
        .andExpect(jsonPath("$.customerAge").value(25))
        .andExpect(jsonPath("$.customerAddress").value("abc"))
        .andExpect(jsonPath("$.customerPhoneNumber").value(999))
        .andDo(print());
	}
	
	@Test
	public void testDeletecustomer() throws Exception 
	{
		//String content="{\"customerId\":12345,\"customerName\":\"TV\",\"customerCategory\":\"LED\",\"customerPrice\":10000.0}";
//		when(customerService.findCustomerById(12345)).thenReturn(new Customer(12345,"machine", 25, "art", 100));
		mockMvc.perform(delete("/customers/12345"))
		.andDo(print())
		.andExpect(status().isOk());
			
	}
	
	/*@Test
	public void testFindAllCustomers()
	{
		Customer customer
	}
		
	}*/
}

