package br.edu.ifsp.application.repository.sqlite;

public class BuildDatabase {

    private void buidTables(){
        createFuncionarioTable();
        createLinhaTable();
        createLoginTable();
        createOnibusTable();
        createTrechoLinhaTable();
        createTrechoTable();
        createViagemTable();
        createPassagemTable();
        createAssentoTrechoLinhaTable();
    }

    private String createFuncionarioTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE FUNCIONARIO (\n");
        builder.append("cpf text primary key,\n");
        builder.append("nome text not null,\n");
        builder.append("rg text not null,\n");
        builder.append("cargo text not null\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createLinhaTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE LINHA (\n");
        builder.append("id integer primary key AUTOINCREMENT,\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createLoginTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE USERS (\n");
        builder.append("login text primary key,\n");
        builder.append("senha text not null,\n");
        builder.append("nome text not null,\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createOnibusTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE ONIBUS (\n");
        builder.append("renavam text primary key,\n");
        builder.append("placa text not null,\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createPassagemTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE PASSAGEM (\n");
        builder.append("numPassagem integer primary key,\n");
        builder.append("precoTotal integer not null,\n");
        builder.append("nome text not null,\n");
        builder.append("cpf text not null\n");
        builder.append("rg text not null\n");
        builder.append("telefone text not null\n");
        builder.append("seguro integer not null\n");
        builder.append("seguro integer not null\n");
        builder.append("tipoEspecial text not null\n");
        builder.append("assento text not null\n");
        builder.append("viagem integer not null\n");
        builder.append("FOREIGN KEY (viagem) REFERENCES VIAGEM(id)\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createTrechoTable(){
        return null;
    }
    private String createTrechoLinhaTable(){
        return null;
    }
    private String createViagemTable(){
        return null;
    }
    private String createAssentoTrechoLinhaTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE ASSENTOTRECHOLINHA (\n");
        builder.append("id integer primary key,\n");
        builder.append("data text not null,\n");
        builder.append("nome text not null,\n");
        builder.append("cpf text not null\n");
        builder.append("rg text not null\n");
        builder.append("telefone text not null\n");
        builder.append("seguro integer not null\n");
        builder.append("seguro integer not null\n");
        builder.append("tipoEspecial text not null\n");
        builder.append("assento text not null\n");
        builder.append("viagem integer not null\n");
        builder.append("FOREIGN KEY (viagem) REFERENCES VIAGEM(id)\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }
}
