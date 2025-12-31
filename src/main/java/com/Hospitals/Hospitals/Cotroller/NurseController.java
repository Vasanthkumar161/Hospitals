package com.Hospitals.Hospitals.Cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Hospitals.Hospitals.Repositry.NurseRepository;
import com.Hospitals.Hospitals.entity.Nurse;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("nurses", repo.findAll());
        return "nurses";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "nurse_edit";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", repo.findById(id).orElse(new Nurse()));
        return "nurse_edit";
    }

    @PostMapping("/save")
    public String save(Nurse nurse) {

        if ("WORKING".equals(nurse.getStatus())) {
            nurse.setEntryTime(LocalDateTime.now());
            nurse.setExitTime(null);
        } else {
            nurse.setExitTime(LocalDateTime.now());
        }

        repo.save(nurse);
        return "redirect:/nurses";
    }

    // ----------------------------
    // 👉 NEW ENDPOINT FOR ATTENDANCE
    // ----------------------------
    @GetMapping("/attendance")
    public String attendance(Model model) {
        model.addAttribute("nurses", repo.findAll());
        return "attendance";  // attendance.html
    }
}
