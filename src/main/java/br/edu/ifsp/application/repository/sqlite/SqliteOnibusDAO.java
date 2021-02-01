package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.usecases.onibus.OnibusDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteOnibusDAO implements OnibusDAO {



    @Override
    public boolean create(Onibus onibus) {
        String sql = "INSERT INTO ONIBUS (renavam, placa) values(?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,onibus.getRenavam());
            preparedStatement.setString(2,onibus.getPlaca());

            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Onibus> findOne(String key) {
        String sql = "SELECT * FROM ONIBUS WHERE RENAVAM = ?";
        Onibus onibus = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1,key);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                onibus = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(onibus);
    }

    @Override
    public List<Onibus> findAll() {
        String sql = "SELECT * FROM ONIBUS";
        List<Onibus> onibus = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Onibus onibus1 = resultSetEntity(rs);
                onibus.add(onibus1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return onibus;
    }

    @Override
    public boolean update(Onibus onibus) {
        String sql = "UPDATE ONIBUS SET placa = ? WHERE renavam = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,onibus.getPlaca());
            preparedStatement.setString(2,onibus.getRenavam());

            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String renavam) {
        String sql = "DELETE FROM ONIBUS WHERE renavam = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,renavam);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

        @Override
    public boolean delete(Onibus onibus) {
        if(onibus == null || onibus.getRenavam().equals(""))
            throw new IllegalArgumentException("O onibus não deve ser vazio");

        return deleteByKey(onibus.getRenavam());
    }

    private Onibus resultSetEntity(ResultSet resultSet) throws SQLException {
        return new Onibus(resultSet.getString("placa"),resultSet.getString("renavam"));

    }
}
