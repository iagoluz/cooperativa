    package dao;

    import model.Venda;
    import util.ConnectionFactory;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class VendaDAO {
public void inserir(Venda venda) throws SQLException {
    String sql = "INSERT INTO venda (data, valor_total, id_cliente) VALUES (?, ?, ?)";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setDate(1, new java.sql.Date(venda.getData().getTime()));
        stmt.setDouble(2, venda.getValorTotal());
        stmt.setInt(3, venda.getIdCliente());
        stmt.executeUpdate();
    }
}

public void atualizar(Venda venda) throws SQLException {
    String sql = "UPDATE venda SET data = ?, valor_total = ?, id_cliente = ? WHERE id = ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setDate(1, new java.sql.Date(venda.getData().getTime()));
        stmt.setDouble(2, venda.getValorTotal());
        stmt.setInt(3, venda.getIdCliente());
        stmt.setInt(4, venda.getId());
        stmt.executeUpdate();
    }
}


        public void remover(int id) throws SQLException {
            String sql = "DELETE FROM venda WHERE id = ?";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }

        public List<Venda> listar() throws SQLException {
            String sql = "SELECT * FROM venda";
            List<Venda> vendas = new ArrayList<>();
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda(
                            rs.getInt("id"),
                            rs.getDate("data"),
                            rs.getDouble("valor_total"),
                            rs.getInt("id_cliente")
                    );
                    vendas.add(venda);
                }
            }
            return vendas;
        }
    }
