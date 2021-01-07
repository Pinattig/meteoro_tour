package br.edu.ifsp.domain.usecases.funcionario;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.utils.DAO;

import java.util.List;

public class GerenciarFuncionarioUseCase {

    DAO funcionarioDao;

    public GerenciarFuncionarioUseCase(DAO funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    public List<Funcionario> getAll(){
        return this.funcionarioDao.findAll();
    }

    public boolean edit(Funcionario funcionario){
        return  this.funcionarioDao.update(funcionario);
    }

    public Object insert(Funcionario funcionario){
        return this.funcionarioDao.create(funcionario);
    }

    public boolean delete(Funcionario funcionario){
        return this.funcionarioDao.delete(funcionario);
    }

    public boolean delete(Object key){
        return this.funcionarioDao.deleteByKey(key);
    }
}
