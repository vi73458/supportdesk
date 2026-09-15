package br.com.victorhugo.supportdesk.controller;

import br.com.victorhugo.supportdesk.domain.Chamado;
import br.com.victorhugo.supportdesk.domain.StatusChamado;
import br.com.victorhugo.supportdesk.dto.ChamadoRequest;
import br.com.victorhugo.supportdesk.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/chamados")
@CrossOrigin(origins = "http://localhost:5173")
public class ChamadoController {
    private final ChamadoService service;
    public ChamadoController(ChamadoService service) { this.service = service; }

    @GetMapping public List<Chamado> listar() { return service.listar(); }
    @GetMapping("/{id}") public Chamado buscar(@PathVariable Long id) { return service.buscar(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Chamado criar(@Valid @RequestBody ChamadoRequest request) { return service.criar(request); }
    @PatchMapping("/{id}/status")
    public Chamado atualizarStatus(@PathVariable Long id, @RequestParam StatusChamado status) { return service.atualizarStatus(id, status); }
}
