package com.fiap.soat.service;

import com.fiap.soat.model.Mensagem;
import com.fiap.soat.repository.MensagemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fiap.soat.helper.MensagemHelper.gerarMensagem;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.assertj.core.api.Assertions.assertThat;


public class MensagemServiceTest {

    @Mock
    private MensagemRepository mensagemRepository;
    private MensagemService mensagemService;
    AutoCloseable mockContext;

    @BeforeEach
    void setup(){
        mockContext = openMocks(this);
        mensagemService = new MensagemServiceImpl(mensagemRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockContext.close();

    }

    @Test
    void devePermitirRegistarMensagem(){
        //Arrange
        var mensagem = gerarMensagem();
        // “quando alguém chamar mensagemRepository.save(...), devolva exatamente o objeto que foi passado como argumento.”
        when(mensagemRepository.save(any(Mensagem.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var mensagemRegistrada = mensagemService.registarMensagem(mensagem);

        //Assert
        assertThat(mensagemRegistrada)
                .isNotNull()
                .isInstanceOf(Mensagem.class);

        assertThat(mensagemRegistrada.getId())
                .isNotNull();

        assertThat(mensagemRegistrada.getUsuario())
                .isEqualTo(mensagem.getUsuario());

        assertThat(mensagemRegistrada.getConteudo())
                .isEqualTo(mensagem.getConteudo());
    }


    @Test
    void devePermitirObterMensagemPorId(){

        // Arrange
        var id = randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);
        when(mensagemRepository.findById(any(UUID.class))).thenReturn(Optional.of(mensagem));

        // Act
        var mensagemObtida = mensagemService.obterMensagem(id);

        // Assert
        verify(mensagemRepository, times(1)).findById(any(UUID.class));
        assertThat(mensagemObtida).isEqualTo(mensagem);

    }

    @Test
    void devePermitirObterMensagens(){
        // Arrange
        var mensagem1 = gerarMensagem();
        var mensagem2 = gerarMensagem();
        mensagem2.setConteudo("conteudo 2");

        when(mensagemRepository.findAllByUsuario(any(String.class))).thenReturn(List.of(mensagem1, mensagem2));

        // Act
        var mensagensObtidas = mensagemService.obterMensagens(mensagem1.getUsuario());

        // Assert
        verify(mensagemRepository, times(1)).findAllByUsuario(any(String.class));
        assertThat(mensagensObtidas).size().isEqualTo(2);
        assertThat(mensagensObtidas.get(0)).isEqualTo(mensagem1);
        assertThat(mensagensObtidas.get(1)).isEqualTo(mensagem2);

    }


    @Test
    void devePermitirModificarMensagem(){
        fail("lógica não implementada");
    }


    @Test
    void devePermitirRemoverMensagem(){
        fail("lógica não implementada");
    }
}
