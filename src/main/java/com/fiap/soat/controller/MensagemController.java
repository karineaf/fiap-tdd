package com.fiap.soat.controller;

import com.fiap.soat.model.Mensagem;
import com.fiap.soat.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/mensagens")
@RequiredArgsConstructor
public class MensagemController {
    private final MensagemService mensagemService;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Mensagem> registrarMensagem(@RequestBody Mensagem mensagem){
        var mensagemRegistrada = mensagemService.registarMensagem(mensagem);
        return new ResponseEntity<>(mensagemRegistrada, CREATED);

    }

}
