package br.com.victorhugo.supportdesk.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosticos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Diagnostico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "chamado_id", nullable = false)
    private Chamado chamado;
    @Column(nullable = false, columnDefinition = "TEXT") private String etapa;
    @Column(nullable = false, columnDefinition = "TEXT") private String resultado;
    @Column(columnDefinition = "TEXT") private String observacao;
    @Column(nullable = false) private LocalDateTime criadoEm;

    @PrePersist
    void prePersist() { criadoEm = LocalDateTime.now(); }
}
