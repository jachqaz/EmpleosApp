package com.empleos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarHome(Model model) {
        model.addAttribute("mensaje", "Binvenidos a Empleos");
        model.addAttribute("fecha", new Date());
        return "home";
    }
}
