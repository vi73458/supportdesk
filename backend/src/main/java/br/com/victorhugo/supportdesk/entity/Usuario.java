package br.com.victorhugo.supportdesk.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120) private String nome;
    @Column(nullable = false, unique = true, length = 160) private String email;
    @Column(nullable = false, length = 30) private String perfil;
    @Column(nullable = false) private Boolean ativo = true;
}
