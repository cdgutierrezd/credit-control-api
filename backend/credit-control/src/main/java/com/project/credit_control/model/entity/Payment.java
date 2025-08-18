package com.project.credit_control.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false) //Many payments can be associated with a single client.
    @JoinColumn(name = "client_id" )
    private Client client;

    private BigDecimal amount;

    private LocalDateTime date;
}
