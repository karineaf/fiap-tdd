package com.fiap.soat.controller;

import com.fiap.soat.handler.GlobalExceptionHandler;
import com.fiap.soat.model.Mensagem;
import com.fiap.soat.service.MensagemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.soat.helper.MensagemHelper.gerarMensagem;
import static com.fiap.soat.helper.StringConverter.asJsonString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MensagemControllerTest {

    private MockMvc mock;

    @Mock
    private MensagemService mensagemService;
    AutoCloseable mockContext;

    @BeforeEach
    void setup(){
        mockContext = openMocks(this);
        MensagemController mensagemController = new MensagemController(mensagemService);

        // Add filtro que intercepta  qualquer chamada e no response coloca um encoding. Objetivo:
        mock = MockMvcBuilders.standaloneSetup(mensagemController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
        }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mockContext.close();

    }

    @Test
    void devePermitirRegistrarMensagem() throws Exception {

        // Arrange
        var mensagemRequest = gerarMensagem();
        when(mensagemService.registarMensagem(any(Mensagem.class))).thenAnswer(i -> i.getArgument(0));

        // Act + Assert
        mock.perform(post("/mensagens")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(mensagemRequest)))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(mensagemService, times(1)).registarMensagem(any(Mensagem.class));

    }

    @Test
    void devePermitirAlterarMensagem(){
        fail("não implementado");
    }

    @Test
    void devePermitirObterMensagem(){
        fail("não implementado");
    }

    @Test
    void devePermitirRemoverMensagem(){
        fail("não implementado");
    }
}
