package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioCliente extends MongoRepository<Cliente,String> {


}
