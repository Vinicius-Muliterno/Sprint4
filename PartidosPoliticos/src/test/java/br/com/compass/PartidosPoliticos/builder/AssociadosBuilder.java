package br.com.compass.PartidosPoliticos.builder;



import java.time.LocalDate;

import br.com.compass.PartidosPoliticos.dto.AssociadoComPartidoDto;
import br.com.compass.PartidosPoliticos.dto.AssociadosDto;
import br.com.compass.PartidosPoliticos.entities.Associados;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;
import br.com.compass.PartidosPoliticos.entities.enums.Sexo;



public final class AssociadosBuilder {
	
	public static Associados getAssociados() {
		Associados associados = new Associados();
		associados.setId(1L);
		associados.setNome("Teste");
		associados.setCargosPoliticos(CargosPoliticos.PRESIDENTE);
		associados.setDataNascimento(LocalDate.now());
		associados.setSexo(Sexo.FEMININO);
		associados.setDataNascimento(LocalDate.of(1998, 8, 22));
				
		return associados;
	}
	
	public static AssociadosDto getAssociadosDto() {
		AssociadosDto associadosDto = new AssociadosDto();
		associadosDto.setIdAssociado(1L);
		associadosDto.setIdPartido(2L);

		return associadosDto;
	}

	public static AssociadoComPartidoDto getAssociadoComPartidoDto() {
		AssociadoComPartidoDto associadoComPartidoDto = new AssociadoComPartidoDto();
		associadoComPartidoDto.setId(1L);
		associadoComPartidoDto.setNome("Teste");
		associadoComPartidoDto.setCargosPoliticos(CargosPoliticos.PRESIDENTE);
		associadoComPartidoDto.setDataNascimento(LocalDate.now());
		associadoComPartidoDto.setSexo(Sexo.FEMININO);
		associadoComPartidoDto.setDataNascimento(LocalDate.of(1998, 8, 22));

		return associadoComPartidoDto;
	}

	
}
