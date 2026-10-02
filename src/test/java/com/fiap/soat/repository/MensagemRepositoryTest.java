package com.fiap.soat.repository;

import com.fiap.soat.model.Mensagem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class MensagemRepositoryTest {

    @Mock
    private MensagemRepository mensagemRepository;
    AutoCloseable mockContext;

    @BeforeEach
    void setup(){
        mockContext = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockContext.close();

    }


    @Test
    void devePermitirRegistrarMensagem(){

        // Arrange
        var mensagem = gerarMensagem();
        when(mensagemRepository.save(any(Mensagem.class))).thenReturn(mensagem);

        // Act
        var mensagemArmazenada = mensagemRepository.save(mensagem);

        // Assert
        verify(mensagemRepository, times(1)).save(mensagem);
    }

    @Test
    void devePermitirConsultarMensagem(){

        // Arrange
        var id = UUID.randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);

        when(mensagemRepository.findById(any(UUID.class))).thenReturn(Optional.of(mensagem));

        // Act
        var mensagemEncontrada = mensagemRepository.findById(id);

        // Assert
        verify(mensagemRepository, times(1)).findById(id);
        assertThat(mensagemEncontrada)
                .isNotNull()
                .containsSame(mensagem);
    }


    @Test
    void devePermitirApagarMensagem(){

        // Arrange
        var id = UUID.randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);

        doNothing().when(mensagemRepository).deleteById(any(UUID.class));

        // Act
        mensagemRepository.deleteById(id);

        // Assert
        verify(mensagemRepository, times(1)).deleteById(id);
    }


    private Mensagem gerarMensagem(){
        return Mensagem.builder()
                .usuario("usuario")
                .conteudo("conteudo")
                .build();
    }
}
