package org.example.examenjavafx.dao;

import org.example.examenjavafx.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioDAO implements DAO<Usuario> {

    // Almacenamiento en memoria
    private static final List<Usuario> store = new ArrayList<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    static {
        // Datos de ejemplo: usuario administrador
        store.add(new Usuario(nextId.getAndIncrement(), "admin@demo.com", "web", "1.0", "2025-11-24", true));
        store.add(new Usuario(nextId.getAndIncrement(), "usuario@demo.com", "web", "1.0", "2025-11-24", false));
    }

    @Override
    public Optional<Usuario> save(Usuario usuario) {
        usuario.setId(nextId.getAndIncrement());
        store.add(usuario);
        return Optional.of(usuario);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario) {
        return findById(usuario.getId()).map(existing -> {
            existing.setEmail(usuario.getEmail());
            existing.setPlataforma(usuario.getPlataforma());
            existing.setVersion(usuario.getVersion());
            existing.setFecha(usuario.getFecha());
            existing.setIs_admin(usuario.getIs_admin());
            return existing;
        });
    }

    @Override
    public Optional<Usuario> delete(Usuario usuario) {
        boolean removed = store.removeIf(u -> u.getId().equals(usuario.getId()));
        return removed ? Optional.of(usuario) : Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return store.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}

