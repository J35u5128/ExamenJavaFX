package org.example.examenjavafx.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica Data Access Object (DAO).
 * Define las operaciones CRUD (Create, Read, Update, Delete) básicas
 * para cualquier entidad T.
 *
 * @param <T> La clase de la entidad (ej. Videojuego).
 */
public interface DAO<T> {
    /* Métodos de escritura */
    Optional<T> save(T t);
    Optional<T> update(T t);
    Optional<T> delete(T t);

    /* Métodos de lectura */
    List<T> findAll();
    Optional<T> findById(Integer id);
}