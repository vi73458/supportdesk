package br.com.victorhugo.supportdesk.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "equipamentos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50) private String patrimonio;
    @Column(nullable = false, length = 60) private String tipo;
    private String fabricante;
    private String modelo;
    private String numeroSerie;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "usuario_id") private Usuario usuario;
}
