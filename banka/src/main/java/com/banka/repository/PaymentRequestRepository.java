package com.banka.repository;

import com.banka.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {

    PaymentRequest getOne(Long id);
    PaymentRequest save(PaymentRequest paymentRequest);
    @Query("select pr from PaymentRequest pr where pr.debtorAccountNumber like ?1%")
    List<PaymentRequest> findByBankCode(String bankCode);
    List<PaymentRequest> findByIdMT(String idMt);

}
