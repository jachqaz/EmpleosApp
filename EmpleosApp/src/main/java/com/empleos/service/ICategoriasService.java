package com.empleos.service;

import com.empleos.model.Categoria;

import java.util.List;

public interface ICategoriasService {
    void guardar(Categoria categoria);

    List<Categoria> buscarTodas();

    Categoria buscarPorId(Integer idCategoria);
}

/**
 * TODO: 1. Crear la clase CategoriasServiceImpl que implemente esta Interfaz (Guardar las categorías en una lista (LinkedList))
 * <p>
 * TODO: 2. Inyectar la clase de servicio en CategoriasController.
 * <p>
 * TODO: 3. Completar los siguientes métodos en CategoriasController:
 * <p>
 * mostrarIndex -> Renderizar el listado de Categorias (listCategorias.html)
 * Configurar la URL del botón para crear una Categoría
 * <p>
 * guardar -> Guardar el objeto Categoria a traves de la clase de servicio
 * Validar errores de Data Binding y mostrarlos al usuario en caso de haber
 * Mostrar al usuario mensaje de confirmacion de registro guardado
 * <p>
 * TODO: 4. Agregar un nuevo menu llamado Categorias y configurar la URL al listado de Categorias
 */
