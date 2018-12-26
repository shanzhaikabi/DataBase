package com.ssh.service;

import com.ssh.entity.Customer;

public interface CustomerService {
    Customer get(String customerId);
}
