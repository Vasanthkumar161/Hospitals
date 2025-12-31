package com.Hospitals.Hospitals.Cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HospitalController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/doctors")
    public String doctors(Model model) {
        // three doctors; pass to view
        model.addAttribute("doctor1", "Dr. Vasanth Chowdary");
        model.addAttribute("doctor2", "Dr. Hemanth Reddy");
        model.addAttribute("doctor3", "Dr. Chandana Chowdary");
        return "doctors";
    }
}

