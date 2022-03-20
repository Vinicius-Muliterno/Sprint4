package br.com.compass.PartidosPoliticos.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.PartidosPoliticos.dto.PartidosDto;
// import br.com.compass.PartidosPoliticos.dto.PartidosDto;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "partidos")
public class Partidos {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull 
	@NotEmpty
	private String nomePartido;
	
	@NotNull 
	@NotEmpty
	private String sigla;
	
	@NotNull 
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia; // Direita, Centro e Esquerda
	
	@NotNull 
	@NotEmpty
	private LocalDate dataDeFundacao;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPartido")
	private List<Associados> associados = new ArrayList<Associados>();
	
	public void addAssociado(Associados associado) {
		associados.add(associado);
	}
	public void removeAssociado(Associados associado) {
		associados.remove(associado);
	}
	
	public boolean procurarAssociado(Associados associado) {
		return associados.contains(associado);
	}
	
	
	public PartidosDto converter(Partidos partido) {
		return new PartidosDto(partido);
	}
	public Partidos(@Valid PartidosDto partidoDto) {
		
	}
		
}
