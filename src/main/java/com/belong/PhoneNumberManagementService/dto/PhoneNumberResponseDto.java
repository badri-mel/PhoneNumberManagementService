package com.belong.PhoneNumberManagementService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneNumberResponseDto {

    private Long customerId;
    private Long phoneNumberId;
    private String number;
    private Status status;


    public enum Status {
        INACTIVE,ACTIVE
    }
}
