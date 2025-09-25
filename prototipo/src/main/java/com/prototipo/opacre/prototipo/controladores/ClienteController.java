package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Cliente;
import com.prototipo.opacre.prototipo.servicios.ClienteServicio;
import com.prototipo.opacre.prototipo.servicios.ClienteServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cliente")


public class ClienteController {

    //@Autowired
    private final ClienteServicio clienteServicio;

    public ClienteController(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping
    public List<Cliente> findAll(){
        return clienteServicio.findAll();
    }


    @GetMapping("/tabla")
    public Map<String, Object> findAllTabular(){
        List<Cliente> clientes = clienteServicio.findAll();

        Map<String, Object> respuesta = new HashMap<>();

        // Definir las columnas de tu tabla
        //respuesta.put("columnas", List.of("ID", "Nombre", "Email", "Teléfono", "Dirección"));

        // Los datos (puedes usar directamente la lista de clientes)
        respuesta.put("datos", clientes);

        // Información adicional útil
        respuesta.put("total", clientes.size());

        return respuesta;
    }





    @PostMapping ("/registrar")
    public Cliente guardar (@RequestBody Cliente cliente){

        return clienteServicio.guardar(cliente);
    }




}
