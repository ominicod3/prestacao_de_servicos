package com.prestacao_de_servicos.prestacao.repository.agendamento;

import com.prestacao_de_servicos.prestacao.model.agendamento.Agendamento;
import com.prestacao_de_servicos.prestacao.model.agendamento.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByCliente_IdUsuario(Long clienteId);
    List<Agendamento> findByStatus(StatusAgendamento status);
    List<Agendamento> findByAgenda_IdAgenda(Long agendaId);
}