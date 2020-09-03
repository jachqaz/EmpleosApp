package com.jpademo.repository;

import com.jpademo.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
    List<Vacante> findByEstatus(String estatus);

    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
}
