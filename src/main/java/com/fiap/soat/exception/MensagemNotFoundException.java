package com.fiap.soat.exception;

public class MensagemNotFoundException extends RuntimeException{
    public MensagemNotFoundException(String mensagem){
        super(mensagem);
    }
}
