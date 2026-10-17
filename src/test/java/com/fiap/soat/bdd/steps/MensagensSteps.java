package com.fiap.soat.bdd.steps;

import com.fiap.soat.helper.MensagemHelper;
import com.fiap.soat.model.Mensagem;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;

import static com.fiap.soat.helper.MensagemHelper.gerarMensagem;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//@SpringBootTest(webEnvironment = DEFINED_PORT)
public class MensagensSteps {

    private Response response;
    private Long mensagemId;
    private Mensagem mensagemResposta;
    private final String ENDPOINT = "http://localhost:808/mensagens";

    // CENARIO 1
    @Quando("submeter uma nova mensagem")
    public Mensagem submeterNovaMensagem() {
        var payload = gerarMensagem();

        response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(ENDPOINT);
        return response.then().extract().as(Mensagem.class);
    }

    @Entao("a mensagem é registrada com sucesso")
    public void mensagemRegistradaComSucesso() {
        response.then()
                .statusCode(CREATED.value());
    }


    // CENARIO 2
    @Dado("que uma mensagem já foi registrada")
    public void mensagemJaRegistrada() {
        mensagemResposta = submeterNovaMensagem();
    }

    @Quando("buscar a mensagem registada")
    public void buscarMensagemRegistrada() {
        response = given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT + "/{id}", mensagemResposta.getId().toString() );

    }

    @Entao("a mensagem é exibida com sucesso")
    public void mensagemExibidaComSucesso() {
        response.then()
                .statusCode(OK.value());
    }


    // CENARIO 3
    @Quando("requisitar a remoção da mensagem")
    public void requisitarRemocaoMensagem() {
        //response = RestAssured.delete("/mensagens/" + mensagemId);
        response = given()
                .contentType("application/json")
                .when()
                .delete(ENDPOINT + "/{id}", mensagemResposta.getId().toString());
    }

    @Entao("a mensagem é removida com sucesso")
    public void mensagemRemovidaComSucesso() {
        response.then().statusCode(NO_CONTENT.value());
    }
}
