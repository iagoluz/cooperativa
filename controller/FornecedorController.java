package cooperativa.controller;

import cooperativa.dao.FornecedorDAO;
import cooperativa.model.Fornecedor;

import java.sql.SQLException;
import java.util.List;

public class FornecedorController {
    private FornecedorDAO fornecedorDAO;

    public FornecedorController() {
        this.fornecedorDAO = new FornecedorDAO();
    }

    public void adicionarFornecedor(Fornecedor fornecedor) throws SQLException {
        fornecedorDAO.inserir(fornecedor);
    }

    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
        fornecedorDAO.atualizar(fornecedor);
    }

    public void removerFornecedor(int id) throws SQLException {
        fornecedorDAO.remover(id);
    }

    public List<Fornecedor> listarFornecedores() throws SQLException {
        return fornecedorDAO.listar();
    }
}
