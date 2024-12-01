package controller;

import dao.VendaDAO;
import model.Venda;

import java.sql.SQLException;
import java.util.List;

public class VendaController {
    private VendaDAO vendaDAO;

    public VendaController() {
        this.vendaDAO = new VendaDAO();
    }

    public void adicionarVenda(Venda venda) throws SQLException {
        vendaDAO.inserir(venda);
    }

    public void atualizarVenda(Venda venda) throws SQLException {
        vendaDAO.atualizar(venda);
    }

    public void removerVenda(int id) throws SQLException {
        vendaDAO.remover(id);
    }

    public List<Venda> listarVendas() throws SQLException {
        return vendaDAO.listar();
    }
}
