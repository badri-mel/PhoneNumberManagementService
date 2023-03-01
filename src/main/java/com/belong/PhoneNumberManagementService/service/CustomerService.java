package com.belong.PhoneNumberManagementService.service;

import com.belong.PhoneNumberManagementService.entity.CustomerEntity;
import com.belong.PhoneNumberManagementService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;



    public CustomerEntity getCustomer(Long customerId){
        return customerRepository.findById(customerId).get();
    }
}
