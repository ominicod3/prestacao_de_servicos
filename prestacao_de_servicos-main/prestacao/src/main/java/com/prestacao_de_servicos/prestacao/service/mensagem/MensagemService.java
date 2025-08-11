package com.prestacao_de_servicos.prestacao.service.mensagem;


import com.prestacao_de_servicos.prestacao.model.mensagem.Mensagem;
import com.prestacao_de_servicos.prestacao.repository.mensagem.MensagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    public List<Mensagem> listarTodas() {
        return mensagemRepository.findAll();
    }

    public List<Mensagem> listarPorRemetente(Long remetenteId) {
        return mensagemRepository.findByRemetente_IdUsuario(remetenteId);
    }

    public List<Mensagem> listarPorDestinatario(Long destinatarioId) {
        return mensagemRepository.findByDestinatario_IdUsuario(destinatarioId);
    }

    public List<Mensagem> listarPorRemetenteEDestinatario(Long remetenteId, Long destinatarioId) {
        return mensagemRepository.findByRemetente_IdUsuarioAndDestinatario_IdUsuario(remetenteId, destinatarioId);
    }

    public Mensagem salvar(Mensagem mensagem) {
        mensagem.setDataEnvio(LocalDateTime.now());
        return mensagemRepository.save(mensagem);
    }

    public Optional<Mensagem> atualizar(Long id, Mensagem mensagem) {
        return mensagemRepository.findById(id)
                .map(m -> {
                    m.setTexto(mensagem.getTexto());
                    m.setRemetente(mensagem.getRemetente());
                    m.setDestinatario(mensagem.getDestinatario());
                    return mensagemRepository.save(m);
                });
    }

    public boolean deletar(Long id) {
        return mensagemRepository.findById(id)
                .map(m -> {
                    mensagemRepository.delete(m);
                    return true;
                })
                .orElse(false);
    }
}