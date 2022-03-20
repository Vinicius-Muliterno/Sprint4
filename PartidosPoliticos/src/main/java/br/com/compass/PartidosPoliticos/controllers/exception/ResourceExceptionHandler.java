package br.com.compass.PartidosPoliticos.controllers.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.compass.PartidosPoliticos.service.exception.DeletePartidoException;
import br.com.compass.PartidosPoliticos.service.exception.EntityNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {
		
		@ExceptionHandler(EntityNotFoundException.class)
		public ResponseEntity<StandardError> objetoNaoEncontrado(EntityNotFoundException e, HttpServletRequest request){
			
			StandardError erro = new StandardError();
			erro.setTimestamp(Instant.now());
			erro.setStatus(HttpStatus.NOT_FOUND.value());
			erro.setError("ID informado inexistente.");
			erro.setMessage(e.getMessage());
			erro.setPath(request.getRequestURI());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<StandardError> camposInvalidos(MethodArgumentNotValidException e, HttpServletRequest request){
			StandardError erro = new StandardError();
			erro.setTimestamp(Instant.now());
			erro.setStatus(HttpStatus.BAD_REQUEST.value());
			erro.setError("Campo não pode ser vazio/nulo.");
			erro.setMessage("Campo incorreto: " + e.getFieldError().getField().toUpperCase());
			erro.setPath(request.getRequestURI());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		}
		
		@ExceptionHandler(DeletePartidoException.class)
		public ResponseEntity<StandardError> camposInvalidos(DeletePartidoException e, HttpServletRequest request){
			StandardError erro = new StandardError();
			erro.setTimestamp(Instant.now());
			erro.setStatus(HttpStatus.BAD_REQUEST.value());
			erro.setError("Não é possível excluir o partido.");
			erro.setMessage("Erro: " + e.getMessage());
			erro.setPath(request.getRequestURI());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		}
}


