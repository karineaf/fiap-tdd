package com.fiap.soat.service;

import com.fiap.soat.model.Mensagem;

import java.util.List;
import java.util.UUID;

public interface MensagemService {

    Mensagem registarMensagem(Mensagem mensagem);

    Mensagem obterMensagem(UUID id);


}
