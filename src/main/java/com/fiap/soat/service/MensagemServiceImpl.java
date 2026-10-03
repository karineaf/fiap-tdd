package com.fiap.soat.service;

import com.fiap.soat.exception.MensagemNotFoundException;
import com.fiap.soat.model.Mensagem;
import com.fiap.soat.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.time.LocalDateTime.now;
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
    public Mensagem atualizarMensagem(UUID id, Mensagem mensagemAtualizada){
        var mensagem = obterMensagem(id);

        if (!mensagem.getId().equals(mensagemAtualizada.getId()))
            throw new MensagemNotFoundException("mensagem não apresenta o ID correto");

        mensagem.setConteudo(mensagemAtualizada.getConteudo());
        mensagem.setDataAlteracao(now());

        return mensagemRepository.save(mensagem);
    }


    @Override
    public boolean removerMensagem(UUID id){
        var mensagem = obterMensagem(id);
        mensagemRepository.delete(mensagem);
        return true;
    }

    @Override
    public Page<Mensagem> obterMensagens(Pageable pageable){
        return mensagemRepository.obterMensagens(pageable);
    }



}
