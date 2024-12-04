package cooperativa.dao;

import cooperativa.model.Propriedade;
import cooperativa.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropriedadeDAO {

    public void inserir(Propriedade propriedade) throws SQLException {
        String sql = "INSERT INTO propriedade (nome, localizacao, tamanho_ha, id_agricultor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, propriedade.getNome());
            stmt.setString(2, propriedade.getLocalizacao());
            stmt.setDouble(3, propriedade.getTamanhoHa());
            stmt.setInt(4, propriedade.getIdAgricultor());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Propriedade propriedade) throws SQLException {
        String sql = "UPDATE propriedade SET nome = ?, localizacao = ?, tamanho_ha = ?, id_agricultor = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, propriedade.getNome());
            stmt.setString(2, propriedade.getLocalizacao());
            stmt.setDouble(3, propriedade.getTamanhoHa());
            stmt.setInt(4, propriedade.getIdAgricultor());
            stmt.setInt(5, propriedade.getId());
            stmt.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM propriedade WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Propriedade> listar() throws SQLException {
        String sql = "SELECT * FROM propriedade";
        List<Propriedade> propriedades = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Propriedade propriedade = new Propriedade(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("localizacao"),
                        rs.getDouble("tamanho_ha"),
                        rs.getInt("id_agricultor")
                );
                propriedades.add(propriedade);
            }
        }
        return propriedades;
    }
}
