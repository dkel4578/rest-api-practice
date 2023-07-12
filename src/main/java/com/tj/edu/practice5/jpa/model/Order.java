package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name="orderList")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;
    private int guestNo;
    private String userId;
    private int productNo;
    @Column(updatable = false)
    private LocalDateTime oCdatetime;
    @Column(insertable = false)
    private LocalDateTime oUdatetime;
    private int oCnt;
    private int oAddrNo;
    private String oAddr1;
    private String oAddr2;
    private String oPhone;
    private String oMessage;
    private String oPurchase;
    private int deliveryNo;
    private int srcImgNo;
}
