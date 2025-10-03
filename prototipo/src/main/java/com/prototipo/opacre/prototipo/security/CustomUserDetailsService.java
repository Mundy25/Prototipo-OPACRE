package com.prototipo.opacre.prototipo.security;

import com.prototipo.opacre.prototipo.clientes.Cliente;
import com.prototipo.opacre.prototipo.repositorio.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService  {

    @Autowired
    private RepositorioCliente clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar cliente por username
        Cliente cliente = clienteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Verificar si está activo
        if (!cliente.isActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        // Convertir roles a authorities
        return User.builder()
                .username(cliente.getUsername())
                .password(cliente.getPassword())
                .authorities(cliente.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList()))
                .build();
    }

    // Método auxiliar para obtener el cliente completo
    public Cliente getClienteByUsername(String username) {
        return clienteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado: " + username));
    }

}
