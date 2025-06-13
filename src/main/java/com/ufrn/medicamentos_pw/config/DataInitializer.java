package com.ufrn.medicamentos_pw.config;

import com.ufrn.medicamentos_pw.domain.DomainUsuario;
import com.ufrn.medicamentos_pw.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (usuarioRepository.count() == 0) {
            DomainUsuario admin = new DomainUsuario();
            admin.setNome("Administrador");
            admin.setUsername("admin");
            admin.setEmail("admin@exemplo.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");

            DomainUsuario user = new DomainUsuario();
            user.setNome("Usuário Normal");
            user.setUsername("user");
            user.setEmail("user@exemplo.com");
            user.setSenha(passwordEncoder.encode("user123"));
            user.setRole("ROLE_USER");

            usuarioRepository.save(admin);
            usuarioRepository.save(user);

            System.out.println("Usuários padrão criados!");
        }
    }
}
