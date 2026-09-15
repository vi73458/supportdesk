package br.com.victorhugo.supportdesk.controller;

import br.com.victorhugo.supportdesk.domain.StatusChamado;
import br.com.victorhugo.supportdesk.repository.ChamadoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {
    private final ChamadoRepository repository;
    public DashboardController(ChamadoRepository repository){ this.repository = repository; }

    @GetMapping("/resumo")
    public Map<String, Long> resumo(){
        return Map.of(
            "total", repository.count(),
            "abertos", repository.countByStatus(StatusChamado.ABERTO),
            "emAnalise", repository.countByStatus(StatusChamado.EM_ANALISE),
            "emAtendimento", repository.countByStatus(StatusChamado.EM_ATENDIMENTO),
            "aguardandoUsuario", repository.countByStatus(StatusChamado.AGUARDANDO_USUARIO),
            "resolvidos", repository.countByStatus(StatusChamado.RESOLVIDO),
            "encerrados", repository.countByStatus(StatusChamado.ENCERRADO)
        );
    }
}
