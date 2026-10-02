package com.fiap.soat.helper;

import com.fiap.soat.model.Mensagem;

public abstract class MensagemHelper {

    public static Mensagem gerarMensagem(){
        return Mensagem.builder()
                .usuario("usuario")
                .conteudo("conteudo")
                .build();
    }
}
