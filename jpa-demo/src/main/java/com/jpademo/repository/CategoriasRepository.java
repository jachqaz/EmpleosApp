package com.jpademo.repository;

import com.jpademo.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
}
