package com.project.credit_control.repository;

import com.project.credit_control.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {

}
