package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	@GetMapping
	public List<CategoriaResponseDto> getAll() throws Exception {
		
		//consultando todas as categorias do banco de dados
		CategoriaRepository categoriaRepository = new CategoriaRepository();
		List<Categoria> categorias = categoriaRepository.findAll();
		
		
		//copiando as informações de cada categoria do banco de dados
		//para uma lista que irá retornar os outros DTO da resposta
		List<CategoriaResponseDto> response = new ArrayList<>();
		for(Categoria categoria : categorias) {
			CategoriaResponseDto dto = new CategoriaResponseDto();
			dto.setId(categoria.getId());
			dto.setNome(categoria.getNome());
			
			response.add(dto);
		}
		
		//retornar a lista do dto (response)
		return response;
	}
}