package com.empleos.controller;

import com.empleos.model.Vacante;
import com.empleos.service.ICategoriasService;
import com.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
    @Autowired
    private IVacanteService serviceVacantes;

    @Autowired
    private ICategoriasService servicesCategorias;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), false));
    }

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
    public String crear(Vacante vacante, Model model) {
        model.addAttribute("categorias", servicesCategorias.buscarTodas());
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String Guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error" + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        serviceVacantes.guardar(vacante);
        attributes.addFlashAttribute("msg","Registro Guardado");
        System.out.println("Nombre" + vacante);
        return "redirect:/vacantes/index";
    }

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Vacante> lista = serviceVacantes.buscarTodas();
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }
}
