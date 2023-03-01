package com.belong.PhoneNumberManagementService.repository;

import com.belong.PhoneNumberManagementService.entity.PhoneNumberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PhoneNumberRepository extends PagingAndSortingRepository<PhoneNumberEntity, Long> {
    @Transactional
    @Modifying
    @Query("update PhoneNumberEntity p set p.status = ?1 where p.id = ?2")
    int updateStatusByIdV1(@NonNull PhoneNumberEntity.Status status, @NonNull Long id);
    @Transactional
    @Modifying
    @Query(value = "update phone_number set status = ?1 where id = ?2",nativeQuery = true)
    int updateStatusById(@NonNull String status,@NonNull Long id);
    Page<PhoneNumberEntity> findByCustomer_Id(@NonNull Long id, Pageable pageable);
   // List<PhoneNumberEntity> findByCustomer_Id(@NonNull Long id);


}