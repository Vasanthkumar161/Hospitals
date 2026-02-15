package com.Hospitals.Hospitals.Cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Hospitals.Hospitals.Repositry.NurseRepository;
import com.Hospitals.Hospitals.entity.Nurse;
import java.util.Optional;

@Controller
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseRepository repo;

    // 1️⃣ List all nurses
    @GetMapping
    public String list(Model model){
        model.addAttribute("nurses", repo.findAll());
        return "nurses";
    }

    // 2️⃣ Show nurse attendance
    @GetMapping("/attendance")
    public String attendance(Model model){
        model.addAttribute("nurses", repo.findAll());
        return "attendance";
    }

    // 3️⃣ Show Add Nurse form
    @GetMapping("/add")
    public String addNurse(Model model){
        model.addAttribute("nurse", new Nurse());
        return "nurse_edit";   // This is your nurse_edit.html
    }

    // 4️⃣ Show Edit Nurse form
    @GetMapping("/edit/{id}")
    public String editNurse(@PathVariable Long id, Model model){
        Optional<Nurse> optionalNurse = repo.findById(id);
        if(optionalNurse.isPresent()){
            model.addAttribute("nurse", optionalNurse.get());
        } else {
            model.addAttribute("nurse", new Nurse()); // fallback if not found
        }
        return "nurse_edit";
    }

    // 5️⃣ Save Nurse (both Add & Edit)
    @PostMapping("/save")
    public String saveNurse(@ModelAttribute Nurse nurse){
        // Set entry/exit time if status is WORKING/NOT_WORKING
        if("WORKING".equals(nurse.getStatus())){
            nurse.setEntryTime(java.time.LocalDateTime.now());
        } else if("NOT_WORKING".equals(nurse.getStatus())){
            nurse.setExitTime(java.time.LocalDateTime.now());
        }

        repo.save(nurse);
        return "redirect:/nurses";
    }
}

