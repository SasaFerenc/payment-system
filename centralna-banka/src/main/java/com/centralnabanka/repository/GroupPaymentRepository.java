package com.centralnabanka.repository;

import com.centralnabanka.model.GroupPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPaymentRepository extends JpaRepository<GroupPayment, Long> {
    GroupPayment save(GroupPayment payment);
}
