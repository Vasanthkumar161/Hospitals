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

    @GetMapping("/patient_registration")
    public String patientForm(@RequestParam(value="doctor", required=false) String doctor, Model model){
        Patient p = new Patient();
        if(doctor!=null) p.setDoctorName(doctor);
        model.addAttribute("patient",p);
        return "patient_registration";
    }

    @PostMapping("/patient_registration")
    public String savePatient(@ModelAttribute Patient patient){
        Patient saved = patientService.createPendingPatient(patient);
        return "redirect:/patient_success?id="+saved.getId();
    }

    @GetMapping("/patient_success")
    public String successPage(@RequestParam("id") Long id, Model model){
        Patient p = patientService.getPatientById(id);
        model.addAttribute("patient",p);
        return "patient_success";
    }

    @GetMapping("/patients")
    public String listPatients(@RequestParam(value="name",required=false) String name, Model model){
        List<Patient> patients = (name==null || name.isEmpty())?patientService.getAllPatients():patientService.searchByName(name);
        model.addAttribute("patients", patients);
        double totalAmount = patients.stream().mapToDouble(p->p.getAmount()!=null?p.getAmount():0).sum();
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("searchName", name);
        return "patients_list";
    }
}
