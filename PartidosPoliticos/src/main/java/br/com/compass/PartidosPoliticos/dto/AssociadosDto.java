package br.com.compass.PartidosPoliticos.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssociadosDto {
	
	@NotNull
	private Long idAssociado;
	@NotNull
	private Long idPartido;

}
