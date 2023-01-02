package com.lld.bms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lld.bms.dtos.CreateCustomerDTO;
import com.lld.bms.models.Customer;
import com.lld.bms.services.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody CreateCustomerDTO customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Customer createCustomer(@RequestParam Long id) {
        return customerService.getCustomer(id);
    }

}
