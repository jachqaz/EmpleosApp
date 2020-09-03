package com.jpademo;

import com.jpademo.model.Categoria;
import com.jpademo.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Ejemplos de Spring Data JPA");
//		eliminarTodos();
		conteo();
		guardar();
		buscarPorId();
		modificar();
		encontrarPorIds();
		buscarTodos();
//		eliminar();
//		System.out.println(repo);
	}

	private void guardar() {
//		System.out.println("Insertando un registro");
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		repo.save(cat);
		System.out.println(cat);
	}

	private void buscarPorId() {
		Optional<Categoria> optional = repo.findById(8);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}

	private void modificar() {
		Optional<Categoria> optional = repo.findById(8);
		if (optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ingenieria de software");
			catTmp.setDescripcion("Desarrollo de Sistemas");
			repo.save(catTmp);
			System.out.println(catTmp);
		} else {
			System.out.println("Categoria no encontrada");
		}
	}

	private void eliminar() {
		int idCategoria = 1;
		repo.deleteById(idCategoria);
	}

	private void conteo() {
		long count = repo.count();
		System.out.println("Total Catergorias " + count);
	}

	private void eliminarTodos() {
		repo.deleteAll();
	}

	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<>();
		ids.add(8);
		ids.add(9);
		Iterable<Categoria> categorias = repo.findAllById(ids);
		categorias.forEach(System.out::println);
	}

	private void buscarTodos() {
		Iterable<Categoria> categorias = repo.findAll();
		categorias.forEach(System.out::println);
	}
}
