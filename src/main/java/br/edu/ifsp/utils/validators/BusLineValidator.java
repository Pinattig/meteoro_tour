package br.edu.ifsp.utils.validators;

import br.edu.ifsp.domain.entities.linha.Linha;

public class BusLineValidator implements IValidator<Linha>{

    private String message;

    public BusLineValidator() {
        this.message = "";
    }

    @Override
    public String validateFields(Linha object) {
        validateObject(object);
        if(!message.equals(""))
            return message;
        validateName(object.getNome());
        return message;
    }

    private void validateName(String name){
        if(name.equals(""))
            message += "O nome não pode estar vazio \n";
    }

    private void validateObject(Linha object){
        if(object == null)
            message += "A linha é nula \n";
    }
}
