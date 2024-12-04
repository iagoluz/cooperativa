
package cooperativa.util;

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
    public static String gerarRelatorioVendasDetalhado() throws SQLException {
    String sql = """
        SELECT 
            cliente.nome AS cliente_nome,
            produto_agricola.nome AS produto_nome,
            venda_produto.quantidade,
            venda_produto.preco,
            (venda_produto.quantidade * venda_produto.preco) AS total
        FROM venda_produto
        JOIN produto_agricola ON venda_produto.id_produto = produto_agricola.id
        JOIN venda ON venda_produto.id_venda = venda.id
        JOIN cliente ON venda.id_cliente = cliente.id
        ORDER BY cliente.nome, produto_agricola.nome
    """;
    StringBuilder resultado = new StringBuilder("Relatório Detalhado de Vendas:\n");

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            resultado.append("Cliente: ").append(rs.getString("cliente_nome"))
                     .append(" | Produto: ").append(rs.getString("produto_nome"))
                     .append(" | Quantidade: ").append(rs.getDouble("quantidade"))
                     .append(" | Preço Unitário: R$ ").append(rs.getDouble("preco"))
                     .append(" | Total: R$ ").append(rs.getDouble("total"))
                     .append("\n");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao gerar relatório detalhado de vendas: " + e.getMessage());
        throw e;
    }

    return resultado.toString();
}
public static String gerarRelatorioEstoqueProdutos() throws SQLException {
    String sql = """
        SELECT 
            produto_agricola.nome AS produto_nome,
            produto_agricola.preco AS preco_unitario, -- Adicionar o preço unitário diretamente
            SUM(venda_produto.quantidade) AS vendido,
            (produto_agricola.preco * SUM(venda_produto.quantidade)) AS total_vendido
        FROM venda_produto
        JOIN produto_agricola ON venda_produto.id_produto = produto_agricola.id
        GROUP BY produto_agricola.nome, produto_agricola.preco -- Adicionar preco no GROUP BY
        ORDER BY produto_nome
    """;
    StringBuilder resultado = new StringBuilder("Relatório de Estoque e Vendas de Produtos:\n");

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            resultado.append("Produto: ").append(rs.getString("produto_nome"))
                     .append(" | Preço Unitário: R$ ").append(rs.getDouble("preco_unitario")) // Exibir o preço unitário
                     .append(" | Total Vendido: ").append(rs.getDouble("vendido"))
                     .append(" | Valor Total Vendido: R$ ").append(rs.getDouble("total_vendido"))
                     .append("\n");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao gerar relatório de estoque e vendas de produtos: " + e.getMessage());
        throw e;
    }

    return resultado.toString();
}

}


