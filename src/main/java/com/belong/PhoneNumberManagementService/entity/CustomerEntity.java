package com.belong.PhoneNumberManagementService.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name ="firstname")
    private String firstName;

    @Column(name ="lastname")
    private String lastName;

    @Column(name ="email")
    private String email;


    @OneToMany
    @JoinColumn(name = "customer_id")
    @JsonProperty("phone_numbers")
    private Set<PhoneNumberEntity> phoneNumberEntities = new HashSet<>();


}