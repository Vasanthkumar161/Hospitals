package com.Hospitals.Hospitals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Hospitals.Hospitals", "service"})
public class HospitalsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalsApplication.class, args);
    }
}
        