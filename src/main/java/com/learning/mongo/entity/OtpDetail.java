package com.learning.mongo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "Otp")
@Data
@Builder
public class OtpDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_seq")
    @SequenceGenerator(name = "otp_seq", sequenceName = "otp_sequence", allocationSize = 1)
    private Long serial_no;
    private String email;
    private Long otp;
    private String transactionId;
    private Long mobileNo;
}