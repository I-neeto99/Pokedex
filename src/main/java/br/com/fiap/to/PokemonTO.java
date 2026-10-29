package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PokemonTO {

    @NotNull(message = "O codigo e obrigatorio")
    @Positive(message = "O codigo deve ser maior que zero")
    private Long codigo;

    @NotBlank(message = "O nome e obrigatorio")
    @Size(max = 50, message = "O nome deve ter no maximo 50 caracteres")
    private String nome;

    @NotNull(message = "A altura e obrigatoria")
    @Positive(message = "A altura deve ser maior que zero")
    private Double altura;

    @NotNull(message = "O peso e obrigatorio")
    @Positive(message = "O peso deve ser maior que zero")
    private Double peso;

    @NotBlank(message = "A categoria e obrigatoria")
    @Size(max = 50, message = "A categoria deve ter no maximo 50 caracteres")
    private String categoria;

    @NotNull(message = "A data da captura e obrigatoria")
    @Past(message = "A data da captura deve ser uma data no passado")
    private LocalDate dataDaCaptura;

    public PokemonTO() {}

    public PokemonTO(Long codigo, String nome, Double altura, Double peso, String categoria, LocalDate dataDaCaptura) {
        this.codigo = codigo;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.categoria = categoria;
        this.dataDaCaptura = dataDaCaptura;
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

    public LocalDate getDataDaCaptura() {
        return dataDaCaptura;
    }

    public void setDataDaCaptura(LocalDate dataDaCaptura) {
        this.dataDaCaptura = dataDaCaptura;
    }
}
