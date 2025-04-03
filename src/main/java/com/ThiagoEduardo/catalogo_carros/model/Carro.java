package com.ThiagoEduardo.catalogo_carros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carros")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private int ano;
    private int potencia;
    private double economia;

    @Enumerated(EnumType.STRING)
    private TipoCarro tipo;

    private double preco;

    public Carro() {}

    public Carro(Long id, String marca, String modelo, int ano, int potencia, double economia, TipoCarro tipo, double preco) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.potencia = potencia;
        this.economia = economia;
        this.tipo = tipo;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public int getPotencia() { return potencia; }
    public void setPotencia(int potencia) { this.potencia = potencia; }
    public double getEconomia() { return economia; }
    public void setEconomia(double economia) { this.economia = economia; }
    public TipoCarro getTipo() { return tipo; }
    public void setTipo(TipoCarro tipo) { this.tipo = tipo; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}
