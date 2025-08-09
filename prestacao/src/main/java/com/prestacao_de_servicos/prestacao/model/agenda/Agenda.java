package com.prestacao_de_servicos.prestacao.model.agenda;

import com.prestacao_de_servicos.prestacao.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    private LocalDateTime dataHora; // junta data e hora num campo só

    @Enumerated(EnumType.STRING)
    private StatusAgenda status; // LIVRE, RESERVADO, CONFIRMADO

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;
}
