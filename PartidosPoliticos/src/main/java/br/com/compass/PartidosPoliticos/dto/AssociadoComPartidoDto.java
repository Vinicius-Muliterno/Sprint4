package br.com.compass.PartidosPoliticos.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.PartidosPoliticos.entities.Associados;
import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssociadoComPartidoDto {

	private Long id;

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CargosPoliticos cargosPoliticos;

	@NotNull
	@NotEmpty
	private String nomePartido;
	
	public AssociadoComPartidoDto(Associados associado, Partidos partido) {
		this.setId(associado.getId());
		this.setNome(associado.getNome());
		this.setCargosPoliticos(associado.getCargosPoliticos());
		this.setNomePartido(partido.getNomePartido());
	}
	

}
