package com.jpademo;

import com.jpademo.model.Categoria;
import com.jpademo.model.Perfil;
import com.jpademo.model.Usuario;
import com.jpademo.model.Vacante;
import com.jpademo.repository.CategoriasRepository;
import com.jpademo.repository.PerfilesRepository;
import com.jpademo.repository.UsuariosRepository;
import com.jpademo.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

    @Autowired
    private CategoriasRepository repoCategorias;

    @Autowired
    private VacantesRepository repoVacantes;

    @Autowired
    private PerfilesRepository repoPerfiles;

    @Autowired
    private UsuariosRepository repoUsuarios;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		System.out.println("Ejemplos de Spring Data JPA");
//		eliminarTodos();
//		borrarTodosEnBloque();
        conteo();
        guardar();
        buscarPorId();
        modificar();
        encontrarPorIds();
        buscarTodos();
        existeId();
        guardarTodas();
        buscarTodosJpa();
        buscarTodosOrdenados();
        buscarTodosPaginacion();
        buscarVacantes();
        guardarVacante();
        crearPerfilesAplicacion();
//        crearUsuarioConDosPerfiles();
        buscarUsuario();
        buscarVacantesPorEstatus();
        buscarVacantesPorDestacadoEstatus();
        buscarVacantesSalario();
        buscarVacantesVariosEstados();
//		eliminar();
//		System.out.println(repo);
    }

    private void guardar() {
//		System.out.println("Insertando un registro");
        Categoria cat = new Categoria();
        cat.setNombre("Finanzas");
        cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
        repoCategorias.save(cat);
        System.out.println(cat);
    }

    private void buscarPorId() {
        Optional<Categoria> optional = repoCategorias.findById(8);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("Categoria no encontrada");
        }
    }

    private void modificar() {
        Optional<Categoria> optional = repoCategorias.findById(8);
        if (optional.isPresent()) {
            Categoria catTmp = optional.get();
            catTmp.setNombre("Ingenieria de software");
            catTmp.setDescripcion("Desarrollo de Sistemas");
            repoCategorias.save(catTmp);
            System.out.println(catTmp);
        } else {
            System.out.println("Categoria no encontrada");
        }
    }

    private void eliminar() {
        int idCategoria = 1;
        repoCategorias.deleteById(idCategoria);
    }

    private void conteo() {
        long count = repoCategorias.count();
        System.out.println("Total Catergorias " + count);
    }

    private void eliminarTodos() {
        repoCategorias.deleteAll();
    }

    private void encontrarPorIds() {
        List<Integer> ids = new LinkedList<>();
        ids.add(8);
        ids.add(9);
        Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
        categorias.forEach(System.out::println);
    }

    private void buscarTodos() {
        Iterable<Categoria> categorias = repoCategorias.findAll();
        categorias.forEach(System.out::println);
    }

    private void existeId() {
        boolean existe = repoCategorias.existsById(14);
        System.out.println("La categoria existe: " + existe);
    }

    private void guardarTodas() {
        List<Categoria> lista = new LinkedList<>();
        Categoria catTmp = new Categoria();
        catTmp.setNombre("Ingenieria de software");
        catTmp.setDescripcion("Desarrollo de Sistemas");
        lista.add(catTmp);
        lista.add(catTmp);
        lista.add(catTmp);
        repoCategorias.saveAll(lista);
    }

    private void buscarTodosJpa() {
        List<Categoria> categorias = repoCategorias.findAll();
        categorias.forEach(System.out::println);
    }

    private void borrarTodosEnBloque() {
        repoCategorias.deleteAllInBatch();
    }

    private void buscarTodosOrdenados() {
        List<Categoria> categorias = repoCategorias.findAll(Sort.by("id").descending());
        categorias.forEach(System.out::println);
    }

    private void buscarTodosPaginacion() {
        Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5));
        System.out.println("Total Registros: " + page.getTotalElements());
        System.out.println("Total Paginas: " + page.getTotalPages());
        page.getContent().forEach(categoria -> System.out.println(categoria.getId() + " " + categoria.getNombre()));
    }

    //	private void buscarTodosPaginacionOrdenados(){
//		Page<Categoria> page=repo.findAll(PageRequest.of(0,5),Sort.by("id"));
//        System.out.println("Total Registros: "+page.getTotalElements());
//        System.out.println("Total Paginas: "+page.getTotalPages());
//		page.getContent().forEach(categoria -> System.out.println(categoria.getId()+" "+categoria.getNombre()));
//	}
    private void buscarVacantes() {
        List<Vacante> lista = repoVacantes.findAll();
        lista.forEach(vacante -> System.out.println(vacante.getId() + " " + vacante.getNombre() + "-> " + vacante.getCategoria().getNombre()));
    }

    private void guardarVacante() {
        Vacante vacante = new Vacante();
        vacante.setNombre("Profesor de Matematicas");
        vacante.setDescripcion("Sin descripcion ");
        vacante.setFecha(new Date());
        vacante.setSalario(8500.0);
        vacante.setEstatus("Aprobada");
        vacante.setDestacado(0);
        vacante.setImagen("escuela.png");
        vacante.setDetalles("Sin detalles");
        vacante.setCategoria(repoCategorias.getOne(1));
        repoVacantes.save(vacante);
    }

    private void crearPerfilesAplicacion() {
        repoPerfiles.saveAll(getPerfilesAplicacion());
    }

    private void crearUsuarioConDosPerfiles() {
        Usuario user = new Usuario();
        user.setNombre("Ivan Tinajero");
        user.setEmail("ivanetinajero@gmail.com");
        user.setFechaRegistro(new Date());
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEstatus(1);

        Perfil per1 = new Perfil();
        per1.setId(2);

        Perfil per2 = new Perfil();
        per2.setId(3);

        user.agregar(per1);
        user.agregar(per2);

        repoUsuarios.save(user);
    }

    private void buscarUsuario() {
        Optional<Usuario> optional = repoUsuarios.findById(1);
        if (optional.isPresent()) {
            Usuario u = optional.get();
            System.out.println("Usuario: " + u.getNombre());
            System.out.println("Perfiles asignados");
            u.getPerfiles().forEach(perfil -> System.out.println(perfil.getPerfil()));
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    private void buscarVacantesPorEstatus() {
        List<Vacante> lista = repoVacantes.findByEstatus("Eliminada");
        System.out.println("Registros encontrados: " + lista.size());
        lista.forEach(vacante -> System.out.println(vacante.getId() + ": " + vacante.getNombre() + ": " + vacante.getEstatus()));
    }

    private void buscarVacantesPorDestacadoEstatus() {
        List<Vacante> lista = repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
        System.out.println("Registros encontrados: " + lista.size());
        lista.forEach(vacante -> System.out.println(vacante.getId() + ": " + vacante.getNombre() + ": " + vacante.getEstatus() + ": " + vacante.getDestacado()));
    }

    private void buscarVacantesSalario() {
        List<Vacante> lista = repoVacantes.findBySalarioBetweenOrderBySalarioDesc(7000, 14000);
        System.out.println("Registros encontrados: " + lista.size());
        lista.forEach(vacante -> System.out.println(vacante.getId() + ": " + vacante.getNombre() + ": " + vacante.getSalario()));
    }

    private void buscarVacantesVariosEstados() {
        String[] estatusStrings = new String[]{"Eliminada", "Creada"};
        List<Vacante> lista = repoVacantes.findByEstatusIn(estatusStrings);
        System.out.println("Registros encontrados: " + lista.size());
        lista.forEach(vacante -> System.out.println(vacante.getId() + ": " + vacante.getNombre() + ": " + vacante.getEstatus()));

    }

    private List<Perfil> getPerfilesAplicacion() {
        List<Perfil> lista = new LinkedList<Perfil>();
        Perfil per1 = new Perfil();
        per1.setPerfil("SUPERVISOR");

        Perfil per2 = new Perfil();
        per2.setPerfil("ADMINISTRADOR");

        Perfil per3 = new Perfil();
        per3.setPerfil("USUARIO");

        lista.add(per1);
        lista.add(per2);
        lista.add(per3);

        return lista;
    }
}
