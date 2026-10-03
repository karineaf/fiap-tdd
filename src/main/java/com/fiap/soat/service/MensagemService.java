package com.fiap.soat.service;

import com.fiap.soat.model.Mensagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MensagemService {

    Mensagem registarMensagem(Mensagem mensagem);

    Mensagem obterMensagem(UUID id);

    Mensagem atualizarMensagem (UUID id, Mensagem mensagemNova);

    boolean removerMensagem(UUID id);

    Page<Mensagem> obterMensagens(Pageable pageable);
}
