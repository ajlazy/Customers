package com.capgemini.customer.rest;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import com.capgemini.customer.entity.Customer;

public class CustomerControllerRestTemplate {
	
	private static RestTemplate REST_TEMPLATE=new RestTemplate();
	private static final String baseUrl="http://localhost:9090/";
	
	
public static void main(String[] args) {
		
		//Adding a new product into database.
		Customer customer = new Customer(102, "Samaa Dua", 29, "Dwarka", 981);
		customer = addCustomer(baseUrl+"customers", customer);
			
		/*//Getting product from database
		String url = baseUrl + "/products/102";
		Product product = findProductById(url);
		System.out.println("Product from DB : " + product);*/
		
		/*// Deleting product from database
		String url = baseUrl + "products/101";		
		deleteProduct(url);*/
		
		//Updating product into database
		/*String url = baseUrl + "product";
		Customer customer = new Customer(101, "Apple iPhone X", "Mobile", 95000);
		updateProduct(url, product);
		//customer = findProductById(baseUrl + "products/101");*/
			
}
	
	
	public static Customer addCustomer(String url,Customer customer)
		{
			 REST_TEMPLATE.postForObject(url, customer,Customer.class);
			return customer;
			 
			
		}
	
	public static Customer updateCustomer(String url,Customer customer)
	{
		REST_TEMPLATE.put(url, customer);
		return customer;
	}
		
	public static void deleteCustomer(String url)
	{
		REST_TEMPLATE.delete(url);
		

	}
	public static Customer findProductById(String url)
	{
		
		return REST_TEMPLATE.getForObject(url,Customer.class);
	}
	
		
	
	
	

}
