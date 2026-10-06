package com.fiap.soat.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static com.fiap.soat.helper.MensagemHelper.gerarMensagem;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(scripts = {"/db_load.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = AFTER_TEST_METHOD)
public class MensagemControllerTestIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirRegistrarMensagem() throws Exception {

        var mensagemRequest = gerarMensagem();

        given()
                .body(mensagemRequest)
                .contentType(APPLICATION_JSON_VALUE)
        .when()
                .post("/mensagens")
        .then()
                .statusCode(CREATED.value())
                .body("$", hasKey("id"))
                .body("$", hasKey("usuario"))
                .body("$", hasKey("conteudo"))
                .body("$", hasKey("gostei"))
                .body("$", hasKey("dataCriacao"))
                .body("usuario", equalTo(mensagemRequest.getUsuario()))
                .body("conteudo", equalTo(mensagemRequest.getConteudo()));

    }

    @Test
    void devePermitirObterMensagem() throws Exception {

        when()
                .get("/mensagens/{id}", "046a6d68-7325-40ca-8ba9-974d1f189fac")
        .then()
                .statusCode(OK.value())
                .body("$", hasKey("id"))
                .body("usuario", equalTo("Carlota"))
                .body("conteudo", equalTo("mensagem da Carlota"));

    }


    @Test
    void devePermitirObterMensagemV2() throws Exception {
        given()
                .pathParam("id", "046a6d68-7325-40ca-8ba9-974d1f189fac")
        .when()
                .get("/mensagens/{id}")
        .then()
                .statusCode(OK.value())
                .body("$", hasKey("id"))
                .body("usuario", equalTo("Carlota"))
                .body("conteudo", equalTo("mensagem da Carlota"));

    }


}
