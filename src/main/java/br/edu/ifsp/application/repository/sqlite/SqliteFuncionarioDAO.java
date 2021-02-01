package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.usecases.funcionario.FuncionarioDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteFuncionarioDAO implements FuncionarioDAO {
    @Override
    public boolean create(Funcionario funcionario) {
        String sql = "INSERT INTO FUNCIONARIO (cpf, nome, rg, cargo) values(?,?,?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,funcionario.getCpf());
            preparedStatement.setString(2,funcionario.getNome());
            preparedStatement.setString(3,funcionario.getRg());
            preparedStatement.setString(4,funcionario.getCargo());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Funcionario> findOne(String cpf) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE cpf = ?";
        Funcionario funcionario = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1,cpf);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                funcionario = resultSetEntity(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(funcionario);
    }

    private Funcionario resultSetEntity(ResultSet rs) throws SQLException {
        return new Funcionario(rs.getString("cpf"),rs.getString("nome"),
                                rs.getString("rg"),rs.getString("cargo"));
    }

    @Override
    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM FUNCIONARIO";
        List<Funcionario> funcionarios = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Funcionario funcionario1 = resultSetEntity(rs);
                funcionarios.add(funcionario1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return funcionarios;
    }

    @Override
    public boolean update(Funcionario funcionario) {
        String sql = "UPDATE FUNCIONARIO(nome, rg, cargo) VALUES (?,?,?) WHERE cpf = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,funcionario.getNome());
            preparedStatement.setString(2,funcionario.getRg());
            preparedStatement.setString(3,funcionario.getCargo());
            preparedStatement.setString(4,funcionario.getCpf());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String cpf) {
        String sql = "DELETE FROM FUNCIONARIO WHERE cpf = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,cpf);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Funcionario funcionario) {
        if(funcionario == null || funcionario.getCpf().equals(""))
            throw new IllegalArgumentException("O funcionario não deve ser vazio");

        return deleteByKey(funcionario.getCpf());
    }
}
