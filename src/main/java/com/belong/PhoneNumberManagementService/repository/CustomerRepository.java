package com.belong.PhoneNumberManagementService.repository;

import com.belong.PhoneNumberManagementService.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

//    @Override
//    Optional<Customer> findById(Long aLong);
}