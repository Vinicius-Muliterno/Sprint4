package br.com.compass.PartidosPoliticos.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compass.PartidosPoliticos.dto.AssociadoComPartidoDto;
import br.com.compass.PartidosPoliticos.dto.AssociadosDto;
import br.com.compass.PartidosPoliticos.entities.Associados;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;
import br.com.compass.PartidosPoliticos.service.AssociadosService;

@RestController
@RequestMapping("/associados")
public class AssociadosController {

	@Autowired
	private AssociadosService associadoService;

	@GetMapping
	public ResponseEntity<List<Associados>> findAllOrByCargoPolitico(CargosPoliticos cargo) {
		if(cargo != null) {
			return ResponseEntity.ok(associadoService.findByCargoPolitico(cargo));
		}
		return ResponseEntity.ok(associadoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Associados> findById(@PathVariable Long id){
		return ResponseEntity.ok(associadoService.findById(id));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Associados> save(@RequestBody @Valid Associados associado, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(associado.getId()).toUri();
		return ResponseEntity.created(uri).body(associadoService.save(associado));
	}
	
	@PostMapping("/partidos")
	@Transactional
	public ResponseEntity<AssociadoComPartidoDto> associarPartido(@RequestBody @Valid AssociadosDto associacaoDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(associacaoDTO.getIdAssociado()).toUri();
		return ResponseEntity.created(uri).body(associadoService.associarPartido(associacaoDTO));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Associados> updateById(@PathVariable Long id, @RequestBody @Valid Associados associado){
		return ResponseEntity.ok(associadoService.updateById(id, associado));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		associadoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idAssociado}/partidos/{idPartido}")
	@Transactional
	public ResponseEntity<?> deletarAssociacao(@PathVariable Long idAssociado, @PathVariable Long idPartido){
		associadoService.deletarAssociacao(idAssociado, idPartido);
		return ResponseEntity.noContent().build();
	}
}