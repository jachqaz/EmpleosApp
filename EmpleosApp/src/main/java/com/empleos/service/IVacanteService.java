package com.empleos.service;

import com.empleos.model.Vacante;

import java.util.List;

public interface IVacanteService {
    List<Vacante> buscarTodas();

    Vacante buscarPorId(Integer idVacante);

    void guardar(Vacante vacante);
}
