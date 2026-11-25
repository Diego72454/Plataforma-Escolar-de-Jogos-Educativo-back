package br.edu.escola.jogos.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<Map<String, String>> credenciais(CredenciaisInvalidasException e) {
        return resposta(HttpStatus.UNAUTHORIZED, "Matrícula ou senha incorretas.");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, String>> requisicaoInvalida(Exception e) {
        return resposta(HttpStatus.BAD_REQUEST, "Preencha a matrícula e a senha.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> generico(Exception e) {
        return resposta(HttpStatus.INTERNAL_SERVER_ERROR, "Algo deu errado. Tente novamente em instantes.");
    }

    private ResponseEntity<Map<String, String>> resposta(HttpStatus status, String msg) {
        return ResponseEntity.status(status).body(Map.of("mensagem", msg));
    }
}
