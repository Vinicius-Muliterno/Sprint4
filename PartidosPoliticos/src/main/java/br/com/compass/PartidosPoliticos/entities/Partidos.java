package br.com.compass.PartidosPoliticos.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity (name = "partido")
public class Partidos {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nomePartido;
	
	private String sigla;
	
	private String ideologia; // Direita, Centro e Esquerda
	
	private LocalDate dataDeFundacao;
	
	
	@SuppressWarnings("unused")
	public Partidos() {
	
	}
	
	
	
	public Partidos(Long id, String nomePartido, String sigla, String ideologia, LocalDate dataDeFundacao) {
		
		this.id = id;
		this.nomePartido = nomePartido;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataDeFundacao = dataDeFundacao;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePartido() {
		return nomePartido;
	}

	public void setNomePartido(String nomePartido) {
		this.nomePartido = nomePartido;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(String ideologia) {
		this.ideologia = ideologia;
	}

	public LocalDate getDataDeFundacao() {
		return dataDeFundacao;
	}

	public void setDataDeFundacao(LocalDate dataDeFundacao) {
		this.dataDeFundacao = dataDeFundacao;
	}
	
	
	
}
