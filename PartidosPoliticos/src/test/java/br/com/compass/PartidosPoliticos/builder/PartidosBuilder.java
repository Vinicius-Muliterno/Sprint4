package br.com.compass.PartidosPoliticos.builder;

import java.time.LocalDate;

import br.com.compass.PartidosPoliticos.dto.PartidosDto;
import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;

public final class PartidosBuilder {
	
	
	public static Partidos getPartidos() {
		Partidos partido = new Partidos();
		partido.setId(1L);
		partido.setNomePartido("Democracia Cristã	");
		partido.setSigla("DC");
		partido.setIdeologia(Ideologia.CENTRO);
		partido.setDataDeFundacao(LocalDate.now());
		partido.setDataDeFundacao(LocalDate.of(1998, 8, 22));

		return partido;
	}
	
	public static PartidosDto getPartidosDto() {
		PartidosDto partidoDto = new PartidosDto();
		partidoDto.setId(1L);
		partidoDto.setNomePartido("Democracia Cristã	");
		partidoDto.setSigla("DC");
		partidoDto.setIdeologia(Ideologia.CENTRO);
		partidoDto.setDataDeFundacao(LocalDate.now());
		partidoDto.setDataDeFundacao(LocalDate.of(1998, 8, 22));
		
		return partidoDto;
	}
	
}
