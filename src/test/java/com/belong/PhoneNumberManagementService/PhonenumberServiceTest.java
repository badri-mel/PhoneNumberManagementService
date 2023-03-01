package com.belong.PhoneNumberManagementService;

import com.belong.PhoneNumberManagementService.dto.PhoneNumberResponseDto;
import com.belong.PhoneNumberManagementService.entity.CustomerEntity;
import com.belong.PhoneNumberManagementService.entity.PhoneNumberEntity;
import com.belong.PhoneNumberManagementService.repository.CustomerRepository;
import com.belong.PhoneNumberManagementService.repository.PhoneNumberRepository;
import com.belong.PhoneNumberManagementService.service.PhoneNumberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class PhonenumberServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;
    @InjectMocks
    private PhoneNumberService phoneNumberService;


    public void setupAll() {



    when(phoneNumberRepository.findAll(Pageable.ofSize(1)))
            .thenReturn(new PageImpl<>(
                    List.of(PhoneNumberEntity.builder()
                                    .number("0444555666")
                                    .id(1L)
                                    .customer(CustomerEntity.builder()
                                            .id(1L)
                                            .email("bnarayanan@gmail.com")
                                            .firstName("Badri")
                                            .lastName("Sugavanam")
                                            .build())
                                    .status(PhoneNumberEntity.Status.ACTIVE)
                                    .build(),
                            PhoneNumberEntity.builder()
                                    .number("0444555667")
                                    .id(2L)
                                    .customer(CustomerEntity.builder()
                                            .id(2L)
                                            .email("bnarayanan1@gmail.com")
                                            .firstName("Badri")
                                            .lastName("Sugavanam")
                                            .build())
                                    .status(PhoneNumberEntity.Status.ACTIVE)
                                    .build())
            ));


    }





    public void setup() {



        when(phoneNumberRepository.findByCustomer_Id(1L,Pageable.ofSize(1)))
                .thenReturn(new PageImpl<>(List.of(PhoneNumberEntity.builder()
                                .number("0444555666")
                                .id(1L)
                                .customer(CustomerEntity.builder()
                                        .id(1L)
                                        .email("bnarayanan@gmail.com")
                                        .firstName("Badri")
                                        .lastName("Sugavanam")
                                        .build())
                                .status(PhoneNumberEntity.Status.ACTIVE)
                                .build())));

    }

    @Test
    public void testGetAllPhoneNumbers(){
        setupAll();
        Page<PhoneNumberResponseDto> result = phoneNumberService.getAllPhoneNumbers(Pageable.ofSize(1));
        boolean isEqual = result.stream().toList().containsAll(List.of(PhoneNumberResponseDto.builder()
                        .phoneNumberId(1L)
                        .number("0444555666")
                        .customerId(1L)
                        .status(PhoneNumberResponseDto.Status.ACTIVE)
                        .build(),
                PhoneNumberResponseDto.builder()
                        .phoneNumberId(2L)
                        .number("0444555667")
                        .customerId(2L)
                        .status(PhoneNumberResponseDto.Status.ACTIVE)
                        .build()));
        System.out.println(result.stream().toList());
        Assertions.assertTrue(isEqual);


    }

    @Test
    public void testGetPhoneNumberByCustomerId(){
        setup();
        Page<PhoneNumberResponseDto> result = phoneNumberService.getAllPhoneNumbersPerCustomer(1L, Pageable.ofSize(1));
        boolean isTrue = phoneNumberService.getAllPhoneNumbersPerCustomer(1L, Pageable.ofSize(1))
                .stream().toList()
                .containsAll(List.of(PhoneNumberResponseDto.builder()
                                .customerId(1L)
                                .phoneNumberId(1L)
                                .number("0444555666")
                                .status(PhoneNumberResponseDto.Status.ACTIVE)
                                .build()));
        Assertions.assertTrue(isTrue);
    }
}
