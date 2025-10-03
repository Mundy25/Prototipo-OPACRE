package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RepositorioFactura  extends MongoRepository<Factura,String> {
    List<Factura> findByClienteId(String clienteId);
    List<Factura> findByClienteUsername(String clienteUsername);
    List<Factura> findByEstado(String estado);

}

