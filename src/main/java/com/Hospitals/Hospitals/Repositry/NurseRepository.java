package com.Hospitals.Hospitals.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Hospitals.Hospitals.entity.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long> {}
