package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ProdutoRepository {
	public void create(Produto produto) throws Exception{
		String query = "INSERT INTO produto(nome, preco, quantidade, categoria_id) VALUES (?, ?, ?, ?)";
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.setInt(4, produto.getCategoria().getId());
		statement.execute();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		if(resultSet.next()) {
			produto.setId(resultSet.getInt(1));
		}
		
		connection.close();
	}
	
	public void update(Produto produto) throws Exception{
		String query = "UPDATE produto SET nome=?, preco=?, quantidade=?, categoria_id=? WHERE id=?";
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.setInt(4, produto.getCategoria().getId());
		statement.setInt(5, produto.getId());
		statement.execute();
		
		connection.close();
	}
	
	public void delete(Integer id) throws Exception{
		String query = "DELETE FROM produto WHERE id=?";
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.execute();
		
		connection.close();
	}
	
	public List<Produto> findAll() throws Exception{
		String query = "SELECT p.id AS idproduto, p.nome AS nomeproduto, p.preco, p.quantidade, "
				+ "c.id AS idcategoria, c.nome AS nomecategoria FROM produto INNER JOIN categoria "
				+ "ON c.id = p.categoria_id ORDER BY p.nome ASC";
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		
		List<Produto> produtos = new ArrayList<>();
		while (resultSet.next());
		{
			Produto produto = new Produto();
			produto.setCategoria(new Categoria());
			
			produto.setId(resultSet.getInt("idproduto"));
			produto.setNome(resultSet.getString("nomeproduto"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.getCategoria().setId(resultSet.getInt("idcategoria"));
			produto.getCategoria().setNome(resultSet.getString("nomecategoria"));
			
			produtos.add(produto);
		}
		connection.close();
		return produtos;
	}
	
	public Produto findById(Integer id) throws Exception{
		String query = "SELECT p.id AS idproduto, p.nome AS nomeproduto, p.preco, p.quantidade, "
				+ "c.id AS idcategoria, c.nome AS nomecategoria FROM produto INNER JOIN categoria "
				+ "ON c.id = p.categoria_id WHERE p.id = ?";
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Produto produto = null;
		if (resultSet.next());
		{
			produto = new Produto();
			produto.setCategoria(new Categoria());
			
			produto.setId(resultSet.getInt("idproduto"));
			produto.setNome(resultSet.getString("nomeproduto"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.getCategoria().setId(resultSet.getInt("idcategoria"));
			produto.getCategoria().setNome(resultSet.getString("nomecategoria"));
		}
		connection.close();
		return produto;
	}
}
