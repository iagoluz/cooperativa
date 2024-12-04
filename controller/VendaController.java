package cooperativa.controller;

import cooperativa.dao.VendaDAO;
import cooperativa.dao.VendaProdutoDAO;
import cooperativa.model.Venda;
import cooperativa.model.VendaProduto;

import java.sql.SQLException;
import java.util.List;

public class VendaController {
    private VendaDAO vendaDAO;
    private VendaProdutoDAO vendaProdutoDAO;

    public VendaController() {
        this.vendaDAO = new VendaDAO();
        this.vendaProdutoDAO = new VendaProdutoDAO();
    }

    public void adicionarVenda(Venda venda) throws SQLException {
        vendaDAO.inserir(venda);
    }

    public void atualizarVenda(Venda venda) throws SQLException {
        vendaDAO.atualizar(venda);
    }

    public void removerVenda(int id) throws SQLException {
        vendaProdutoDAO.removerProdutosPorVenda(id); // Remove os produtos associados à venda
        vendaDAO.remover(id);
    }

    public List<Venda> listarVendas() throws SQLException {
        return vendaDAO.listar();
    }

    // Métodos para gerenciar produtos em uma venda
    public void adicionarProdutoAVenda(VendaProduto vendaProduto) throws SQLException {
        vendaProdutoDAO.inserir(vendaProduto);
    }

    public void atualizarProdutoDeVenda(VendaProduto vendaProduto) throws SQLException {
        vendaProdutoDAO.atualizar(vendaProduto);
    }

    public void removerProdutoDeVenda(int id) throws SQLException {
        vendaProdutoDAO.remover(id);
    }

    /*public List<VendaProduto> listarProdutosPorVenda(int idVenda) throws SQLException {
        return vendaProdutoDAO.listarProdutosPorVenda(idVenda);
    }*/
    public boolean verificarClienteExistente(int idCliente) throws SQLException {
    VendaDAO vendaDAO = new VendaDAO();
    return vendaDAO.verificarClienteExistente(idCliente);
}

}
