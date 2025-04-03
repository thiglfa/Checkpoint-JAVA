package com.ThiagoEduardo.catalogo_carros.repository;

import com.ThiagoEduardo.catalogo_carros.model.Carro;
import com.ThiagoEduardo.catalogo_carros.model.TipoCarro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findTop10ByOrderByPotenciaDesc();

    List<Carro> findTop10ByOrderByEconomiaDesc();

    List<Carro> findByTipo(TipoCarro tipo);
}

