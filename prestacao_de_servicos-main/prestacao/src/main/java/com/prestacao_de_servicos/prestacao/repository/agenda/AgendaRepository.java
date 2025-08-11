package com.prestacao_de_servicos.prestacao.repository.agenda;

import com.prestacao_de_servicos.prestacao.model.agenda.Agenda;
import com.prestacao_de_servicos.prestacao.model.agenda.StatusAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByVendedor_IdUsuario(Long vendedorId);

    List<Agenda> findByStatus(StatusAgenda status);

    List<Agenda> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}