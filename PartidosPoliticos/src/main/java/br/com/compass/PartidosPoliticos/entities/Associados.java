package br.com.compass.PartidosPoliticos.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.PartidosPoliticos.dto.AssociadoComPartidoDto;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;
import br.com.compass.PartidosPoliticos.entities.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "associado")

public class Associados {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CargosPoliticos cargosPoliticos; // Vereador, Prefeito, Deputado_Estadual, Deputado_Federal, Senador, Governador, Presidente e Nenhum
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo; // Masculino, Feminino
	
	public AssociadoComPartidoDto converter(Associados associado, Partidos partido) {
		return new AssociadoComPartidoDto(associado, partido);
	}
}
