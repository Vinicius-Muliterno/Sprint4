package br.com.compass.PartidosPoliticos.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidosDto {

	@NotNull
	private Long id;

	@NotNull
	private String nomePartido;

	private String sigla;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia; // Direita, Centro e Esquerda

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeFundacao;

	public PartidosDto(Partidos partido) {
		this.id = partido.getId();
		this.nomePartido = partido.getNomePartido();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataDeFundacao = partido.getDataDeFundacao();
	}

}
