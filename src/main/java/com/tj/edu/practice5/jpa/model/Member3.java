package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Member3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String password;
    private String uName;
    private String partner;
    private String gender;
    private String uAddrNo;
    private String uAddr1;
    private String uAddr2;
    private String uPhone;
    private String uEmail;
    private String birth;
    private String weddingday;
    private String cdatetime;
    private String udatetime;
    private String status;
    private String bank;
    private String bankaccount;
}
