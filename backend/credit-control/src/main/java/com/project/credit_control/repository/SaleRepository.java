package com.project.credit_control.repository;

import com.project.credit_control.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale,Integer> {
    @Query("SELECT SUM(s.amount) FROM Sale s WHERE s.client.id = :clientId")
    Optional<BigDecimal> sumAmountByClientId(@Param("clientId") Integer clientId);
}
