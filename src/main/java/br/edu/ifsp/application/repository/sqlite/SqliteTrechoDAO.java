package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.usecases.trecho.TrechoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SqliteTrechoDAO implements TrechoDAO {
    @Override
    public Trecho getByCities(String cidadeOrigem, String cidadeDestino) {
        return null;
    }

    @Override
    public Trecho findOneByKey(UUID key) {

        String sql = "SELECT * FROM TRECHO WHERE id = ?";
        Trecho trecho = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(key));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                trecho = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return trecho;
    }

    @Override
    public Trecho findOneByName(String name) {
        String sql = "SELECT * FROM TRECHO WHERE nome = ?";
        Trecho trecho = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(name));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                trecho = resultSetEntity(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(trecho);
        return trecho;
    }

    @Override
    public boolean create(Trecho trecho) {
        String sql = "INSERT INTO TRECHO (cidadeOrigem, cidadeDestino, quilometragem, tempoDuracao, valorPassagem,taxaEmbarque,valorSeguro,nome,id) values(?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,trecho.getCidadeOrigem());
            preparedStatement.setString(2,trecho.getCidadeDestino());
            preparedStatement.setDouble(3,trecho.getQuilometragem());
            preparedStatement.setString(4,String.valueOf(trecho.getTempoDuracao()));
            preparedStatement.setDouble(5,trecho.getValorPassagem());
            preparedStatement.setDouble(6,trecho.getTaxaEmbarque());
            preparedStatement.setDouble(7,trecho.getValorSeguro());
            preparedStatement.setString(8,trecho.getNome());
            preparedStatement.setString(9,trecho.getId().toString());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Trecho> findOne(UUID key) {
        String sql = "SELECT * FROM TRECHO WHERE id = ?";
        Trecho trecho = null;
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(key));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                trecho = resultSetEntity(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(trecho);
    }

    @Override
    public List<Trecho> findAll() {
        String sql = "SELECT * FROM TRECHO";
        List<Trecho> trechos = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Trecho trecho = resultSetEntity(rs);
                trechos.add(trecho);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return trechos;
    }

    @Override
    public boolean update(Trecho trecho) {
        String sql = "UPDATE TRECHO SET cidadeOrigem = ?, cidadeDestino = ?, quilometragem = ?, tempoDuracao = ?, valorPassagem = ?,taxaEmbarque = ?,valorSeguro = ?,nome = ? where id = ?";
        try(PreparedStatement preparedStatement = ConnectionFactory.createPreparedStatement(sql)){
            preparedStatement.setString(1,trecho.getCidadeOrigem());
            preparedStatement.setString(2,trecho.getCidadeDestino());
            preparedStatement.setDouble(3,trecho.getQuilometragem());
            preparedStatement.setString(4,String.valueOf(trecho.getTempoDuracao()));
            preparedStatement.setDouble(5,trecho.getValorPassagem());
            preparedStatement.setDouble(6,trecho.getTaxaEmbarque());
            preparedStatement.setDouble(7,trecho.getValorSeguro());
            preparedStatement.setString(8,trecho.getNome());
            preparedStatement.setString(9, String.valueOf(trecho.getId()));

            return preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(UUID key) {
        String sql = "DELETE FROM TRECHO WHERE id = ?";
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
    public boolean delete(Trecho trecho) {
        if(trecho == null || trecho.getId().equals(""))
            throw new IllegalArgumentException("O onibus não deve ser vazio");

        return deleteByKey(trecho.getId());
    }


    private Trecho resultSetEntity(ResultSet resultSet) throws SQLException {
        return new Trecho(UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("cidadeOrigem"),
                        resultSet.getString("cidadeDestino"),
                        resultSet.getDouble("quilometragem"),
                        LocalTime.parse(resultSet.getString("tempoDuracao")),
                        resultSet.getDouble("valorPassagem"),
                        resultSet.getDouble("taxaEmbarque"),
                        resultSet.getDouble("valorSeguro"),
                        resultSet.getString("nome"));

    }
}
