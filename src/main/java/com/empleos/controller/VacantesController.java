package com.empleos.controller;

import com.empleos.model.Vacante;
import com.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
    @Autowired
    private IVacanteService serviceVacantes;

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
//        System.out.println("IdVacante" + idVacante);
//        model.addAttribute("idVacante", idVacante);
        System.out.println("Vacante " + vacante);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int idVacante, Model model) {
        System.out.println("Borrando vacante con id: " + idVacante);
        model.addAttribute("id", idVacante);
        return "mensaje";
    }

    @GetMapping("/create")
    public String crear() {
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String Guardar(Vacante vacante) {
        System.out.println("Nombre" + vacante);
        return "vacantes/lisTvacantes";
    }
}
