package br.com.victorhugo.supportdesk.repository;

import br.com.victorhugo.supportdesk.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
