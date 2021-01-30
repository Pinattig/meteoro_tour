package br.edu.ifsp.domain.entities.vendedor;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.linha.Linha;

import java.util.List;

public class Administrador extends Vendedor {
    private String login;
    private String senha;

    private List<Funcionario> funcionarios;
    private List<Onibus> onibus;
    private List<Relatorio> relatorios;
    private List<Trecho> trechos;
    private List<Linha> linhas;

    public Administrador() {
    }

    public Administrador(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public Boolean validateSenha(String senha){
        return this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", funcionarios=" + funcionarios +
                ", onibus=" + onibus +
                ", relatorios=" + relatorios +
                ", trechos=" + trechos +
                ", linhas=" + linhas +
                "} " + super.toString();
    }
}
