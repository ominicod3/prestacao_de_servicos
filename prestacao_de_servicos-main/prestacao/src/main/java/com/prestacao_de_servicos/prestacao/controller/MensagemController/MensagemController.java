package com.prestacao_de_servicos.prestacao.controller.MensagemController;

import com.prestacao_de_servicos.prestacao.model.mensagem.Mensagem;
import com.prestacao_de_servicos.prestacao.service.mensagem.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/mensagens")
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @GetMapping
    public ResponseEntity<List<Mensagem>> listar(
            @RequestParam(required = false) Long remetenteId,
            @RequestParam(required = false) Long destinatarioId) {

        List<Mensagem> mensagens;

        if (remetenteId != null && destinatarioId != null) {
            mensagens = mensagemService.listarPorRemetenteEDestinatario(remetenteId, destinatarioId);
        } else if (remetenteId != null) {
            mensagens = mensagemService.listarPorRemetente(remetenteId);
        } else if (destinatarioId != null) {
            mensagens = mensagemService.listarPorDestinatario(destinatarioId);
        } else {
            mensagens = mensagemService.listarTodas();
        }

        return ResponseEntity.ok(mensagens);
    }

    @PostMapping
    public ResponseEntity<Mensagem> criar(@RequestBody Mensagem mensagem) {
        Mensagem criada = mensagemService.salvar(mensagem);
        URI location = URI.create("/api/mensagens/" + criada.getId());
        return ResponseEntity.created(location).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> atualizar(@PathVariable Long id, @RequestBody Mensagem mensagem) {
        return mensagemService.atualizar(id, mensagem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removida = mensagemService.deletar(id);
        if (removida) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}