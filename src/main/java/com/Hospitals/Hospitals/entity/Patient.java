package com.Hospitals.Hospitals.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String disease;
    private Integer age;
    private String gender;
    private String doctorName;

    private String paymentMethod; // PhonePe / UPI / Cash
    private String upiId;
    private Integer amount;
    private String paymentStatus; // PENDING / SUCCESS / FAILED

    @Column(unique = true)
    private String merchantOrderId;

    private LocalDateTime createdAt;

    public Patient() {}

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (amount == null) amount = 300;
        if (paymentStatus == null) paymentStatus = "PENDING";
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getUpiId() { return upiId; }
    public void setUpiId(String upiId) { this.upiId = upiId; }
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getMerchantOrderId() { return merchantOrderId; }
    public void setMerchantOrderId(String merchantOrderId) { this.merchantOrderId = merchantOrderId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
