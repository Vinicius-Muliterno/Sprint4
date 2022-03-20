package br.com.compass.PartidosPoliticos.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.PartidosPoliticos.dto.AssociadoComPartidoDto;
import br.com.compass.PartidosPoliticos.dto.AssociadosDto;
import br.com.compass.PartidosPoliticos.entities.Associados;
import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;
import br.com.compass.PartidosPoliticos.repositorys.AssociadosRepository;
import br.com.compass.PartidosPoliticos.repositorys.PartidosRepository;
import br.com.compass.PartidosPoliticos.service.exception.MethodArgumentNotValidException;

@Service
public class AssociadosService {

	@Autowired
	private AssociadosRepository associadosRepository;

	@Autowired
	private PartidosRepository partidoRepository;
	
	public List<Associados> findByCargoPolitico(CargosPoliticos cargo) {
		return associadosRepository.findByCargosPoliticos(cargo);
	}

	public List<Associados> findAll() {
		return associadosRepository.findAll();
	}

	public Associados findById(long id) {
		return associadosRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("O ID " + id + " não foi encontrado."));
	}

	public Associados save(@Valid Associados associado) {
		try {
			return associadosRepository.save(associado);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public AssociadoComPartidoDto associarPartido(@Valid AssociadosDto associacaoDTO) {
		Partidos partido = partidoRepository.findById(associacaoDTO.getIdPartido())
				.orElseThrow(() -> new EntityNotFoundException("O partido com ID: " + associacaoDTO.getIdPartido() + " não foi encontrado."));
		Associados associado = associadosRepository.findById(associacaoDTO.getIdAssociado())
				.orElseThrow(() -> new EntityNotFoundException("O Associado com ID: " + associacaoDTO.getIdAssociado() + " não foi encontrado."));
		partido.addAssociado(associado);
		
		return new AssociadoComPartidoDto(associado, partido);
	}

	public Associados updateById(Long id, @Valid Associados associado) {
		Associados atualizarAssociado = findById(id);
		try {
			atualizarAssociado.setNome(associado.getNome());
			atualizarAssociado.setCargosPoliticos(associado.getCargosPoliticos());
			atualizarAssociado.setDataNascimento(associado.getDataNascimento());
			atualizarAssociado.setSexo(associado.getSexo());
			return atualizarAssociado;
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public void deleteById(long id) {
		findById(id);
		associadosRepository.deleteById(id);
	}

	public void deletarAssociacao(Long idAssociado, Long idPartido) {
		Associados associado = associadosRepository.findById(idAssociado)
				.orElseThrow(() -> new EntityNotFoundException("O associado com ID " + idAssociado + " não foi encontrado."));
		Partidos partido = partidoRepository.findById(idPartido)
				.orElseThrow(() -> new EntityNotFoundException("O partido com ID " + idPartido + " não foi encontrado."));
		if (partido.procurarAssociado(associado)) {
			partido.removeAssociado(associado);
		}else {
			throw new EntityNotFoundException("O associado com ID " + idAssociado + " não foi encontrado no Partido de ID " + idPartido);	
		}
	}

}
	

