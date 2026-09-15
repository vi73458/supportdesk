package br.com.victorhugo.supportdesk.controller;

import br.com.victorhugo.supportdesk.domain.Chamado;
import br.com.victorhugo.supportdesk.domain.Diagnostico;
import br.com.victorhugo.supportdesk.dto.DiagnosticoRequest;
import br.com.victorhugo.supportdesk.repository.ChamadoRepository;
import br.com.victorhugo.supportdesk.repository.DiagnosticoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados/{chamadoId}/diagnosticos")
@CrossOrigin(origins = "http://localhost:5173")
public class DiagnosticoController {
    private final DiagnosticoRepository diagnosticoRepository;
    private final ChamadoRepository chamadoRepository;

    public DiagnosticoController(DiagnosticoRepository diagnosticoRepository, ChamadoRepository chamadoRepository) {
        this.diagnosticoRepository = diagnosticoRepository;
        this.chamadoRepository = chamadoRepository;
    }

    @GetMapping
    public List<Diagnostico> listar(@PathVariable Long chamadoId) {
        return diagnosticoRepository.findByChamadoIdOrderByCriadoEmDesc(chamadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diagnostico criar(@PathVariable Long chamadoId, @Valid @RequestBody DiagnosticoRequest request) {
        Chamado chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
        return diagnosticoRepository.save(Diagnostico.builder()
                .chamado(chamado)
                .etapa(request.etapa())
                .resultado(request.resultado())
                .observacao(request.observacao())
                .build());
    }
}
