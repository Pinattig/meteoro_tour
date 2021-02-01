package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.edu.ifsp.application.main.Main.gerenciarLinhaUseCase;
import static br.edu.ifsp.application.main.Main.gerenciarTrechosUseCase;

public class SqliteTrechoLinhaDAO implements TrechoLinhaDAO {
    @Override
    public List<TrechoLinha> getByTrechoId(UUID trechoId) {
        String sql = "SELECT * FROM TRECHOSLINHAS WHERE trecho = ?";
        List<TrechoLinha> trechoLinhas = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(trechoId));
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                TrechoLinha trechoLinha = resultSetEntity(rs);
                trechoLinhas.add(trechoLinha);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return trechoLinhas;
    }



    @Override
    public List<TrechoLinha> getByLinhaId(Long linhaId) {
        String sql = "SELECT * FROM TRECHOSLINHAS WHERE linha = ?";
        List<TrechoLinha> trechoLinhas = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(linhaId));
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                TrechoLinha trechoLinha = resultSetEntity(rs);
                trechoLinhas.add(trechoLinha);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return trechoLinhas;
    }

    @Override
    public boolean create(TrechoLinha type) {
        String sql = "INSERT INTO TRECHOSLINHAS (id, horarioSaida, ordem, linha, assentosTrechoLinha, trecho) values(?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(type.getId()));
            preparedStatement.setString(1, String.valueOf(type.getHorarioSaida()));
            preparedStatement.setString(1, String.valueOf(type.getOrdem()));
            preparedStatement.setString(1, String.valueOf(type.getLinha().getId()));
            preparedStatement.setString(1, String.valueOf(type.getAssentosTrechoLinha().getId()));
            preparedStatement.setString(1, String.valueOf(type.getTrecho()));

            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<TrechoLinha> findOne(UUID key) {
        String sql = "SELECT * FROM TRECHOLINHAS WHERE id = ?";
        TrechoLinha trechoLinha = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(key));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                trechoLinha = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(trechoLinha);
    }

    @Override
    public List<TrechoLinha> findAll() {
        String sql = "SELECT * FROM TRECHOSLINHAS";
        List<TrechoLinha> trechoLinhas = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                TrechoLinha trechoLinha = resultSetEntity(rs);
                trechoLinhas.add(trechoLinha);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return trechoLinhas;
    }

    @Override
    public boolean update(TrechoLinha type) {
        String sql = "UPDATE INTO TRECHOSLINHAS (id, horarioSaida, ordem, linha, assentosTrechoLinha, trecho) values(?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(type.getId()));
            preparedStatement.setString(1, String.valueOf(type.getHorarioSaida()));
            preparedStatement.setString(1, String.valueOf(type.getOrdem()));
            preparedStatement.setString(1, String.valueOf(type.getLinha().getId()));
            preparedStatement.setString(1, String.valueOf(type.getAssentosTrechoLinha().getId()));
            preparedStatement.setString(1, String.valueOf(type.getTrecho()));

            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByKey(UUID key) {
        String sql = "DELETE FROM TRECHOLINHAS WHERE id = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(key));
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(TrechoLinha type) {
        if(type == null)
            throw new IllegalArgumentException("A passagem não deve ser vazia");

        return deleteByKey(type.getId());
    }

    private TrechoLinha resultSetEntity(ResultSet rs) throws SQLException {

        return new TrechoLinha(LocalTime.parse(rs.getString("horarioSaida")),
                Integer.parseInt(rs.getString("ordem")),
                gerenciarLinhaUseCase.getOne(rs.getLong("linha")).get(),
                null,
                gerenciarTrechosUseCase.findOneByKey(UUID.fromString(rs.getString("trecho"))));
    }
}
