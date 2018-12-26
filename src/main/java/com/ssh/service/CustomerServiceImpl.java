package com.ssh.service;

import com.ssh.entity.Customer;
import com.ssh.respository.CustomerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepositoryImpl customerRepository;

    public Customer get(String customerId) {
        return customerRepository.get(customerId);
    }
}
