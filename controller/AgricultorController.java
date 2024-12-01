package controller;

import dao.AgricultorDAO;
import model.Agricultor;

import java.sql.SQLException;
import java.util.List;

public class AgricultorController {
    private AgricultorDAO agricultorDAO;

    public AgricultorController() {
        this.agricultorDAO = new AgricultorDAO();
    }

    public void adicionarAgricultor(Agricultor agricultor) throws SQLException {
        agricultorDAO.inserir(agricultor);
    }

    public void atualizarAgricultor(Agricultor agricultor) throws SQLException {
        agricultorDAO.atualizar(agricultor);
    }

    public void removerAgricultor(int id) throws SQLException {
        agricultorDAO.remover(id);
    }

    public List<Agricultor> listarAgricultores() throws SQLException {
        return agricultorDAO.listar();
    }
}
