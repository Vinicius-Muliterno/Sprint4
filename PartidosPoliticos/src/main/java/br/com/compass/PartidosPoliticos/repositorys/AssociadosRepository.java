package br.com.compass.PartidosPoliticos.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.PartidosPoliticos.entities.Associados;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;

@Repository
public interface AssociadosRepository extends JpaRepository <Associados , Long>{

	List<Associados> findByCargosPoliticos(CargosPoliticos cargo);
		
	
	

}
