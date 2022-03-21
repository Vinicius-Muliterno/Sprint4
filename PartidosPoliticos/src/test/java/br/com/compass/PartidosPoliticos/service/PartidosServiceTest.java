package br.com.compass.PartidosPoliticos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compass.PartidosPoliticos.dto.AssociadoComPartidoDto;
import br.com.compass.PartidosPoliticos.dto.PartidosDto;
import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;
import br.com.compass.PartidosPoliticos.repositorys.PartidosRepository;
import br.com.compass.PartidosPoliticos.service.exception.EntityNotFoundException;
import br.com.compass.PartidosPoliticos.service.exception.MethodArgumentNotValidException;

@SpringBootTest
public class PartidosServiceTest {

	@InjectMocks
	private PartidosService service;

	@Mock
	private PartidosRepository repository;

	@Mock
	private Partidos partido;

	@Mock
	private PartidosDto partidoDto;

	private Optional<Partidos> optionalPartido;

	@Mock
	private AssociadoComPartidoDto associadoDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startPartido();
		startPartidoDTO();
		startOptionalPartido();
		startAssociadoDTO();
	}

	@Test
	void deveriaRetornarUmaListaDePartidoAoPassarIdeologia() {
		Mockito.when(repository.findByIdeologia(Mockito.any())).thenReturn(List.of(partidoDto));

		List<PartidosDto> response = service.findByIdeologia(Ideologia.ESQUERDA);

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(PartidosDto.class, response.get(0).getClass());
		assertEquals(1, response.get(0).getId());
		assertEquals("NomeTeste", response.get(0).getNomePartido());
		assertEquals("NT", response.get(0).getSigla());
		assertEquals(Ideologia.ESQUERDA, response.get(0).getIdeologia());
	}

	@Test
	void deveriaRetornarUmaListaDePartidoFindAll() {
		Mockito.when(repository.findAll()).thenReturn(List.of(partido));

		List<PartidosDto> response = service.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(PartidosDto.class, response.get(0).getClass());
		assertEquals(1, response.get(0).getId());
		assertEquals("NomeTeste", response.get(0).getNomePartido());
		assertEquals("NT", response.get(0).getSigla());
		assertEquals(Ideologia.ESQUERDA, response.get(0).getIdeologia());
	}

	@Test
	void deveriaRetornarUmPartidoFindById() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalPartido);

		PartidosDto response = service.findById(partido.getId());

		assertNotNull(response);
		assertEquals(PartidosDto.class, response.getClass());
		assertEquals(1, response.getId());
		assertEquals("NomeTeste", response.getNomePartido());
		assertEquals("NT", response.getSigla());
		assertEquals(Ideologia.ESQUERDA, response.getIdeologia());
	}

	@Test
	void deveriaRetornarEntityNotFoundException() {
		Mockito.when(repository.findById(Mockito.anyLong()))
				.thenThrow(new EntityNotFoundException("ID não encontrado."));

		try {
			service.findById((long) 1);
		} catch (EntityNotFoundException e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals("ID não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaRetornarMethodArgumentNotValidException() {
		Mockito.when(repository.findById(Mockito.any()))
				.thenThrow(new MethodArgumentNotValidException("Dados inválidos."));

		try {
			service.updateById((long) 1, partidoDto);
		} catch (MethodArgumentNotValidException e) {
			assertEquals(MethodArgumentNotValidException.class, e.getClass());
			assertEquals("Dados inválidos.", e.getMessage());
		}
	}

	@Test
	void deveriaCriarUmNovoPartido() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(partido);

		PartidosDto response = service.save(partidoDto);

		assertNotNull(response);
		assertEquals(PartidosDto.class, response.getClass());
		assertEquals(1, response.getId());
		assertEquals("NomeTeste", response.getNomePartido());
		assertEquals("NT", response.getSigla());
		assertEquals(Ideologia.ESQUERDA, response.getIdeologia());
	}

	@Test
	void deveriaDeletarPartidoComSucesso() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalPartido);
		Mockito.doNothing().when(repository).deleteById(Mockito.anyLong());
		service.deleteById((long) 1);

		Mockito.verify(repository, times(1)).deleteById(Mockito.anyLong());
	}

	@Test
	void deveriaRetornarEntityNotFoundExceptionAoTentarDeletar() {
		Mockito.when(repository.findById(Mockito.anyLong()))
				.thenThrow(new EntityNotFoundException("ID não encontrado."));
		try {
			service.deleteById((long) 1);
		} catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals("ID não encontrado.", e.getMessage());
		}
	}

	private void startPartido() {
		partido = new Partidos();
		partido.setId((long) 1);
		partido.setNomePartido("NomeTeste");
		partido.setSigla("NT");
		partido.setDataDeFundacao(LocalDate.now());
		partido.setIdeologia(Ideologia.ESQUERDA);
	}

	private void startPartidoDTO() {
		partidoDto = new PartidosDto();
		partidoDto.setId((long) 1);
		partidoDto.setNomePartido("NomeTeste");
		partidoDto.setSigla("NT");
		partidoDto.setDataDeFundacao(LocalDate.now());
		partidoDto.setIdeologia(Ideologia.ESQUERDA);
	}

	private void startOptionalPartido() {
		partido = new Partidos();
		partido.setId((long) 1);
		partido.setNomePartido("NomeTeste");
		partido.setSigla("NT");
		partido.setDataDeFundacao(LocalDate.now());
		partido.setIdeologia(Ideologia.ESQUERDA);
		optionalPartido = Optional.of(partido);
	}

	private void startAssociadoDTO() {
		associadoDto = new AssociadoComPartidoDto();
		associadoDto.setId((long) 1);
		associadoDto.setNome("NomeTeste");
		associadoDto.setCargosPoliticos(CargosPoliticos.PRESIDENTE);
		associadoDto.setNomePartido("NomeTeste");
	}
}