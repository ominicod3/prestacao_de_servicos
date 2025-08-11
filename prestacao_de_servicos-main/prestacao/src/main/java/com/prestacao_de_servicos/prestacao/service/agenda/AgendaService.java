package com.prestacao_de_servicos.prestacao.service.agenda;

import com.prestacao_de_servicos.prestacao.model.agenda.Agenda;
import com.prestacao_de_servicos.prestacao.model.agenda.StatusAgenda;
import com.prestacao_de_servicos.prestacao.repository.agenda.AgendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Agenda> listarTodas() {
        return agendaRepository.findAll();
    }

    public List<Agenda> listarPorVendedor(Long vendedorId) {
        return agendaRepository.findByVendedor_IdUsuario(vendedorId);
    }

    public List<Agenda> listarPorStatus(StatusAgenda status) {
        return agendaRepository.findByStatus(status);
    }

    public List<Agenda> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return agendaRepository.findByDataHoraBetween(inicio, fim);
    }

    public Agenda salvar(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Optional<Agenda> atualizar(Long id, Agenda agenda) {
        return agendaRepository.findById(id)
                .map(a -> {
                    a.setDataHora(agenda.getDataHora());
                    a.setStatus(agenda.getStatus());
                    a.setVendedor(agenda.getVendedor());
                    return agendaRepository.save(a);
                });
    }

    public boolean deletar(Long id) {
        return agendaRepository.findById(id)
                .map(a -> {
                    agendaRepository.delete(a);
                    return true;
                })
                .orElse(false);
    }
}