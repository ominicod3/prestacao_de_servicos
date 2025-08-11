package com.prestacao_de_servicos.prestacao.service.agendamento;

import com.prestacao_de_servicos.prestacao.model.agendamento.Agendamento;
import com.prestacao_de_servicos.prestacao.model.agendamento.StatusAgendamento;
import com.prestacao_de_servicos.prestacao.repository.agendamento.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> listarPorCliente(Long clienteId) {
        return agendamentoRepository.findByCliente_IdUsuario(clienteId);
    }

    public List<Agendamento> listarPorStatus(StatusAgendamento status) {
        return agendamentoRepository.findByStatus(status);
    }

    public List<Agendamento> listarPorAgenda(Long agendaId) {
        return agendamentoRepository.findByAgenda_IdAgenda(agendaId);
    }

    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public Optional<Agendamento> atualizar(Long id, Agendamento agendamento) {
        return agendamentoRepository.findById(id)
                .map(existente -> {
                    existente.setStatus(agendamento.getStatus());
                    existente.setCliente(agendamento.getCliente());
                    existente.setAgenda(agendamento.getAgenda());
                    return agendamentoRepository.save(existente);
                });
    }

    public boolean deletar(Long id) {
        return agendamentoRepository.findById(id)
                .map(existente -> {
                    agendamentoRepository.delete(existente);
                    return true;
                })
                .orElse(false);
    }
}