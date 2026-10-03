package com.fiap.soat.service;

import com.fiap.soat.model.Mensagem;
import com.fiap.soat.repository.MensagemRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.fiap.soat.helper.MensagemHelper.gerarMensagem;
import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


// TESTE DE INTEGRAÇÃO COM H2, USA COMO REF OS REGISTROS DO db_load.sql

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_load.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = AFTER_TEST_METHOD)
public class MensagemServiceIT {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private MensagemService mensagemService;

    @Test
    void devePermitirRegistrarMensagem(){

        // Arrange
        var mensagem = gerarMensagem();

        // Act
        var mensagemRegistrada =  mensagemService.registarMensagem(mensagem);


        // Assert
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
    void devePermitirObterMensagem(){

        // Arrange
        var id  = fromString("3eaf2063-4885-4622-b56d-4e151fe41ffc");

        // Act
        var mensagemObtida = mensagemService.obterMensagem(id);

        // Assert
        assertThat(mensagemObtida)
                .isNotNull()
                .isInstanceOf(Mensagem.class);

        assertThat(mensagemObtida.getId())
                .isNotNull();

        assertThat(mensagemObtida.getUsuario())
                .isNotNull();

        assertThat(mensagemObtida.getConteudo())
                .isNotNull();

    }


    @Test
    void devePermitirRemoverMensagem(){

        // Arrange
        var id  = fromString("1daea186-3481-4ef1-972a-3a39331efb78");

        // Act
        var mensagemRemovida = mensagemService.removerMensagem(id);

        // Assert
        assertThat(mensagemRemovida).isTrue();

        // assertThatThrownBy(() -> mensagemService.obterMensagem(mensagemRegistrada.getId()))
        //         .isInstanceOf(MensagemNotFoundException.class)
        //         .hasMessage("Mensagem não encontrada");

    }
}
