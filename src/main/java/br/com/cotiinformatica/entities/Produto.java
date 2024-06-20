package br.com.cotiinformatica.entities;

import lombok.Data;

@Data
public class Produto {
	private Integer id;
	private String nome;
	private Double preco;
	private Integer quantidade;
	private Categoria categoria;
}