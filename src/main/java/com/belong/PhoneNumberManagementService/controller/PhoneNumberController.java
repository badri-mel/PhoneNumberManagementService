package com.belong.PhoneNumberManagementService.controller;

import com.belong.PhoneNumberManagementService.dto.PhoneNumberActivationResponseDto;
import com.belong.PhoneNumberManagementService.dto.PhoneNumberResponseDto;
import com.belong.PhoneNumberManagementService.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PhoneNumberController {

    public static final int PAGE_SIZE = 20;
    public static final String ACTIVATED_SUCCESSFULLY = "Activated successfully";

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping(value = "/v1/phone-numbers")
    public ResponseEntity<?> getAllPhoneNumbers(@RequestParam(defaultValue ="0") Integer page){
        return ResponseEntity.ok(phoneNumberService.getAllPhoneNumbers(PageRequest.of(page, PAGE_SIZE)));
    }

    @GetMapping(value = "/v1/customers/{id}/phone-numbers")
    public ResponseEntity<Page<PhoneNumberResponseDto>> getAllPhoneNumberPerCustomer(@PathVariable Long id, @RequestParam(defaultValue ="0") Integer page){
       return ResponseEntity.ok(phoneNumberService.getAllPhoneNumbersPerCustomer(id,PageRequest.of(page,PAGE_SIZE)));
    }


    @PutMapping(value = "/v1/phone-number/{phoneNumberId}/activate")
    public ResponseEntity<?> activatePhoneNumber(@PathVariable Long phoneNumberId, @RequestParam(defaultValue = "ACTIVE") String status) {
        phoneNumberService.activatePhoneNumber(phoneNumberId, status);
        return ResponseEntity.ok(PhoneNumberActivationResponseDto.builder()
                        .message(ACTIVATED_SUCCESSFULLY)
                .build());
    }

}
