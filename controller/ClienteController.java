package cooperativa.controller;

import cooperativa.dao.ClienteDAO;
import cooperativa.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void adicionarCliente(Cliente cliente) throws SQLException {
        clienteDAO.inserir(cliente);
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        clienteDAO.atualizar(cliente);
    }

    public void removerCliente(int id) throws SQLException {
        clienteDAO.remover(id);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listar();
    }
}
