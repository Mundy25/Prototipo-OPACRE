package com.prototipo.opacre.prototipo.controladores;
import com.prototipo.opacre.prototipo.servicios.ClienteServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {
    private final ClienteServicio clienteServicio;

    public PaginasController(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("/")                // localhost:8080
    public String home() {
        return "index";             // busca templates/index.html
    }

    @GetMapping("/api/clientes/tabla") // localhost:8080/api/clientes/tabla
    public String clientes(Model model) {
        model.addAttribute("clientes", clienteServicio.findAll());
        return "clientes";          // busca templates/clientes.html
    }
}
