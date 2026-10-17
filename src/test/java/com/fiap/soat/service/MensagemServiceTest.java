package com.fiap.soat.service;

import com.fiap.soat.model.Mensagem;
import com.fiap.soat.repository.MensagemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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

        verify(mensagemRepository, times(1)).save(mensagem);
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

    /*
    @Test
    void devePermitirObterMensagens(){
        fail("lógica não implementada");

    }
    */

    @Test
    void devePermitirModificarMensagem(){
        var id = randomUUID();
        var mensagemAntiga = gerarMensagem();
        mensagemAntiga.setId(id);
        var mensagemNova = mensagemAntiga;
        mensagemNova.setConteudo("conteudo_teste");

        when(mensagemRepository.findById(any(UUID.class))).thenReturn(Optional.of(mensagemAntiga));

        when(mensagemRepository.save(any(Mensagem.class))).thenReturn(mensagemNova);

        var mensagemObtida = mensagemService.atualizarMensagem(id, mensagemNova);

        assertThat(mensagemObtida)
                .isInstanceOf(Mensagem.class)
                .isNotNull();

        assertThat(mensagemObtida.getId())
                .isEqualTo(mensagemNova.getId());

        assertThat(mensagemObtida.getUsuario())
                .isEqualTo(mensagemNova.getUsuario());

        assertThat(mensagemObtida.getConteudo())
                .isEqualTo(mensagemNova.getConteudo());

        verify(mensagemRepository, times(1)).save(any(Mensagem.class));

    }


    @Test
    void devePermitirRemoverMensagem(){

        var id = randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);

        when(mensagemRepository.findById(any(UUID.class))).thenReturn(Optional.of(mensagem));

        boolean mensagemRemovida = mensagemService.removerMensagem(id);

        assertThat(mensagemRemovida).isTrue();
        verify(mensagemRepository, times(1)).delete(any(Mensagem.class));
    }
}
