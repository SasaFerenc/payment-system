package com.centralnabanka.repository;

import com.centralnabanka.model.GroupPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupPaymentRepository extends JpaRepository<GroupPayment, Long> {

    List<GroupPayment> findBySettledFalse();

    GroupPayment save(GroupPayment payment);
}
