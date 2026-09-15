package br.com.victorhugo.supportdesk.entity;

import br.com.victorhugo.supportdesk.enums.Prioridade;
import br.com.victorhugo.supportdesk.enums.StatusChamado;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusChamado status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Prioridade prioridade;

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
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private LocalDateTime resolvidoEm;
    private LocalDateTime encerradoEm;

    @PrePersist
    void prePersist() {
        if (status == null) status = StatusChamado.ABERTO;
        if (prioridade == null) prioridade = Prioridade.MEDIA;
        criadoEm = LocalDateTime.now();
        atualizadoEm = criadoEm;
    }

    @PreUpdate
    void preUpdate() { atualizadoEm = LocalDateTime.now(); }
}
