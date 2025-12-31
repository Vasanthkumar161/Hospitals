package com.Hospitals.Hospitals.Cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Hospitals.Hospitals.entity.Patient;
import service.PatientService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    // ================= Registration form =================
    @GetMapping("/patient_registration")
    public String patientForm(@RequestParam(value="doctor", required=false) String doctor, Model model) {
        Patient patient = new Patient();
        if (doctor != null) {
            patient.setDoctorName(doctor); // pre-fill doctor name
        }
        model.addAttribute("patient", patient);
        return "patient_registration";
    }

    // ================= Save patient =================
    @PostMapping("/patient_registration")
    public String savePatient(@ModelAttribute Patient patient) {
        Patient saved = patientService.createPendingPatient(patient);
        return "redirect:/patient_success?id=" + saved.getId();
    }

    // ================= Success page =================
    @GetMapping("/patient_success")
    public String successPage(@RequestParam("id") Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient_success";
    }

    // ================= Patient List page =================
    @GetMapping("/patients")
    public String listPatients(@RequestParam(value="name", required=false) String name, Model model) {

        List<Patient> patients;

        if (name != null && !name.isEmpty()) {
            patients = patientService.searchByName(name);
            model.addAttribute("searchName", name);
        } else {
            patients = patientService.getAllPatients();
        }

        if (patients == null) {
            patients = new ArrayList<>();
        }

        // Handle null amount safely
        double totalAmount = patients.stream()
                .mapToDouble(p -> p.getAmount() != null ? p.getAmount() : 0)
                .sum();

        model.addAttribute("patients", patients);
        model.addAttribute("totalAmount", totalAmount);

        return "patients_list";
    }
}

