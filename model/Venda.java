package model;

import java.util.Date;

public class Venda {
    private int id;
    private Date data;
    private double valorTotal;
    private int idCliente;

    // Construtor
    public Venda(int id, Date data, double valorTotal, int idCliente) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.idCliente = idCliente;
    }
public Venda(java.util.Date data, double valorTotal, int clienteId) {
    this.data = data;
    this.valorTotal = valorTotal;
    this.idCliente = idCliente;
}

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
