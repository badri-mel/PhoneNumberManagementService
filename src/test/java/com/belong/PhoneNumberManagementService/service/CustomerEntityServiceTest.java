package com.belong.PhoneNumberManagementService.service;

import com.belong.PhoneNumberManagementService.entity.CustomerEntity;
import com.belong.PhoneNumberManagementService.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerEntityServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    public void setup(){

        when(customerRepository.findById(1L))
                .thenReturn(Optional.ofNullable(CustomerEntity.builder()
                        .id(1L)
                        .email("bnarayanan@gmail.com")
                        .firstName("Badri")
                        .lastName("Sugavanam")
                        .build()));

    }


    @Test
    void fetchCustomer(){
        setup();

        CustomerEntity actualCustomerEntity = customerService.getCustomer(1L);

        Assertions.assertEquals(CustomerEntity.builder()
                .id(1L)
                .email("bnarayanan@gmail.com")
                .firstName("Badri")
                .lastName("Sugavanam")
                .build(), actualCustomerEntity);
    }

    @Test
    void fetchPhonePerCustomer(){
        setup();

        CustomerEntity actualCustomerEntity = customerService.getCustomer(1L);

        Assertions.assertEquals(CustomerEntity.builder()
                .id(1L)
                .email("bnarayanan@gmail.com")
                .firstName("Badri")
                .lastName("Sugavanam")
                .build(), actualCustomerEntity);
    }
}
