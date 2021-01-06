package br.edu.ifsp.domain.entities.funcionario;

public class Funcionario {
    private String cpf;
    private String nome;
    private String rg;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(String cpf, String nome, String rg, String cargo) {
        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", rg='" + rg + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
