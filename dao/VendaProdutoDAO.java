package cooperativa.dao;

import cooperativa.model.VendaProduto;
import cooperativa.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaProdutoDAO {

    public void inserir(VendaProduto vendaProduto) throws SQLException {
        String sql = "INSERT INTO venda_produto (id_venda, id_produto, quantidade, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, vendaProduto.getIdVenda());
            stmt.setInt(2, vendaProduto.getIdProduto());
            stmt.setDouble(3, vendaProduto.getQuantidade());
            stmt.setDouble(4, vendaProduto.getPreco());
            stmt.executeUpdate();
        }
    }

    public void atualizar(VendaProduto vendaProduto) throws SQLException {
        String sql = "UPDATE venda_produto SET quantidade = ?, preco = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, vendaProduto.getQuantidade());
            stmt.setDouble(2, vendaProduto.getPreco());
            stmt.setInt(3, vendaProduto.getIdProduto());
            stmt.executeUpdate();
        }
    }
public List<VendaProduto> listarPorVenda(int idVenda) throws SQLException {
    String sql = "SELECT vp.id_produto, p.nome, vp.quantidade, vp.preco " +
                 "FROM venda_produto vp " +
                 "JOIN produto_agricola p ON vp.id_produto = p.id " +
                 "WHERE vp.id_venda = ?";
    List<VendaProduto> produtos = new ArrayList<>();
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idVenda);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VendaProduto produto = new VendaProduto(
                        rs.getInt("id_produto"),
                        idVenda,
                        rs.getString("nome"), // Nome do produto
                        rs.getDouble("quantidade"),
                        rs.getDouble("preco")
                );
                produtos.add(produto);
            }
        }
    }
    return produtos;
}

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM venda_produto WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void removerProdutosPorVenda(int idVenda) throws SQLException {
        String sql = "DELETE FROM venda_produto WHERE id_venda = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVenda);
            stmt.executeUpdate();
        }
    }

  /*  public List<VendaProduto> listarPorVenda(int idVenda) throws SQLException {
        String sql = "SELECT * FROM venda_produto WHERE id_venda = ?";
        List<VendaProduto> produtos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVenda);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VendaProduto vendaProduto = new VendaProduto(
                            rs.getInt("id"),
                            rs.getInt("id_venda"),
                            rs.getInt("id_produto"),
                            rs.getDouble("quantidade"),
                            rs.getDouble("preco")
                    );
                    produtos.add(vendaProduto);
                }
            }
        }
        return produtos;
    }
    */
}
