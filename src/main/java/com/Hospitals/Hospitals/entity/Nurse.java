package com.Hospitals.Hospitals.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String qualification;
    private String gender;
    private String shift;
    private String status; // WORKING / NOT_WORKING
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Nurse() {}

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
}

