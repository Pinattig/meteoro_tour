package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.usecases.login.LoginDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SqliteLoginDAO implements LoginDAO {
    @Override
    public boolean authenticateLogin(String senha, String login) {
        String sql = "SELECT * FROM USERS WHERE login = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(login));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                return senha.equals(rs.getString("senha"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean createLogin(String senha, String login, String nome) {
        String sql = "INSERT INTO USERS (login, senha, nome) values(?,?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3, nome);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
