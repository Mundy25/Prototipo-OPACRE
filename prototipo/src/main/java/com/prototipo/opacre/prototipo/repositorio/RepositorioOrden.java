package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioOrden  extends MongoRepository<Orden,String> {
    List<Orden> findByClienteId(String clienteId);
    List<Orden> findByClienteUsername(String clienteUsername);
    List<Orden> findByEstado(String estado);
}
