package br.com.compass.PartidosPoliticos.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.PartidosPoliticos.dto.AssociadoComPartidoDto;
import br.com.compass.PartidosPoliticos.dto.PartidosDto;
import br.com.compass.PartidosPoliticos.entities.Associados;
import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;
import br.com.compass.PartidosPoliticos.repositorys.PartidosRepository;
import br.com.compass.PartidosPoliticos.service.exception.DeletePartidoException;
import br.com.compass.PartidosPoliticos.service.exception.EntityNotFoundException;
import br.com.compass.PartidosPoliticos.service.exception.MethodArgumentNotValidException;

@Service
public class PartidosService {
	
	@Autowired 
	public PartidosRepository partidoRepository;

	public List<PartidosDto> findByIdeologia(Ideologia ideologia) {
		return partidoRepository.findByIdeologia(ideologia);
	}

	public List<PartidosDto> findAll() {
		List<Partidos> partidos = partidoRepository.findAll();
		List<PartidosDto> partidosDTO = partidos.stream().map(a -> a.converter(a)).collect(Collectors.toList());
		return partidosDTO;
	}

	public List<AssociadoComPartidoDto> findByPartidoAssociados(Long id) {
		Partidos partido = findByPartido(id);
		List<Associados> associados = partido.getAssociados();
		List<AssociadoComPartidoDto> associadosDTO = associados.stream().map(a -> a.converter(a, partido))
				.collect(Collectors.toList());
		return associadosDTO;
	}

	public PartidosDto findById(Long id) {
		Partidos partido = findByPartido(id);
		return new PartidosDto(partido);
	}

	public PartidosDto save(@Valid PartidosDto partidoDto) {
		Partidos partido = new Partidos (partidoDto);
		try {
			partidoRepository.save(partido);
			return new PartidosDto(partido);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public PartidosDto updateById(Long id, @Valid PartidosDto partidoDTO) {
		Partidos atualizarPartido = findByPartido(id);
		try {
			atualizarPartido.setNomePartido(partidoDTO.getNomePartido());
			atualizarPartido.setSigla(partidoDTO.getSigla());
			atualizarPartido.setIdeologia(partidoDTO.getIdeologia());
			atualizarPartido.setDataDeFundacao(partidoDTO.getDataDeFundacao());
			return new PartidosDto(atualizarPartido);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public void deleteById(Long id) {
		List<Associados> associados = findByPartido(id).getAssociados();
		if(associados.isEmpty()) {
			partidoRepository.deleteById(id);
		} else {
			throw new DeletePartidoException("Partido contém associados. Para excluir o partido é necessário desvincular os associados primeiro.");
		}
	}
	
	private Partidos findByPartido(Long id) {
		Partidos partido = partidoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ID " + id + " não encontrado."));
		return partido;
	}
}
