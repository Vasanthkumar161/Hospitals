package com.Hospitals.Hospitals.Cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.Hospitals.Hospitals.entity.Patient;
import service.PatientService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiPaymentController {

    @Autowired
    private PatientService service;

    @PostMapping("/patient/initiate")
    public ResponseEntity<?> initiate(@RequestBody Patient incoming) {
        Patient saved = service.createPendingPatient(incoming);

        String payeeVpa = "yourvpa@upi";  // replace
        String payeeName = "Vasanth Hospital";
        String amount = String.valueOf(saved.getAmount());
        String txNote = "TokenPayment:" + saved.getId();

        String upiLink = String.format("upi://pay?pa=%s&pn=%s&tn=%s&am=%s&cu=INR",
                payeeVpa, payeeName.replace(" ", "+"), txNote.replace(" ", "+"), amount);

        return ResponseEntity.ok(Map.of(
                "merchantOrderId", saved.getMerchantOrderId(),
                "upiLink", upiLink,
                "patientId", saved.getId()
        ));
    }

    @GetMapping("/patient/{merchantOrderId}/status")
    public ResponseEntity<?> checkStatus(@PathVariable String merchantOrderId) {
        Optional<Patient> opt = service.findByMerchant(merchantOrderId);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Patient p = opt.get();
        return ResponseEntity.ok(Map.of("status", p.getPaymentStatus(), "patientId", p.getId()));
    }

    @PostMapping("/patient/{merchantOrderId}/mark-success")
    public ResponseEntity<?> markSuccess(@PathVariable String merchantOrderId) {
        Patient updated = service.updatePaymentStatus(merchantOrderId, "SUCCESS");
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("ok", true, "patientId", updated.getId()));
    }

    @PostMapping("/patient/{merchantOrderId}/mark-failed")
    public ResponseEntity<?> markFailed(@PathVariable String merchantOrderId) {
        Patient updated = service.updatePaymentStatus(merchantOrderId, "FAILED");
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("ok", true));
    }
}
