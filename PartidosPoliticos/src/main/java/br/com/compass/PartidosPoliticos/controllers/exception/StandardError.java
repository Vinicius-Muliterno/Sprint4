package br.com.compass.PartidosPoliticos.controllers.exception;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class StandardError {
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
