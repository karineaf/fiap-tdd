package com.fiap.soat.controller;

import com.fiap.soat.exception.MensagemNotFoundException;
import com.fiap.soat.handler.GlobalExceptionHandler;
import com.fiap.soat.model.Mensagem;
import com.fiap.soat.service.MensagemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static com.fiap.soat.helper.MensagemHelper.gerarMensagem;
import static com.fiap.soat.helper.StringConverter.asJsonString;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MensagemControllerTestIT {

  
}
