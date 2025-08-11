package com.prestacao_de_servicos.prestacao.controller.AgendamentoController;

import com.prestacao_de_servicos.prestacao.model.agendamento.Agendamento;
import com.prestacao_de_servicos.prestacao.model.agendamento.StatusAgendamento;
import com.prestacao_de_servicos.prestacao.service.agendamento.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) StatusAgendamento status,
            @RequestParam(required = false) Long agendaId) {

        List<Agendamento> agendamentos;

        if (clienteId != null) {
            agendamentos = agendamentoService.listarPorCliente(clienteId);
        } else if (status != null) {
            agendamentos = agendamentoService.listarPorStatus(status);
        } else if (agendaId != null) {
            agendamentos = agendamentoService.listarPorAgenda(agendaId);
        } else {
            agendamentos = agendamentoService.listarTodos();
        }

        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Agendamento agendamento) {
        Agendamento criado = agendamentoService.salvar(agendamento);
        URI location = URI.create("/api/agendamentos/" + criado.getIdAgendamento());
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        return agendamentoService.atualizar(id, agendamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = agendamentoService.deletar(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}