package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.trecho.TrechoDAO;
import br.edu.ifsp.domain.usecases.viagem.ViagemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.edu.ifsp.application.main.Main.gerenciarLinhaUseCase;

public class SqliteViagemDAO implements ViagemDAO {
    @Override
    public List<Viagem> getViagensByDate(LocalDate dataInicio, LocalDate dataFim) {
        return null;
    }

    @Override
    public boolean create(Viagem viagem) {
        String sql = "INSERT INTO VIAGEM (id, data, horarioSaida, cidadeOrigem, cidadeDestino, trechosLinhas, linha) values(?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(viagem.getId()));
            preparedStatement.setString(2, String.valueOf(viagem.getData()));
            preparedStatement.setString(3, String.valueOf(viagem.getHorarioSaida()));
            preparedStatement.setString(4, viagem.getCidadeOrigem());
            preparedStatement.setString(5, viagem.getCidadeDestino());
            preparedStatement.setString(6, String.valueOf(viagem.getTrechoLinhas().get(0).getId()));
            preparedStatement.setString(7, String.valueOf(viagem.getLinha().getId()));
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Viagem> findOne(UUID key) {
        String sql = "SELECT * FROM VIAGEM WHERE id = ?";
        Viagem viagem = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(key));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                viagem = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(viagem);
    }

    @Override
    public List<Viagem> findAll() {
        String sql = "SELECT * FROM VIAGEM";
        List<Viagem> viagens = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Viagem viagem = resultSetEntity(rs);
                viagens.add(viagem);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return viagens;
    }

    @Override
    public boolean update(Viagem viagem) {
        String sql = "UPDATE VIAGEM(data, horarioSaida, cidadeOrigem, cidadeDestino, trechosLinhas, linha) VALUES(?,?,?,?,?,?) WHERE id = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1, String.valueOf(viagem.getData()));
            preparedStatement.setString(2, String.valueOf(viagem.getHorarioSaida()));
            preparedStatement.setString(3, viagem.getCidadeOrigem());
            preparedStatement.setString(4, viagem.getCidadeDestino());
            preparedStatement.setString(5, String.valueOf(viagem.getTrechoLinhas().get(0).getId()));
            preparedStatement.setString(6, String.valueOf(viagem.getLinha().getId()));
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(UUID id) {
        String sql = "DELETE FROM VIAGEM WHERE id = ?";
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
    public boolean delete(Viagem viagem) {
        if(viagem == null)
            throw new IllegalArgumentException("O onibus não deve ser vazio");

        return deleteByKey(viagem.getId());
    }

    private Viagem resultSetEntity(ResultSet resultSet) throws SQLException {
        return new Viagem(resultSet.getString("cidadeOrigem"),
                        resultSet.getString("cidadeDestino"),
                        gerenciarLinhaUseCase.getOne(Long.valueOf(resultSet.getString("linha"))),
                        LocalDate.parse(resultSet.getString("data")),
                        LocalTime.parse(resultSet.getString("horarioSaida")),
                        SqliteTrechoLinhaDAO.findAll());
    }
}
