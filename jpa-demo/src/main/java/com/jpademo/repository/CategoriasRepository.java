package com.jpademo.repository;

import com.jpademo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
}
