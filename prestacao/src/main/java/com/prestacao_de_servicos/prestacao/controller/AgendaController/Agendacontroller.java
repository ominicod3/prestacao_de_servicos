package com.prestacao_de_servicos.prestacao.controller.AgendaController;

import com.prestacao_de_servicos.prestacao.model.agenda.Agenda;
import com.prestacao_de_servicos.prestacao.service.agenda.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/agendas")
public class Agendacontroller {

    private final(
    AgendaService agendaService)
    {
        this.agendaService = agendaService;
    }

@GetMapping
public ResponseEntity<Agenda>> listar(
@RequestParam(name = "vendedorId", required = false ) long vendedorId)
List<Agenda> agendas;
        if (vendedorId == null) {
            agendas = agendaService.listarTodos();
        } else {
            agendas = agendaService.listarPorVendedor(vendedorId);
        }
        return ResponseEntity.ok(agendas);

    @PostMapping
    public ResponseEntity<Agenda> criar(@RequestBody Agenda agenda) {
        Agenda criado = agendaService.salvar(agenda);
        URI location = URI.create("/api/agendas/" + criado.getIdAgenda());
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizar(@PathVariable Long id, @RequestBody Agenda agenda) {
        return agendaService.atualizar(id, agenda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = agendaService.deletar(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
