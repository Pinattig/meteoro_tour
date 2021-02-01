package br.edu.ifsp.utils.validators;

import br.edu.ifsp.domain.entities.onibus.Onibus;

public class BusValidator implements IValidator <Onibus>{

    private String message;

    public BusValidator() {
        this.message = "";
    }

    @Override
    public String validateFields(Onibus object) {
        validatePlaca(object.getPlaca());
        validateRenavam(object.getRenavam());

        return message;
    }

    private void validatePlaca(String placa){
        if(placa.equals(""))
            message += "A placa não pode estar vazia \n";
    }

    private void validateRenavam(String renavam){
        if(renavam.equals(""))
            message += "O Renavam não pode estar vazio \n";
    }
}
