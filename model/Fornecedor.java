package model;

public class Fornecedor {
    private int id;
    private String nome;
    private String produtoFornecido;
    private String cnpj;
    private String telefone;

    // Construtor
    public Fornecedor(int id, String nome, String produtoFornecido, String cnpj, String telefone) {
        this.id = id;
        this.nome = nome;
        this.produtoFornecido = produtoFornecido;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProdutoFornecido() {
        return produtoFornecido;
    }

    public void setProdutoFornecido(String produtoFornecido) {
        this.produtoFornecido = produtoFornecido;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
