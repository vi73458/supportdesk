package br.com.victorhugo.supportdesk.controller;

import br.com.victorhugo.supportdesk.domain.Categoria;
import br.com.victorhugo.supportdesk.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {
    private final CategoriaRepository repository;
    public CategoriaController(CategoriaRepository repository){ this.repository = repository; }
    @GetMapping public List<Categoria> listar(){ return repository.findAll(); }
}
