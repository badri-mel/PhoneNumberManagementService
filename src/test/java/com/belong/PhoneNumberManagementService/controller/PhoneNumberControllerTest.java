package com.belong.PhoneNumberManagementService.controller;

import com.belong.PhoneNumberManagementService.dto.PhoneNumberActivationResponseDto;
import com.belong.PhoneNumberManagementService.service.PhoneNumberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.belong.PhoneNumberManagementService.controller.PhoneNumberController.ACTIVATED_SUCCESSFULLY;
import static org.mockito.Mockito.when;

@WebMvcTest(PhoneNumberController.class)
public class PhoneNumberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneNumberService phoneNumberService;

    @SneakyThrows
    @Test
    public void activatePhoneNumberTest() {
        when(phoneNumberService.activatePhoneNumber(1L,"ACTIVE"))
                .thenReturn(1);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/v1/phone-number/1/activate")
                .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(PhoneNumberActivationResponseDto
                                .builder()
                                        .message(ACTIVATED_SUCCESSFULLY)
                                        .build()
                                )
                        );
        MvcResult result = mockMvc.perform(request).andReturn();

        PhoneNumberActivationResponseDto actual = new ObjectMapper().readValue(result.getResponse().getContentAsString(), PhoneNumberActivationResponseDto.class);
        Assertions.assertEquals(PhoneNumberActivationResponseDto
                .builder()
                .message(ACTIVATED_SUCCESSFULLY)
                .build(),actual);


    }
}
