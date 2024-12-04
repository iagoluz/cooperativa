package cooperativa.dao;

import cooperativa.model.Agricultor;
import cooperativa.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgricultorDAO {

    public void inserir(Agricultor agricultor) throws SQLException {
        String sql = "INSERT INTO agricultor (nome, cpf_cnpj, endereco, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agricultor.getNome());
            stmt.setString(2, agricultor.getCpfCnpj());
            stmt.setString(3, agricultor.getEndereco());
            stmt.setString(4, agricultor.getTelefone());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Agricultor agricultor) throws SQLException {
        String sql = "UPDATE agricultor SET nome = ?, cpf_cnpj = ?, endereco = ?, telefone = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agricultor.getNome());
            stmt.setString(2, agricultor.getCpfCnpj());
            stmt.setString(3, agricultor.getEndereco());
            stmt.setString(4, agricultor.getTelefone());
            stmt.setInt(5, agricultor.getId());
            stmt.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM agricultor WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Agricultor> listar() throws SQLException {
        String sql = "SELECT * FROM agricultor";
        List<Agricultor> agricultores = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Agricultor agricultor = new Agricultor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf_cnpj"),
                        rs.getString("endereco"),
                        rs.getString("telefone")
                );
                agricultores.add(agricultor);
            }
        }
        return agricultores;
    }
}
