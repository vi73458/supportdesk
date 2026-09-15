package br.com.victorhugo.supportdesk.controller;

import br.com.victorhugo.supportdesk.domain.Usuario;
import br.com.victorhugo.supportdesk.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
    private final UsuarioRepository repository;
    public UsuarioController(UsuarioRepository repository){ this.repository = repository; }
    @GetMapping public List<Usuario> listar(){ return repository.findAll(); }
}
