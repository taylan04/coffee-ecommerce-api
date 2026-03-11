package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//@RestControllerAdvice = essa classe vai interceptar exceções lançadas nos controllers
//essa classe pode capturar exceções de qualquer controller
@RestControllerAdvice
public class RestExceptionHandler {

    //ExceptionHandler = quando essa exceção acontecer, execute este método
    //ResponseEntity é uma classe do Spring usada para manipular a resposta HTTP inteira ( o codigo, a mensagem e etc)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> excecaoGenerica(Exception ex) {
        ApiError apiError = ApiError.builder().data_hora(LocalDateTime.now())
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .erros(List.of(ex.getMessage()))
                .build();

        //aqui eu crio um objeto responseEntity porque o método precisa retornar um ResponseEntity<ApiError>
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> NaoEncontradoExcecao (Exception ex) {
        ApiError apiError = ApiError.builder().data_hora(LocalDateTime.now())
                .codigo(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .erros(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> argumentoInvalidoExcecao(MethodArgumentNotValidException ex) {
        List<String> errosLista = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError
                .builder()
                .data_hora(LocalDateTime.now())
                .codigo(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .erros(errosLista)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
