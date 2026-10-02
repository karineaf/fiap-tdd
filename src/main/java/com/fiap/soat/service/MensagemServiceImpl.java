package com.fiap.soat.service;

import com.fiap.soat.exception.MensagemNotFoundException;
import com.fiap.soat.model.Mensagem;
import com.fiap.soat.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;


@Service
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService {


    private final MensagemRepository mensagemRepository;

    @Override
    public Mensagem registarMensagem(Mensagem mensagem) {
        mensagem.setId(randomUUID());
        return mensagemRepository.save(mensagem);
    }

    @Override
    public Mensagem obterMensagem(UUID id){
        return mensagemRepository.findById(id).orElseThrow(() -> new MensagemNotFoundException("Mensagem não encontrada"));

    }

    @Override
    public List<Mensagem> obterMensagens(String usuario){
        return mensagemRepository.findAllByUsuario(usuario);
    }



}
