package com.ThiagoEduardo.catalogo_carros.service;
import com.ThiagoEduardo.catalogo_carros.model.Carro;
import com.ThiagoEduardo.catalogo_carros.model.TipoCarro;
import com.ThiagoEduardo.catalogo_carros.repository.CarroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Optional<Carro> buscarPorId(Long id) {
        return carroRepository.findById(id);
    }

    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    public Optional<Carro> atualizar(Long id, Carro carroAtualizado) {
        return carroRepository.findById(id).map(carro -> {
            carro.setMarca(carroAtualizado.getMarca());
            carro.setModelo(carroAtualizado.getModelo());
            carro.setAno(carroAtualizado.getAno());
            carro.setPotencia(carroAtualizado.getPotencia());
            carro.setEconomia(carroAtualizado.getEconomia());
            carro.setTipo(carroAtualizado.getTipo());
            carro.setPreco(carroAtualizado.getPreco());
            return carroRepository.save(carro);
        });
    }

    public boolean deletar(Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Carro> listarPorPotencia() {
        return carroRepository.findTop10ByOrderByPotenciaDesc();
    }

    public List<Carro> listarPorEconomia() {
        return carroRepository.findTop10ByOrderByEconomiaDesc();
    }

    public List<Carro> listarEletricos() {
        return carroRepository.findByTipo(TipoCarro.ELETRICO);
    }
}


