package com.belong.PhoneNumberManagementService.service;

import com.belong.PhoneNumberManagementService.dto.PhoneNumberResponseDto;
import com.belong.PhoneNumberManagementService.entity.PhoneNumberEntity;
import com.belong.PhoneNumberManagementService.exception.ResourceNotFoundException;
import com.belong.PhoneNumberManagementService.repository.PhoneNumberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;


    public Page<PhoneNumberResponseDto> getAllPhoneNumbers(Pageable pageable) {

        Page<PhoneNumberEntity> resultEntity = phoneNumberRepository.findAll(pageable);

        return Optional.of(resultEntity
                .map(entity -> new ModelMapper().map(entity, PhoneNumberResponseDto.class)))
                .filter(phoneNumberResponseDtos -> phoneNumberResponseDtos.getTotalPages()>0)
                .orElseThrow(()-> new ResourceNotFoundException("No PhoneNumber found"));
    }


    public Page<PhoneNumberResponseDto> getAllPhoneNumbersPerCustomer(Long customerId, Pageable pageable) {

        Page<PhoneNumberEntity> resultEntity = phoneNumberRepository.findByCustomer_Id(customerId, pageable);
        return Optional.of(resultEntity.map(entity -> new ModelMapper().map(entity, PhoneNumberResponseDto.class)))
                .filter(r->r.getTotalPages()!=0)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

    }


    public Integer activatePhoneNumber(Long phoneNumberId, String status) {

        return Optional.of(phoneNumberRepository.updateStatusByIdV1(PhoneNumberEntity.Status.valueOf(status), phoneNumberId))
                .filter(integer -> integer !=0)
                .orElseThrow(()->new ResourceNotFoundException("PhoneNumber found Exception"));

    }

}
