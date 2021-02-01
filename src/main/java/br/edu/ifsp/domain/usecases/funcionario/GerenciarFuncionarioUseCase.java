package br.edu.ifsp.domain.usecases.funcionario;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.utils.exceptions.InvalidFieldsException;
import br.edu.ifsp.utils.validators.EmployeeValidator;
import br.edu.ifsp.utils.validators.IValidator;

import java.util.List;

public class GerenciarFuncionarioUseCase {

    private FuncionarioDAO funcionarioDao;
    private IValidator validator;

    public GerenciarFuncionarioUseCase(FuncionarioDAO funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
        this.validator = new EmployeeValidator();
    }

    public List<Funcionario> getAll(){
        return this.funcionarioDao.findAll();
    }

    public boolean edit(Funcionario funcionario){
        String msg = validator.validateFields(funcionario);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        return funcionarioDao.update(funcionario);
    }

    public boolean insert(Funcionario funcionario){

        String msg = validator.validateFields(funcionario);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);
        if(funcionarioDao.findOne(funcionario.getCpf()).isPresent())
            throw new IllegalArgumentException("O funcionário ja existe!");
        return funcionarioDao.create(funcionario);
    }

    public boolean delete(Funcionario funcionario){
        String msg = validator.validateFields(funcionario);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        return this.funcionarioDao.delete(funcionario);
    }

    public boolean deleteByKey(String key){
        return this.funcionarioDao.deleteByKey(key);
    }


}
