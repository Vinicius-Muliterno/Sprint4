package br.com.compass.PartidosPoliticos.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociadosDto {
	
	@NotNull
	private Long idAssociado;
	@NotNull
	private Long idPartido;

}
