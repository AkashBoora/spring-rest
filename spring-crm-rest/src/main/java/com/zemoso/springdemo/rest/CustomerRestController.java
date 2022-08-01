package com.zemoso.springdemo.rest;


import com.zemoso.springdemo.entity.Customer;
import com.zemoso.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    private CustomerService  customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        Customer customer = customerService.getCustomer(customerId);

        if(customer==null){
            throw new CustomerNotFoundException("Customer id not found - "+customerId);
        }
        return customer;
    }

    // adding mapping POST /customers -add a new Customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){

        // if id is 0 it will insert into db
        customer.setId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    // adding mapping PUT /customers - update existing customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }

    // adding mapping DELETE /customers/{customerId}
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){

        Customer customer = customerService.getCustomer(customerId);
        if(customer == null){
            throw  new CustomerNotFoundException("Customer id not found - "+customerId);
        }

        customerService.deleteCustomer(customerId);
        return  "Deleted Customer id - "+customerId;
    }
}

