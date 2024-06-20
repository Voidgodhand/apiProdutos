package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class CategoriaRepository {
	public List<Categoria> findAll() throws Exception{
		String query = "SELECT id, nome FROM categoria ORDER BY nome ASC";
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		
		List<Categoria> categorias = new ArrayList<>();
		while(resultSet.next())
		{
			Categoria categoria = new Categoria();
			categoria.setId(resultSet.getInt("id"));
			categoria.setNome(resultSet.getString("nome"));
			
			categorias.add(categoria);
		}
		connection.close();
		return categorias;
	}
}
