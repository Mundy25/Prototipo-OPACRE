//package com.prototipo.opacre.prototipo.controladores;
//
//import com.prototipo.opacre.prototipo.clientes.Factura;
//import com.prototipo.opacre.prototipo.servicios.FacturaServicio;
//import com.prototipo.opacre.prototipo.servicios.FacturaServicioImp;
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
//@RequestMapping ("/api/factura")
//
//public class FacturaController {
//
//    @Autowired
//    private FacturaServicio facturaServicio;
//
//    @GetMapping
//    public List<Factura> findAll(){return facturaServicio.findAll();}
//
//}
package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Cliente;
import com.prototipo.opacre.prototipo.repositorio.RepositorioCliente;
import com.prototipo.opacre.prototipo.clientes.Factura;
import com.prototipo.opacre.prototipo.servicios.FacturaServicio;
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
@RequestMapping("/api/factura")
public class FacturaController {

    @Autowired
    private FacturaServicio facturaServicio;

    @Autowired
    private RepositorioCliente clienteRepository;

    // Obtener todas las facturas (PROTEGIDO - requiere JWT)
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Factura> facturas = facturaServicio.findAll();
            return ResponseEntity.ok(facturas);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener facturas: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Obtener mis facturas (del cliente autenticado)
    @GetMapping("/mis-facturas")
    public ResponseEntity<?> obtenerMisFacturas() {
        try {
            // Obtener el usuario autenticado desde el contexto de seguridad
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Buscar cliente
            Cliente cliente = clienteRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            // Buscar facturas del cliente (necesitas agregar este método en el servicio)
            List<Factura> misFacturas = facturaServicio.findByClienteId(cliente.getId());

            return ResponseEntity.ok(misFacturas);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener tus facturas: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Crear nueva factura (PROTEGIDO - requiere JWT)
    @PostMapping
    public ResponseEntity<?> crearFactura(@RequestBody Factura factura) {
        try {
            // Obtener el usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Buscar el cliente autenticado
            Cliente cliente = clienteRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            // Asignar información del cliente a la factura automáticamente
            factura.setClienteId(cliente.getId());
            factura.setClienteUsername(cliente.getUsername());
            factura.setClienteNombre(cliente.getNombre());
            factura.setFechaCreacion(LocalDateTime.now());
            factura.setEstado("pendiente");

            // Calcular total si los productos tienen precio
            if (factura.getProducto() != null && !factura.getProducto().isEmpty()) {
                double total = factura.getProducto().stream()
                        .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                        .sum();
                factura.setValor(String.valueOf(total));
            }

            // Guardar la factura
            Factura facturaGuardada = facturaServicio.save(factura);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Factura creada exitosamente");
            response.put("factura", facturaGuardada);
            response.put("cliente", cliente.getNombre());
            response.put("total", factura.getValor());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Obtener factura por ID (solo si pertenece al cliente autenticado)
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerFacturaPorId(@PathVariable String id) {
        try {
            Factura factura = facturaServicio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

            // Verificar que la factura pertenece al usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            if (!factura.getClienteUsername().equals(username)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No tienes permiso para ver esta factura");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }

            return ResponseEntity.ok(factura);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Actualizar estado de factura (pendiente, pagada, cancelada)
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            String nuevoEstado = body.get("estado");

            Factura factura = facturaServicio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

            // Verificar que la factura pertenece al usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            if (!factura.getClienteUsername().equals(username)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No tienes permiso para modificar esta factura");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }

            factura.setEstado(nuevoEstado);
            Factura facturaActualizada = facturaServicio.save(factura);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Estado de factura actualizado exitosamente");
            response.put("factura", facturaActualizada);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar estado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Marcar factura como pagada
    @PutMapping("/{id}/pagar")
    public ResponseEntity<?> marcarComoPagada(@PathVariable String id) {
        try {
            Factura factura = facturaServicio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

            // Verificar que la factura pertenece al usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            if (!factura.getClienteUsername().equals(username)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No tienes permiso para pagar esta factura");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }

            factura.setEstado("pagada");
            Factura facturaActualizada = facturaServicio.save(factura);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Factura pagada exitosamente");
            response.put("factura", facturaActualizada);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al procesar pago: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Cancelar factura
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarFactura(@PathVariable String id) {
        try {
            Factura factura = facturaServicio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

            // Verificar que la factura pertenece al usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            if (!factura.getClienteUsername().equals(username)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No tienes permiso para cancelar esta factura");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }

            // Solo se puede cancelar si está pendiente
            if (!"pendiente".equals(factura.getEstado())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Solo se pueden cancelar facturas pendientes");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            factura.setEstado("cancelada");
            facturaServicio.save(factura);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Factura cancelada exitosamente");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al cancelar factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}