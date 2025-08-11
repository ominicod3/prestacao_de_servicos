package com.prestacao_de_servicos.prestacao.model.agendamento;



import com.prestacao_de_servicos.prestacao.model.agenda.Agenda;
import com.prestacao_de_servicos.prestacao.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status; // PENDENTE, CONFIRMADO, CANCELADO

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;
}