package br.com.fiap.to;

import java.time.LocalDate;

public class PokemonTO {
    private Long codigo;
    private String nome;
    private Double altura;
    private Double peso;
    private String categoria;
    private LocalDate fataDeCaptura;

    public PokemonTO(LocalDate fataDeCaptura, String categoria, Double peso, Double altura, String nome, Long codigo) {
        this.fataDeCaptura = fataDeCaptura;
        this.categoria = categoria;
        this.peso = peso;
        this.altura = altura;
        this.nome = nome;
        this.codigo = codigo;
    }

    public PokemonTO() {

    }

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getAltura() {
        return altura;
    }
    public void setAltura(Double altura) {
        this.altura = altura;
    }
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public LocalDate getFataDeCaptura() {
        return fataDeCaptura;
    }
    public void setFataDeCaptura(LocalDate fataDeCaptura) {
        this.fataDeCaptura = fataDeCaptura;
    }
}
