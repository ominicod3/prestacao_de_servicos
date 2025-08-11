package com.prestacao_de_servicos.prestacao.repository.mensagem;


import com.prestacao_de_servicos.prestacao.model.mensagem.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByRemetente_IdUsuario(Long remetenteId);
    List<Mensagem> findByDestinatario_IdUsuario(Long destinatarioId);
    List<Mensagem> findByRemetente_IdUsuarioAndDestinatario_IdUsuario(Long remetenteId, Long destinatarioId);
}