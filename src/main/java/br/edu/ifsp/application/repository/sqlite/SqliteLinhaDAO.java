package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteLinhaDAO implements LinhaDAO {

    @Override
    public boolean create(Linha linha) {
        String sql = "INSERT INTO LINHA (nome) values(?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, linha.getNome());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Linha> findOne(Long key) {
        String sql = "SELECT * FROM LINHA WHERE ID = ?";
        Linha linha = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(key));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                linha = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(linha);
    }

    @Override
    public List<Linha> findAll() {
        String sql = "SELECT * FROM LINHA";
        List<Linha> linhas = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Linha linha = resultSetEntity(rs);
                linhas.add(linha);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return linhas;
    }

    @Override
    public boolean update(Linha linha) {
        String sql = "UPDATE LINHA SET nome = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,linha.getNome());
            preparedStatement.setString(2, String.valueOf(linha.getId()));

            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Long id) {
        String sql = "DELETE FROM LINHA WHERE id = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Linha linha) {
        if(linha == null)
            throw new IllegalArgumentException("O linha não deve ser vazia");

        return deleteByKey(linha.getId());
    }

    private Linha resultSetEntity(ResultSet resultSet) throws SQLException {
        return new Linha(resultSet.getString("nome"));

    }
}
