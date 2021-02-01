package br.edu.ifsp.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BuildDatabase {

    public void buildDatabaseIfMissing(){
        if(isDatabaseMissing()){
            System.out.println("criando database");
            buidTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buidTables(){
        try(Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createAssentoTrechoLinhaTable());
            statement.addBatch(createFuncionarioTable());
            statement.addBatch(createLinhaTable());
            statement.addBatch(createLoginTable());
            statement.addBatch(createOnibusTable());
            statement.addBatch(createTrechoTable());
            statement.addBatch(createTrechoLinhaTable());
            statement.addBatch(createViagemTable());
            statement.addBatch(createPassagemTable());
            statement.executeBatch();

            System.out.println("base de dados criada");
        } catch (SQLException throwables) {
            System.out.println("throwables.getMessage() = " + throwables.getMessage());;
        }


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
        builder.append("nome text not null\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createLoginTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE USERS (\n");
        builder.append("login text primary key,\n");
        builder.append("senha text not null,\n");
        builder.append("nome text not null\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createOnibusTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE ONIBUS (\n");
        builder.append("renavam text primary key,\n");
        builder.append("placa text not null\n");
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
        builder.append("cpf text not null,\n");
        builder.append("rg text not null,\n");
        builder.append("telefone text not null,\n");
        builder.append("seguro integer not null,\n");
        builder.append("tipoEspecial text not null,\n");
        builder.append("assento text not null,\n");
        builder.append("viagem integer not null,\n");
        builder.append("FOREIGN KEY (viagem) REFERENCES VIAGEM(id)\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createTrechoTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE TRECHO (\n");
        builder.append("id integer primary key,\n");
        builder.append("cidadeOrigem text not null,\n");
        builder.append("nome text not null,\n");
        builder.append("cidadeDestino text not null,\n");
        builder.append("quilometragem integer not null,\n");
        builder.append("tempoDuracao text not null,\n");
        builder.append("valorPassagem integer not null,\n");
        builder.append("taxaEmbarque integer not null,\n");
        builder.append("valorSeguro integer not null\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createTrechoLinhaTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE TRECHOSLINHAS (\n");
        builder.append("id integer primary key,\n");
        builder.append("horarioSaida text not null,\n");
        builder.append("ordem integer not null,\n");
        builder.append("linha integer not null,\n");
        builder.append("assentosTrechoLinha integer not null,\n");
        builder.append("trecho integer not null,\n");
        builder.append("FOREIGN KEY (assentosTrechoLinha) REFERENCES ASSENTOTRECHOLINHA(id),\n");
        builder.append("FOREIGN KEY (linha) REFERENCES LINHA(id),\n");
        builder.append("FOREIGN KEY (trecho) REFERENCES TRECHO(id)\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

    private String createViagemTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE VIAGEM (\n");
        builder.append("id integer primary key,\n");
        builder.append("data text not null,\n");
        builder.append("horarioSaida text not null,\n");
        builder.append("cidadeOrigem text not null,\n");
        builder.append("cidadeDestino text not null,\n");
        builder.append("trechosLinhas integer not null,\n");
        builder.append("linha integer not null,\n");
        builder.append("FOREIGN KEY (trechosLinhas) REFERENCES TRECHOSLINHAS(id),\n");
        builder.append("FOREIGN KEY (linha) REFERENCES LINHA(id)\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }
    private String createAssentoTrechoLinhaTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE ASSENTOTRECHOLINHA (\n");
        builder.append("id integer primary key,\n");
        builder.append("data text not null,\n");
        builder.append("assentosPrefDisponiveis integer not null,\n");
        builder.append("assentosDisponiveis integer not null\n");
        builder.append(")\n");

        System.out.println("builder.toString() = " + builder.toString());
        return builder.toString();
    }

}
