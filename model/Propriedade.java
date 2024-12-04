
package cooperativa.model;

public class Propriedade {
    private int id;
    private String nome;
    private String localizacao;
    private double tamanhoHa;
    private int idAgricultor;

    // Construtor
    public Propriedade(int id, String nome, String localizacao, double tamanhoHa, int idAgricultor) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.tamanhoHa = tamanhoHa;
        this.idAgricultor = idAgricultor;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getTamanhoHa() {
        return tamanhoHa;
    }

    public void setTamanhoHa(double tamanhoHa) {
        this.tamanhoHa = tamanhoHa;
    }

    public int getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(int idAgricultor) {
        this.idAgricultor = idAgricultor;
    }
}
