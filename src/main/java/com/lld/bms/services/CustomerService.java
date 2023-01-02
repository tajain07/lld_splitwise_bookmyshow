package com.lld.bms.services;

import org.springframework.stereotype.Service;

import com.lld.bms.dtos.CreateCustomerDTO;
import com.lld.bms.dtos.CreateUserDTO;
import com.lld.bms.models.Customer;
import com.lld.bms.models.User;
import com.lld.bms.repositories.interfaces.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private final UserService userService;
    private final CustomerRepository customerRepository;

    public Customer createCustomer(CreateCustomerDTO customerRequest) {
        if (userService.doesUserExist(customerRequest.getUsername(), customerRequest.getEmail())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = userService.createUser(
                new CreateUserDTO(customerRequest.getUsername(), customerRequest.getPassword(), "CUSTOMER1"));

        Customer customer = Customer
                .builder()
                .city(customerRequest.getCity())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .user(user)
                .build();
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long customerId) {
        return customerRepository.getById(customerId);
    }
}
