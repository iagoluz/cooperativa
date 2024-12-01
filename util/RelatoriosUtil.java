package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatoriosUtil {

    public static String gerarRelatorioVendasPorCliente() throws SQLException {
        String sql = "SELECT cliente.nome, SUM(venda.valor_total) AS total FROM venda " +
                     "JOIN cliente ON venda.id_cliente = cliente.id GROUP BY cliente.nome";
        StringBuilder resultado = new StringBuilder();
        
        // Recursos JDBC
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.append("Cliente: ").append(rs.getString("nome"))
                         .append(" | Total em Vendas: R$ ").append(rs.getDouble("total"))
                         .append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório de vendas por cliente: " + e.getMessage());
            throw e;
        }

        return resultado.toString();
    }

    public static String gerarRelatorioPropriedades() throws SQLException {
        String sql = "SELECT agricultor.nome, COUNT(propriedade.id) AS total FROM propriedade " +
                     "JOIN agricultor ON propriedade.id_agricultor = agricultor.id GROUP BY agricultor.nome";
        StringBuilder resultado = new StringBuilder();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.append("Agricultor: ").append(rs.getString("nome"))
                         .append(" | Total de Propriedades: ").append(rs.getInt("total"))
                         .append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório de propriedades: " + e.getMessage());
            throw e;
        }

        return resultado.toString();
    }

    public static String gerarRelatorioProdutosAgricolas() throws SQLException {
        String sql = "SELECT nome, COUNT(id) AS quantidade FROM produto_agricola GROUP BY nome";
        StringBuilder resultado = new StringBuilder();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.append("Produto: ").append(rs.getString("nome"))
                         .append(" | Quantidade: ").append(rs.getInt("quantidade"))
                         .append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório de produtos agrícolas: " + e.getMessage());
            throw e;
        }

        return resultado.toString();
    }
}
