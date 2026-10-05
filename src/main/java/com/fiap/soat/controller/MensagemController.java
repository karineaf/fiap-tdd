package com.fiap.soat.controller;

import com.fiap.soat.exception.MensagemNotFoundException;
import com.fiap.soat.model.Mensagem;
import com.fiap.soat.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.*;
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

    @GetMapping(
            value = "/{id}",
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?>buscarMensagem(@PathVariable String id){
        try {
            var uuid = fromString(id);
            var mensagemEncontrada = mensagemService.obterMensagem(uuid);
            return new ResponseEntity<>(mensagemEncontrada,OK);

        } catch (MensagemNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("mensagem não encontrada");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

}
