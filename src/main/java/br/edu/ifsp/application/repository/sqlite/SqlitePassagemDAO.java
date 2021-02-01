package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.passagem.TipoEspecial;
import br.edu.ifsp.domain.usecases.passagem.PassagemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.edu.ifsp.application.main.Main.gerarViagemUseCase;

public class SqlitePassagemDAO implements PassagemDAO {

    @Override
    public Optional<Passagem> findByCpf(String cpf) {
        String sql = "SELECT * FROM PASSAGEM WHERE cpf = ?";
        Passagem passagem = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(cpf));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                passagem = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(passagem);
    }

    @Override
    public boolean create(Passagem passagem) {
        String sql = "INSERT INTO PASSAGEM (numPassagem , precoTotal, nome, cpf, rg, telefone, seguro, tipoEspecial, assento, viagem) values(?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(passagem.getNumPassagem()));
            preparedStatement.setString(2, String.valueOf(passagem.getPrecoTotal()));
            preparedStatement.setString(3, passagem.getNome());
            preparedStatement.setString(4, passagem.getCpf());
            preparedStatement.setString(5, passagem.getRg());
            preparedStatement.setString(6, passagem.getTelefone());
            preparedStatement.setBoolean(7, passagem.isSeguro());
            preparedStatement.setString(8, String.valueOf(passagem.getTipoEspecial()));
            preparedStatement.setString(9, passagem.getAssento());
            preparedStatement.setString(10, String.valueOf(passagem.getViagem()));
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Passagem> findOne(Long key) {
        String sql = "SELECT * FROM PASSAGEM WHERE numPassagem = ?";
        Passagem passagem = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(key));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                passagem = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(passagem);
    }

    @Override
    public List<Passagem> findAll() {
        String sql = "SELECT * FROM PASSAGEM";
        List<Passagem> passagens = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Passagem passagem = resultSetEntity(rs);
                passagens.add(passagem);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return passagens;
    }

    @Override
    public boolean update(Passagem passagem) {
        String sql = "UPDATE PASSAGEM(numPassagem , precoTotal, nome, cpf, rg, telefone, seguro, tipoEspecial, assento, viagem) VALUES(?,?,?,?,?,?,?,?,?,?) WHERE numPassagem = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(passagem.getNumPassagem()));
            preparedStatement.setString(2, String.valueOf(passagem.getPrecoTotal()));
            preparedStatement.setString(3, passagem.getNome());
            preparedStatement.setString(4, passagem.getCpf());
            preparedStatement.setString(5, passagem.getRg());
            preparedStatement.setString(6, passagem.getTelefone());
            preparedStatement.setBoolean(7, passagem.isSeguro());
            preparedStatement.setString(8, String.valueOf(passagem.getTipoEspecial()));
            preparedStatement.setString(9, passagem.getAssento());
            preparedStatement.setString(10, String.valueOf(passagem.getViagem()));
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Long numPassagem) {
        String sql = "DELETE FROM PASSAGEM WHERE numPassagem = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(numPassagem));
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Passagem passagem) {
        if(passagem == null)
            throw new IllegalArgumentException("A passagem não deve ser vazia");

        return deleteByKey(passagem.getNumPassagem());
    }

    private Passagem resultSetEntity(ResultSet resultSet) throws SQLException {
        return new Passagem(resultSet.getLong("numPassagem"),
                resultSet.getDouble("precoTotal"),
                resultSet.getString("nome"),
                resultSet.getString("cpf"),
                resultSet.getString("rg"),
                resultSet.getString("telefone"),
                resultSet.getBoolean("seguro"),
                gerarViagemUseCase.findOne(UUID.fromString(resultSet.getString("viagem"))),
                TipoEspecial.valueOf(resultSet.getString("tipoEspecial")),
                resultSet.getString("assento"));
    }
}

