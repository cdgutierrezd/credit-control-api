package com.project.credit_control.repository;

import com.project.credit_control.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.client.id = :clientId")
    Optional<BigDecimal> sumAmountByClientId(@Param("clientId") Integer clientId);
}
