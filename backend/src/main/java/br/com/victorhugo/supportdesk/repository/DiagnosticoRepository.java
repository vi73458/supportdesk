package br.com.victorhugo.supportdesk.repository;

import br.com.victorhugo.supportdesk.domain.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
    List<Diagnostico> findByChamadoIdOrderByCriadoEmDesc(Long chamadoId);
}
