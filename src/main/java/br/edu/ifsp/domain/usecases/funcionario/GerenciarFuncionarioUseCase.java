package br.edu.ifsp.domain.usecases.funcionario;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.onibus.Onibus;

import java.util.List;

public class GerenciarFuncionarioUseCase {

    private FuncionarioDAO funcionarioDao;

    public GerenciarFuncionarioUseCase(FuncionarioDAO funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    public List<Funcionario> getAll(){
        return this.funcionarioDao.findAll();
    }

    public boolean edit(Funcionario funcionario){
        if(funcionario == null)
            throw new IllegalArgumentException("O funcionário é nulo!");
        if(funcionarioDao.findOne(funcionario.getCpf()).isEmpty())
            throw new IllegalArgumentException("O funcionário não existe!");
        if(verificarCampos(funcionario))
            return funcionarioDao.create(funcionario);
        return false;
    }

    public boolean insert(Funcionario funcionario){
        if(funcionario == null)
            throw new IllegalArgumentException("O funcionário é nulo!");
        if(funcionarioDao.findOne(funcionario.getCpf()).isPresent())
            throw new IllegalArgumentException("O funcionário ja existe!");
        if(verificarCampos(funcionario))
            return funcionarioDao.create(funcionario);
        return false;
    }

    public boolean delete(Funcionario funcionario){
        return this.funcionarioDao.delete(funcionario);
    }

    public boolean deleteByKey(String key){
        return this.funcionarioDao.deleteByKey(key);
    }

    private boolean verificarCampos(Funcionario funcionario) {
        return !(funcionario.getNome().equals("")
                || funcionario.getRg().equals("")
                || funcionario.getCpf().equals("")
                || funcionario.getCargo().equals(""));
    }
}
