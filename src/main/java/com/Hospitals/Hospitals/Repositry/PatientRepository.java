package com.Hospitals.Hospitals.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Hospitals.Hospitals.entity.Patient;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByMerchantOrderId(String merchantOrderId);
    List<Patient> findByNameContainingIgnoreCase(String name);
}
