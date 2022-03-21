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
import br.com.compass.PartidosPoliticos.dto.AssociadosDto;
import br.com.compass.PartidosPoliticos.entities.Associados;
import br.com.compass.PartidosPoliticos.entities.Partidos;
import br.com.compass.PartidosPoliticos.entities.enums.CargosPoliticos;
import br.com.compass.PartidosPoliticos.entities.enums.Ideologia;
import br.com.compass.PartidosPoliticos.entities.enums.Sexo;
import br.com.compass.PartidosPoliticos.repositorys.AssociadosRepository;
import br.com.compass.PartidosPoliticos.repositorys.PartidosRepository;
import br.com.compass.PartidosPoliticos.service.exception.EntityNotFoundException;
import br.com.compass.PartidosPoliticos.service.exception.MethodArgumentNotValidException;

@SpringBootTest
public class AssociadosServiceTest {

	@InjectMocks
	private AssociadosService service;

	@Mock
	private AssociadosRepository repository;

	@Mock
	private PartidosRepository partidoRepository;

	@Mock
	private Associados associado;

	@Mock
	private AssociadosDto associadosDto;

	@Mock
	private AssociadoComPartidoDto associadoComPartidoDto;

	private Optional<Associados> optionalAssociado;

	private Optional<Partidos> optionalPartido;

	@Mock
	private Partidos partido;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startAssociados();
		startAssociadoComPartidoDto();
		startAssociadosDto();
		startOptionalAssociado();
		startPartido();
		startOptionalPartido();
	}

	@Test
	void deveriaRetornarUmaListaDeAssociadoAoPassarCargoPolitico() {
		Mockito.when(repository.findByCargosPoliticos(Mockito.any())).thenReturn(List.of(associado));
		List<Associados> response = service.findByCargoPolitico(CargosPoliticos.PRESIDENTE);

		assertNotNull(response);
		assertEquals(Associados.class, response.get(0).getClass());
		assertEquals(1, response.get(0).getId());
		assertEquals("NomeTeste", response.get(0).getNome());
		assertEquals(CargosPoliticos.PRESIDENTE, response.get(0).getCargosPoliticos());
		assertEquals(Sexo.FEMININO, response.get(0).getSexo());
	}

	@Test
	void deveriaRetornarUmaListaDeAssociadoFindAll() {
		Mockito.when(repository.findAll()).thenReturn(List.of(associado));
		List<Associados> response = service.findAll();

		assertNotNull(response);
		assertEquals(Associados.class, response.get(0).getClass());
		assertEquals(1, response.get(0).getId());
		assertEquals("NomeTeste", response.get(0).getNome());
		assertEquals(CargosPoliticos.PRESIDENTE, response.get(0).getCargosPoliticos());
		assertEquals(Sexo.FEMININO, response.get(0).getSexo());
	}

	@Test
	void deveriaRetornarUmAssociadoAoPassarId() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalAssociado);
		Associados response = service.findById(1);

		assertNotNull(response);
		assertEquals(Associados.class, response.getClass());
		assertEquals(1, response.getId());
		assertEquals("NomeTeste", response.getNome());
		assertEquals(CargosPoliticos.PRESIDENTE, response.getCargosPoliticos());
		assertEquals(Sexo.FEMININO, response.getSexo());
	}

	@Test
	void deveriaRetornarEntityNotFoundException() {
		Mockito.when(repository.findById(Mockito.anyLong()))
				.thenThrow(new EntityNotFoundException("Id não encontrado."));

		try {
			service.findById(1);
		} catch (EntityNotFoundException e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals("Id não foi encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaRetornarMethodArgumentNotValidException() {
		Mockito.when(repository.findById(Mockito.anyLong()))
				.thenThrow(new MethodArgumentNotValidException("Dados inválidos."));
		try {
			service.updateById((long) 1, associado);
		} catch (MethodArgumentNotValidException e) {
			assertEquals(MethodArgumentNotValidException.class, e.getClass());
			assertEquals("Dados inválidos.", e.getMessage());
		}
	}

	@Test
	void deveriaCriarUmNovoAssociado() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(associado);

		Associados response = service.save(associado);

		assertNotNull(response);
		assertEquals(Associados.class, response.getClass());
		assertEquals(1, response.getId());
		assertEquals("NomeTeste", response.getNome());
		assertEquals(CargosPoliticos.PRESIDENTE, response.getCargosPoliticos());
		assertEquals(Sexo.FEMININO, response.getSexo());
	}

	@Test
	void deveriaAssociarAUmPartidoERetornarUmAssociadoComPartidoDto() {
		Mockito.when(partidoRepository.findById(Mockito.anyLong())).thenReturn(optionalPartido);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalAssociado);

		AssociadoComPartidoDto response = service.associarPartido(associadosDto);

		assertNotNull(response);
		assertEquals(AssociadoComPartidoDto.class, response.getClass());
		assertEquals(1, response.getId());
		assertEquals("NomeTeste", response.getNome());
		assertEquals(CargosPoliticos.PRESIDENTE, response.getCargosPoliticos());
	}

	@Test
	void deveriaDeletarUmAssociadoComSucesso() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalAssociado);
		Mockito.doNothing().when(repository).deleteById(Mockito.anyLong());
		service.deleteById(1);

		Mockito.verify(repository, times(1)).deleteById(Mockito.anyLong());
	}

	private void startOptionalAssociado() {
		associado = new Associados();
		associado.setId((long) 1);
		associado.setNome("NomeTeste");
		associado.setCargosPoliticos(CargosPoliticos.PRESIDENTE);
		associado.setSexo(Sexo.FEMININO);
		associado.setDataNascimento(LocalDate.now());
		optionalAssociado = Optional.of(associado);
	}

	private void startAssociados() {
		associado = new Associados();
		associado.setId((long) 1);
		associado.setNome("NomeTeste");
		associado.setCargosPoliticos(CargosPoliticos.PRESIDENTE);
		associado.setSexo(Sexo.FEMININO);
		associado.setDataNascimento(LocalDate.now());
	}

	private void startAssociadoComPartidoDto() {
		associadoComPartidoDto = new AssociadoComPartidoDto();
		associadoComPartidoDto.setId((long) 1);
		associadoComPartidoDto.setNome("NomeTeste");
		associadoComPartidoDto.setCargosPoliticos(CargosPoliticos.PRESIDENTE);
		associadoComPartidoDto.setNomePartido("NomeTeste");
	}

	private void startAssociadosDto() {
		associadosDto = new AssociadosDto();
		associadosDto.setIdAssociado((long) 1);
		associadosDto.setIdPartido((long) 1);
	}

	private void startPartido() {
		partido = new Partidos();
		partido.setId((long) 1);
		partido.setNomePartido("NomeTeste");
		partido.setSigla("NT");
		partido.setDataDeFundacao(LocalDate.now());
		partido.setIdeologia(Ideologia.ESQUERDA);
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
}
