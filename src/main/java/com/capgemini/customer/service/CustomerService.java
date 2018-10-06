package com.capgemini.customer.service;

import java.util.List;
import java.util.Set;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.CustomernotFoundException;

public interface CustomerService {

	public Customer  addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(int customerId);
	public Customer findCustomerById(int customerId) throws CustomernotFoundException;
    public List<Customer> findAllCustomers();
}
