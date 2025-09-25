//package com.prototipo.opacre.prototipo.controladores;
//
//import com.prototipo.opacre.prototipo.clientes.Cliente;
//import com.prototipo.opacre.prototipo.servicios.ClienteServicio;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.ui.Model;
//
//import java.util.List;
//
//@Controller
//public class ClienteVistaController {
//    private final ClienteServicio clienteServicio;
//
//    public ClienteVistaController(ClienteServicio clienteServicio) {
//        this.clienteServicio = clienteServicio;
//    }
//
//    @GetMapping("/api/clientes/tabla")  // ← localhost:8080/api/clientes/tabla
//    public String mostrarTabla(Model model) {
//        List<Cliente> clientes = clienteServicio.findAll();
//        model.addAttribute("clientes", clientes);
//        return "clientes"; // Carga templates/clientes.html
//    }
//}
