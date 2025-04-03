package com.ThiagoEduardo.catalogo_carros;

import com.ThiagoEduardo.catalogo_carros.model.Carro;
import com.ThiagoEduardo.catalogo_carros.model.TipoCarro;
import com.ThiagoEduardo.catalogo_carros.repository.CarroRepository;
import com.ThiagoEduardo.catalogo_carros.service.CarroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CarroServiceTest {

    @Mock
    private CarroRepository carroRepository;

    @InjectMocks
    private CarroService carroService;

    private Carro carro1;
    private Carro carro2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        carro1 = new Carro(1L, "Toyota", "Corolla", 2023, 150, 12.5, TipoCarro.COMBUSTAO, 120000);
        carro2 = new Carro(2L, "Tesla", "Model 3", 2024, 300, 5.0, TipoCarro.ELETRICO, 250000);
    }

    @Test
    void deveRetornarTodosOsCarros() {
        when(carroRepository.findAll()).thenReturn(Arrays.asList(carro1, carro2));

        List<Carro> carros = carroService.listarTodos();

        assertThat(carros).hasSize(2);
        verify(carroRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarCarroPorIdSeExistir() {
        when(carroRepository.findById(1L)).thenReturn(Optional.of(carro1));

        Optional<Carro> resultado = carroService.buscarPorId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getMarca()).isEqualTo("Toyota");
    }

    @Test
    void deveLancarExcecaoSeCarroNaoExistir() {
        when(carroRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Carro> resultado = carroService.buscarPorId(999L);

        assertThat(resultado).isEmpty();
    }

    @Test
    void deveAdicionarUmNovoCarro() {
        when(carroRepository.save(carro1)).thenReturn(carro1);

        Carro carroSalvo = carroService.salvar(carro1);

        assertThat(carroSalvo).isNotNull();
        assertThat(carroSalvo.getModelo()).isEqualTo("Corolla");
        verify(carroRepository, times(1)).save(carro1);
    }
}
