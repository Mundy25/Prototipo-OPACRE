package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Cliente;
import com.prototipo.opacre.prototipo.repositorio.RepositorioCliente;
import com.prototipo.opacre.prototipo.dto.AuthResponse;
import com.prototipo.opacre.prototipo.dto.LoginRequest;
import com.prototipo.opacre.prototipo.dto.RegisterRequest;
import com.prototipo.opacre.prototipo.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private RepositorioCliente clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    // Endpoint de registro
    @PostMapping("/registro")
    public ResponseEntity<?> registrarCliente(@RequestBody RegisterRequest request) {
        try {
            // Validar que el username no exista
            if (clienteRepository.existsByUsername(request.getUsername())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new AuthResponse("El username ya existe"));
            }

            // Crear nuevo cliente
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre(request.getNombre());
            nuevoCliente.setDireccion(request.getDireccion());
            nuevoCliente.setEmail(request.getEmail());
            nuevoCliente.setTelefono(request.getTelefono());
            nuevoCliente.setUsername(request.getUsername());

            // Hashear la contraseña
            nuevoCliente.setPassword(passwordEncoder.encode(request.getPassword()));

            // Guardar cliente
            Cliente clienteGuardado = clienteRepository.save(nuevoCliente);

            // Generar token
            UserDetails userDetails = userDetailsService.loadUserByUsername(clienteGuardado.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            // Retornar respuesta con token
            AuthResponse response = new AuthResponse(
                    token,
                    clienteGuardado.getUsername(),
                    clienteGuardado.getNombre()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Error al registrar cliente: " + e.getMessage()));
        }
    }

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Autenticar usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Cargar detalles del usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            // Obtener cliente
            Cliente cliente = clienteRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            // Generar token
            String token = jwtUtil.generateToken(userDetails);

            // Retornar respuesta con token
            AuthResponse response = new AuthResponse(
                    token,
                    cliente.getUsername(),
                    cliente.getNombre()
            );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Credenciales incorrectas"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Error al iniciar sesión: " + e.getMessage()));
        }
    }

    // Endpoint para verificar token (opcional)
    @GetMapping("/verificar")
    public ResponseEntity<?> verificarToken(@RequestHeader("Authorization") String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            String username = jwtUtil.extractUsername(token);

            if (jwtUtil.validateToken(token)) {
                return ResponseEntity.ok(new AuthResponse("Token válido para: " + username));
            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthResponse("Token inválido"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Token inválido"));
        }
    }
}
