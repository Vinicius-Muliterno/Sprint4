package br.com.compass.PartidosPoliticos.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.PartidosPoliticos.dto.PartidosDto;
import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;

@Repository
public interface PartidosRepository extends JpaRepository<Partidos, Long> {
	
	List<PartidosDto> findByIdeologia(Ideologia ideologia);

}
