package br.com.victorhugo.supportdesk.repository;

import br.com.victorhugo.supportdesk.domain.Chamado;
import br.com.victorhugo.supportdesk.domain.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByStatus(StatusChamado status);
    long countByStatus(StatusChamado status);
}
