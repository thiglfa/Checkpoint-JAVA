package com.ThiagoEduardo.catalogo_carros.controller;
import com.ThiagoEduardo.catalogo_carros.model.Carro;
import com.ThiagoEduardo.catalogo_carros.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public List<Carro> listarTodos() {
        return carroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        return carroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carro> criar(@RequestBody Carro carro) {
        Carro novoCarro = carroService.salvar(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        return carroService.atualizar(id, carroAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return carroService.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/potencia")
    public List<Carro> listarPorPotencia() {
        return carroService.listarPorPotencia();
    }

    @GetMapping("/economia")
    public List<Carro> listarPorEconomia() {
        return carroService.listarPorEconomia();
    }

    @GetMapping("/eletricos")
    public List<Carro> listarEletricos() {
        return carroService.listarEletricos();
    }
}