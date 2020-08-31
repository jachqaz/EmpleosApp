package com.empleos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarHome(Model model) {
//        model.addAttribute("mensaje", "Binvenidos a Empleos");
//        model.addAttribute("fecha", new Date());
        String nombre = "Auxiliar de Contabilidad";
        Date fechaPub = new Date();
        double salario = 9000.0;
        boolean vigente = true;
        model.addAttribute("nombre", nombre);
        model.addAttribute("fechaPub", fechaPub);
        model.addAttribute("salario", salario);
        model.addAttribute("vigente", vigente);
        return "home";
    }

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<String> lista = new LinkedList<>();
        lista.add("Ingeniero de SIstemas");
        lista.add("Auxiliar de Contabilidad");
        lista.add("Vendedor");
        lista.add("Arquitecto");
        model.addAttribute("empleos", lista);
        return "listado";
    }
}
