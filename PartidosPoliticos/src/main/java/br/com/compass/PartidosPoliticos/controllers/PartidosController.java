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
import br.com.compass.PartidosPoliticos.dto.PartidosDto;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;
import br.com.compass.PartidosPoliticos.service.PartidosService;

@RestController
@RequestMapping("/partidos")
public class PartidosController {

	@Autowired
	private PartidosService partidosService;

	@GetMapping
	public ResponseEntity<List<PartidosDto>> findAllOrByIdeologia(Ideologia ideologia) {
		if (ideologia != null) {
			return ResponseEntity.ok(partidosService.findByIdeologia(ideologia));
		}
		return ResponseEntity.ok(partidosService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PartidosDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(partidosService.findById(id));
	}

	@GetMapping("/{id}/associados")
	public ResponseEntity<List<AssociadoComPartidoDto>> findByPartidoAssociados(@PathVariable Long id) {
		return ResponseEntity.ok(partidosService.findByPartidoAssociados(id));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PartidosDto> save(@RequestBody @Valid PartidosDto partidoDTO,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/partidos/{id}").buildAndExpand(partidoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(partidosService.save(partidoDTO));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidosDto> updateById(@PathVariable Long id, @RequestBody @Valid PartidosDto partidoDTO) {
		return ResponseEntity.ok(partidosService.updateById(id, partidoDTO));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		partidosService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}