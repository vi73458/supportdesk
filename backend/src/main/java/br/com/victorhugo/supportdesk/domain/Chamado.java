package br.com.victorhugo.supportdesk.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chamados")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Chamado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 160)
    private String titulo;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30)
    private StatusChamado status = StatusChamado.ABERTO;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private Prioridade prioridade = Prioridade.MEDIA;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "analista_id")
    private Usuario analista;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
    @Column(columnDefinition = "TEXT") private String diagnostico;
    @Column(columnDefinition = "TEXT") private String solucao;
    private LocalDateTime prazoSla;
    @Column(nullable = false) private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private LocalDateTime resolvidoEm;
    private LocalDateTime encerradoEm;

    @PrePersist
    void prePersist() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = criadoEm;
        if (status == null) status = StatusChamado.ABERTO;
        if (prioridade == null) prioridade = Prioridade.MEDIA;
    }
    @PreUpdate
    void preUpdate() { atualizadoEm = LocalDateTime.now(); }
}
