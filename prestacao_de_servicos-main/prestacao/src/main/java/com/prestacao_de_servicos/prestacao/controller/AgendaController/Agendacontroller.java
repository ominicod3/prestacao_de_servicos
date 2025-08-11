package com.prestacao_de_servicos.prestacao.controller.AgendaController;

import com.prestacao_de_servicos.prestacao.model.agenda.Agenda;
import com.prestacao_de_servicos.prestacao.model.agenda.StatusAgenda;
import com.prestacao_de_servicos.prestacao.service.agenda.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> listar(
            @RequestParam(required = false) Long vendedorId,
            @RequestParam(required = false) StatusAgenda status,
            @RequestParam(required = false) LocalDateTime inicio,
            @RequestParam(required = false) LocalDateTime fim) {

        List<Agenda> agendas;

        if (vendedorId != null) {
            agendas = agendaService.listarPorVendedor(vendedorId);
        } else if (status != null) {
            agendas = agendaService.listarPorStatus(status);
        } else if (inicio != null && fim != null) {
            agendas = agendaService.listarPorPeriodo(inicio, fim);
        } else {
            agendas = agendaService.listarTodas();
        }

        return ResponseEntity.ok(agendas);
    }

    @PostMapping
    public ResponseEntity<Agenda> criar(@RequestBody Agenda agenda) {
        Agenda criada = agendaService.salvar(agenda);
        URI location = URI.create("/api/agendas/" + criada.getIdAgenda());
        return ResponseEntity.created(location).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizar(@PathVariable Long id, @RequestBody Agenda agenda) {
        return agendaService.atualizar(id, agenda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removida = agendaService.deletar(id);
        if (removida) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}