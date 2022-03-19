package br.com.compass.PartidosPoliticos.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.PartidosPoliticos.entities.Partidos;

public interface PartidosRepository 
			extends JpaRepository <Partidos , Long> {

}
