package com.capgemini.customer.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.stereotype.Service;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.CustomernotFoundException;
import com.capgemini.customer.repository.CustomerRepository;
import com.capgemini.customer.service.CustomerService;

@Service
class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		return customer = customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {

		return customer = customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(int customerId) {

		customerRepository.deleteById(customerId);
	}

	@Override
	public Customer findCustomerById(int customerId) throws CustomernotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent())
			return customer.get();
		throw new CustomernotFoundException("Customer Not Found");

	}

	@Override
	public List<Customer> findAllCustomers() {

		return  customerRepository.findAll();

	}
	
	
	
	
}