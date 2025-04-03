package com.ThiagoEduardo.catalogo_carros;
import com.ThiagoEduardo.catalogo_carros.model.Carro;
import com.ThiagoEduardo.catalogo_carros.model.TipoCarro;
import com.ThiagoEduardo.catalogo_carros.repository.CarroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarroControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarroRepository carroRepository;

    private Carro carro;

    @BeforeEach
    void setUp() {
        carroRepository.deleteAll();  // Limpa o banco antes de cada teste

        carro = new Carro(null, "Toyota", "Corolla", 2023, 150, 12.5, TipoCarro.COMBUSTAO, 120000);
        carroRepository.save(carro);
    }

    @Test
    public void deveListarTodosOsCarros() {
        ResponseEntity<Carro[]> response = restTemplate.getForEntity("/carros", Carro[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getMarca()).isEqualTo("Toyota");
    }

    @Test
    public void deveBuscarCarroPorId() {
        ResponseEntity<Carro> response = restTemplate.getForEntity("/carros/" + carro.getId(), Carro.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMarca()).isEqualTo("Toyota");
    }

    @Test
    public void deveRetornar404SeCarroNaoForEncontrado() {
        ResponseEntity<Carro> response = restTemplate.getForEntity("/carros/999", Carro.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deveCriarUmNovoCarro() {
        Carro novoCarro = new Carro(null, "Honda", "Civic", 2024, 160, 13.0, TipoCarro.COMBUSTAO, 130000);
        ResponseEntity<Carro> response = restTemplate.postForEntity("/carros", novoCarro, Carro.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getMarca()).isEqualTo("Honda");

        List<Carro> carros = carroRepository.findAll();
        assertThat(carros).hasSize(2);
    }

    @Test
    public void deveAtualizarCarroExistente() {
        carro.setModelo("Corolla XRS");

        restTemplate.put("/carros/" + carro.getId(), carro);

        Carro carroAtualizado = carroRepository.findById(carro.getId()).orElse(null);
        assertThat(carroAtualizado).isNotNull();
        assertThat(carroAtualizado.getModelo()).isEqualTo("Corolla XRS");
    }

    @Test
    public void deveDeletarCarroExistente() {
        restTemplate.delete("/carros/" + carro.getId());

        assertThat(carroRepository.findById(carro.getId())).isEmpty();
    }
}
