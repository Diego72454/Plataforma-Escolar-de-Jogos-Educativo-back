package br.edu.escola.jogos.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired MockMvc mvc;

    private org.springframework.test.web.servlet.ResultActions login(String json) throws Exception {
        return mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(json));
    }

    @Test
    void loginValidoRetornaTurmaEFaixaEtaria() throws Exception {
        login("{\"matricula\":\"2024001\",\"senha\":\"1234\"}")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.turma").value("2º Ano"))
                .andExpect(jsonPath("$.faixaEtaria").value("7–8 anos"));
    }

    @Test
    void senhaErradaRetorna401ComMensagemClara() throws Exception {
        login("{\"matricula\":\"2024001\",\"senha\":\"0000\"}")
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensagem").value("Matrícula ou senha incorretas."));
    }

    @Test
    void matriculaInexistenteRetornaMesmoErro() throws Exception {
        login("{\"matricula\":\"9999999\",\"senha\":\"1234\"}")
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensagem").value("Matrícula ou senha incorretas."));
    }

    @Test
    void camposVaziosRetornam400() throws Exception {
        login("{\"matricula\":\"\",\"senha\":\"\"}")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("Preencha a matrícula e a senha."));
    }
}