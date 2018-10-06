package com.capgemini.customer.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.ws.Response;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.CustomernotFoundException;
import com.capgemini.customer.service.CustomerService;

@RestController
public class CustomerController {
	private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> addedCustomer = new ResponseEntity<Customer>(customerService.addCustomer(customer),
				HttpStatus.OK);
		return addedCustomer;
	}

	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		try {
			Customer updateCustomer = customerService.findCustomerById(customer.getCustomerId());
			ResponseEntity<Customer> updatedCustomer = new ResponseEntity<Customer>(
					customerService.updateCustomer(customer), HttpStatus.OK);
			return updatedCustomer;
		} catch (CustomernotFoundException e) {
			LOGGER.info(customer.getCustomerId() + "not found");
			// logger
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable int customerId) {
		try {
			Customer customer;
			customer = customerService.findCustomerById(customerId);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (CustomernotFoundException e) {
			// logger
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/customers")
	public List<Customer> findAllCustomers() {
		return customerService.findAllCustomers();

	}

	@DeleteMapping("/customers/{customerId}")
	public void deleteCustomer(@PathVariable int customerId) {

		customerService.deleteCustomer(customerId);

	}

}
