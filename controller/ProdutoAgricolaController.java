package cooperativa.controller;

import cooperativa.dao.ProdutoAgricolaDAO;
import cooperativa.model.ProdutoAgricola;

import java.sql.SQLException;
import java.util.List;

public class ProdutoAgricolaController {
    private ProdutoAgricolaDAO produtoAgricolaDAO;

    public ProdutoAgricolaController() {
        this.produtoAgricolaDAO = new ProdutoAgricolaDAO();
    }

    public void adicionarProduto(ProdutoAgricola produto) throws SQLException {
        produtoAgricolaDAO.inserir(produto);
    }

    public void atualizarProduto(ProdutoAgricola produto) throws SQLException {
        produtoAgricolaDAO.atualizar(produto);
    }

    public void removerProduto(int id) throws SQLException {
        produtoAgricolaDAO.remover(id);
    }

    public List<ProdutoAgricola> listarProdutos() throws SQLException {
        return produtoAgricolaDAO.listar();
    }
}
