package controller;

import dao.PropriedadeDAO;
import model.Propriedade;

import java.sql.SQLException;
import java.util.List;

public class PropriedadeController {
    private PropriedadeDAO propriedadeDAO;

    public PropriedadeController() {
        this.propriedadeDAO = new PropriedadeDAO();
    }

    public void adicionarPropriedade(Propriedade propriedade) throws SQLException {
        propriedadeDAO.inserir(propriedade);
    }

    public void atualizarPropriedade(Propriedade propriedade) throws SQLException {
        propriedadeDAO.atualizar(propriedade);
    }

    public void removerPropriedade(int id) throws SQLException {
        propriedadeDAO.remover(id);
    }

    public List<Propriedade> listarPropriedades() throws SQLException {
        return propriedadeDAO.listar();
    }
}
