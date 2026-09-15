package br.com.victorhugo.supportdesk.repository;

import br.com.victorhugo.supportdesk.entity.Chamado;
import br.com.victorhugo.supportdesk.enums.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByStatus(StatusChamado status);
    long countByStatus(StatusChamado status);
    @Query("select count(c) from Chamado c where c.status not in ('RESOLVIDO','ENCERRADO')")
    long countAbertos();
}
