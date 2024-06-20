package br.com.cotiinformatica.entities;

import java.util.List;

import lombok.Data;

@Data
public class Categoria {
	private Integer id;
	private String nome;
	private List<Produto> produtos;
}