package cooperativa.controller;

import cooperativa.dao.VendaProdutoDAO;
import cooperativa.model.VendaProduto;

import java.sql.SQLException;
import java.util.List;

public class VendaProdutoController {
    private VendaProdutoDAO vendaProdutoDAO;

    public VendaProdutoController() {
        this.vendaProdutoDAO = new VendaProdutoDAO();
    }

    // Adiciona um produto à venda
    public void adicionarProdutoAVenda(VendaProduto vendaProduto) throws SQLException {
        vendaProdutoDAO.inserir(vendaProduto);
    }

    // Remove um produto da venda
    public void removerProdutoDeVenda(int idVendaProduto) throws SQLException {
        vendaProdutoDAO.remover(idVendaProduto);
    }

    // Lista os produtos de uma venda
    public List<VendaProduto> listarProdutosPorVenda(int idVenda) throws SQLException {
        return vendaProdutoDAO.listarPorVenda(idVenda);
    }
    
}
