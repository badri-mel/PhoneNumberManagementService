package com.belong.PhoneNumberManagementService.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ExceptionResponse {
    private String message;
    private HttpStatus status;
}
