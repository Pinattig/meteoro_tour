package br.edu.ifsp.utils.validators;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.passagem.TipoEspecial;
import br.edu.ifsp.domain.entities.viagem.Viagem;

public class PassageValidator implements IValidator<Passagem> {

    private String mensage;

    @Override
    public String validateFields(Passagem object) {
        validateObject(object);
        if(!mensage.equals(""))
            return mensage;

        validateId(object.getNumPassagem());
        validateCPF(object.getCpf());
        validateName(object.getNome());
        validateAssento(object.getAssento());
        validatePhone(object.getTelefone());
        validatePrice(object.getPrecoTotal());
        validateViagem(object.getViagem());
        validateRG(object.getRg());
        validateEspecialType(object.getTipoEspecial());

        return mensage;
    }

    private void validateObject(Passagem passagem){
        if(passagem != null)
            mensage += "A passage é nula \n";
    }

    private void validateId(Long id){
        if(id == null)
            mensage += "O id não pode ser vazio \n";
    }

    private void validatePrice(Double price){
        if(price == null)
            mensage += "O valor total não pode ser vazio \n";
    }

    private void validateName(String name){
        if(name == null || name.equals(""))
            mensage += "O nome não pode ser vazio \n";
    }

    private void validateCPF(String cpf){
        if(cpf == null || cpf.equals(""))
            mensage += "O CPF não pode ser vazio \n";
    }

    private void validateRG(String rg){
        if(rg == null || rg.equals(""))
            mensage += "O RG não pode ser vazio \n";
    }

    private void validatePhone(String phone){
        if(phone == null || phone.equals(""))
            mensage += "O RG não pode ser vazio \n";
        else if(!tryParseLong(phone))
            mensage += "Digite apenas numeros no telefone \n";
    }

    private void validateEspecialType(TipoEspecial tipoEspecial){
        if(tipoEspecial == null)
            mensage += "Tipo especial inválido ou vazio \n";
    }

    private void validateAssento(String assento){
        if(assento == null || assento.equals(""))
            mensage += "O assento não pode estar vazio \n";
        else if(!tryParseLong(assento))
            mensage += "Assento inválido \n";

    }

    private void validateViagem(Viagem viagem){
        if(viagem == null)
            mensage += "A viagem é nula \n";

    }

    private boolean tryParseLong(String number){
        try {
            Long.parseLong(number);
            return true;

        }catch (NumberFormatException e){
            return false;
        }
    }
}
