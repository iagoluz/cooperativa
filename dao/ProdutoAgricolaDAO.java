package cooperativa.dao;

import cooperativa.model.ProdutoAgricola;
import cooperativa.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoAgricolaDAO {

    public void inserir(ProdutoAgricola produto) throws SQLException {
        String sql = "INSERT INTO produto_agricola (nome, tipo, preco, id_propriedade) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getIdPropriedade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(ProdutoAgricola produto) throws SQLException {
        String sql = "UPDATE produto_agricola SET nome = ?, tipo = ?, preco = ?, id_propriedade = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getIdPropriedade());
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM produto_agricola WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<ProdutoAgricola> listar() throws SQLException {
        String sql = "SELECT * FROM produto_agricola";
        List<ProdutoAgricola> produtos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProdutoAgricola produto = new ProdutoAgricola(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getDouble("preco"),
                        rs.getInt("id_propriedade")
                );
                produtos.add(produto);
            }
        }
        return produtos;
    }
}
