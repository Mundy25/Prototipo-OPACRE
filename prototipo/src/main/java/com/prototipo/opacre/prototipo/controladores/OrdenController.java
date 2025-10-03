//package com.prototipo.opacre.prototipo.controladores;
//
//import com.prototipo.opacre.prototipo.clientes.Orden;
//import com.prototipo.opacre.prototipo.servicios.OrdenServicio;
//import com.prototipo.opacre.prototipo.servicios.OrdenServicioImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping ("/api/orden")
//
//public class OrdenController {
//    @Autowired
//    private OrdenServicio ordenServicio;
//
//    @GetMapping
//    public List<Orden> findAll(){return ordenServicio.findAll();}
//
//}


package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Cliente;
import com.prototipo.opacre.prototipo.repositorio.RepositorioCliente;
import com.prototipo.opacre.prototipo.clientes.Orden;
import com.prototipo.opacre.prototipo.repositorio.RepositorioCliente;
import com.prototipo.opacre.prototipo.servicios.OrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orden")
public class OrdenController {

    @Autowired
    private OrdenServicio ordenServicio;

    @Autowired
    private RepositorioCliente clienteRepository;

    // Obtener todas las órdenes (PROTEGIDO - requiere JWT)
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Orden> ordenes = ordenServicio.findAll();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener órdenes: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Obtener mis órdenes (del cliente autenticado)
    @GetMapping("/mis-ordenes")
    public ResponseEntity<?> obtenerMisOrdenes() {
        try {
            // Obtener el usuario autenticado desde el contexto de seguridad
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Buscar cliente
            Cliente cliente = clienteRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            // Buscar órdenes del cliente (necesitas agregar este método en el servicio)
            List<Orden> misOrdenes = ordenServicio.findByClienteId(cliente.getId());

            return ResponseEntity.ok(misOrdenes);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener tus órdenes: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Crear nueva orden (PROTEGIDO - requiere JWT)
    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody Orden orden) {
        try {
            // Obtener el usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Buscar el cliente autenticado
            Cliente cliente = clienteRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            // Asignar información del cliente a la orden automáticamente
            orden.setClienteId(cliente.getId());
            orden.setClienteUsername(cliente.getUsername());
            orden.setClienteNombre(cliente.getNombre());
            orden.setClienteEmail(cliente.getEmail());
            orden.setClienteTelefono(cliente.getTelefono());
            orden.setFechaCreacion(LocalDateTime.now());
            orden.setEstado("pendiente");

            // Calcular total si los productos tienen precio
//            if (orden.getProducto() != null && !orden.getProducto().isEmpty()) {
//                double total = orden.getProducto().stream()
//                        .mapToDouble(p -> p.getPrecio() * p.getCantidad())
//                        .sum();
//                orden.setTotal(total);
//            }

            // Calcular total si los productos tienen precio
            if (orden.getProducto() != null && !orden.getProducto().isEmpty()) {
                double total = orden.getProducto().stream()
                        .mapToDouble(p -> p.getPrecio() * (double) p.getCantidad())
                        .sum();
                orden.setTotal(total);
            }

            // Guardar la orden
            Orden ordenGuardada = ordenServicio.save(orden);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Orden creada exitosamente");
            response.put("orden", ordenGuardada);
            response.put("cliente", cliente.getNombre());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear orden: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Obtener orden por ID (solo si pertenece al cliente autenticado)
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerOrdenPorId(@PathVariable String id) {
        try {
            Orden orden = ordenServicio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

            // Verificar que la orden pertenece al usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            if (!orden.getClienteUsername().equals(username)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No tienes permiso para ver esta orden");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }

            return ResponseEntity.ok(orden);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener orden: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Actualizar estado de orden
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            String nuevoEstado = body.get("estado");

            Orden orden = ordenServicio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

            // Verificar que la orden pertenece al usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            if (!orden.getClienteUsername().equals(username)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No tienes permiso para modificar esta orden");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }

            orden.setEstado(nuevoEstado);
            orden.setFechaActualizacion(LocalDateTime.now());
            Orden ordenActualizada = ordenServicio.save(orden);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Estado actualizado exitosamente");
            response.put("orden", ordenActualizada);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar estado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Cancelar orden
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarOrden(@PathVariable String id) {
        try {
            Orden orden = ordenServicio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

            // Verificar que la orden pertenece al usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            if (!orden.getClienteUsername().equals(username)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No tienes permiso para cancelar esta orden");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }

            orden.setEstado("cancelada");
            orden.setFechaActualizacion(LocalDateTime.now());
            ordenServicio.save(orden);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Orden cancelada exitosamente");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al cancelar orden: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
