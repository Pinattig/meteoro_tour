package br.edu.ifsp.utils.validators;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;

public class EmployeeValidator implements IValidator<Funcionario>{


    private String mensage;

    @Override
    public String validateFields(Funcionario object) {
        validateObject(object);
        if(!mensage.equals(""))
            return mensage;
        validateName(object.getNome());
        validateCPF(object.getCpf());
        validateRG(object.getRg());
        validatePosition(object.getCargo());

        return mensage;
    }

    private void validateObject(Funcionario funcionario){
        if(funcionario == null)
            mensage += "O funcionário é nulo \n";
    }

    private void validateName(String name){
        if(name.equals(""))
            mensage += "O nome não pode ser vazio \n";
    }

    private void validateCPF(String cpf){
        if(cpf.equals(""))
            mensage += "O CPF não pode ser vazio \n";
    }

    private void validateRG(String rg){
        if(rg.equals(""))
            mensage += "O RG não pode ser vazio \n";
    }

    private void validatePosition(String position){
        if(position.equals(""))
            mensage += "O Cargo não pode ser vazio \n";
    }
}
