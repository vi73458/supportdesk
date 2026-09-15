package br.com.victorhugo.supportdesk.repository;

import br.com.victorhugo.supportdesk.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
