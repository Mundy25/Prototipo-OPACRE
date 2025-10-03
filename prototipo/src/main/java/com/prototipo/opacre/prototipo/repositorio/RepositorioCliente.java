package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorioCliente extends MongoRepository<Cliente,String> {
    Optional<Cliente> findByUsername(String username);
    boolean existsByUsername(String username);

}
