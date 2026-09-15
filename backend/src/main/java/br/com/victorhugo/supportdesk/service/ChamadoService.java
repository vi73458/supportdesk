package br.com.victorhugo.supportdesk.service;

import br.com.victorhugo.supportdesk.domain.*;
import br.com.victorhugo.supportdesk.dto.ChamadoRequest;
import br.com.victorhugo.supportdesk.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChamadoService {
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Chamado> listar() { return chamadoRepository.findAll(); }
    public Chamado buscar(Long id) { return chamadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Chamado não encontrado")); }

    public Chamado criar(ChamadoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Categoria categoria = categoriaRepository.findById(request.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Chamado chamado = Chamado.builder()
                .titulo(request.titulo())
                .descricao(request.descricao())
                .prioridade(request.prioridade())
                .usuario(usuario)
                .categoria(categoria)
                .build();
        return chamadoRepository.save(chamado);
    }

    public Chamado atualizarStatus(Long id, StatusChamado status) {
        Chamado chamado = buscar(id);
        chamado.setStatus(status);
        return chamadoRepository.save(chamado);
    }
}
