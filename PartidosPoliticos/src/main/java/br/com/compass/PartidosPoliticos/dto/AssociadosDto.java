package br.com.compass.PartidosPoliticos.dto;

import java.time.LocalDate;

public class AssociadosDto {
	
	private Long id;
	
	private String nomeAssociado;
	
	private String cargoPolitico; // Vereador, Prefeito, Deputado Estadual, Deputado Federal, Senador, Governador, Presidente e Nenhum
	
	private LocalDate dataNascimento;
	
	private String sexo; // masculino, feminino

}
