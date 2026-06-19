package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.dtos.CampoInvalidoDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.dtos.HttpErrorMessageDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.dtos.HttpValidationErrorMessageDto;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistrarUsuarioException.class)
    public ResponseEntity<HttpErrorMessageDto> handleRegistrarUsuarioException(RegistrarUsuarioException e) {
        HttpErrorMessageDto errorResponse = new HttpErrorMessageDto(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<HttpErrorMessageDto> handleJWTVerificationException(JWTVerificationException e) {
        HttpErrorMessageDto errorResponse = new HttpErrorMessageDto(HttpStatus.UNAUTHORIZED,
                "Token inválido ou expirado! Faça o login novamente.",
                LocalDateTime.now());
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<HttpErrorMessageDto> handleJWTCreationException(JWTCreationException e) {
        HttpErrorMessageDto errorResponse = new HttpErrorMessageDto(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno ao gerar credenciais de acesso",
                LocalDateTime.now()
        );

        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {

        List<CampoInvalidoDto> camposInvalidos = e.getBindingResult().getFieldErrors().stream()
                .map(erro -> new CampoInvalidoDto(erro.getField(), erro.getDefaultMessage())).toList();

        HttpValidationErrorMessageDto errorResponse = new HttpValidationErrorMessageDto(
                HttpStatus.BAD_REQUEST,
                "Erro na validacao de dados.",
                LocalDateTime.now(),
                camposInvalidos);

        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    @ExceptionHandler(CepNaoEncontradoException.class)
    public ResponseEntity<HttpErrorMessageDto> handleCepNaoEncontradoException(CepNaoEncontradoException e) {
        HttpErrorMessageDto errorResponse = new HttpErrorMessageDto(
                HttpStatus.NOT_FOUND, "Não foi encontrado nenhum registro associado a este CEP!", LocalDateTime.now()
        );
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<HttpErrorMessageDto> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e) {
        HttpErrorMessageDto errorResponse = new HttpErrorMessageDto(HttpStatus.NOT_FOUND, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

}
