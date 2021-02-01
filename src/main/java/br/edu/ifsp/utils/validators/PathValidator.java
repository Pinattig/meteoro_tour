package br.edu.ifsp.utils.validators;

import br.edu.ifsp.domain.entities.trecho.Trecho;

import java.time.LocalTime;
import java.util.UUID;

public class PathValidator implements IValidator<Trecho> {

    private String message;

    public PathValidator() {
        this.message = "";
    }

    @Override
    public String validateFields(Trecho object) {
        validateObject(object);
        if(!message.equals(""))
            return message;
        validateId(object.getId());
        validateName(object.getNome());
        validateCidadeOrigem(object.getCidadeOrigem());
        validateCidadeDestino(object.getCidadeDestino());
        validateKm(object.getQuilometragem());
        validateTempoDuracao(object.getTempoDuracao());
        validateValorPassagem(object.getValorPassagem());
        validateValorSeguro(object.getValorSeguro());
        valiadateTaxaEmbarque(object.getTaxaEmbarque());

        return message;
    }

    private void validateObject(Trecho trecho){
        if(trecho == null)
            message += "O trecho é nulo \n";
    }

    private void validateId(UUID id){
        if(id == null)
            message += "O id não pode ser vazio \n";
    }

    private void validateName(String name){
        if(name == null || name.equals(""))
            message += "O nome não pode ser vazio \n";
    }

    private void validateCidadeOrigem(String origem){
        if(origem == null || origem.equals(""))
            message += "A cidade de origem não pode ser vazia \n";
    }

    private void validateCidadeDestino(String destino){
        if(destino == null || destino.equals(""))
            message += "A cidade de destino não pode ser vazia \n";
    }

    private void validateKm(Double km){
        if(km == null)
            message += "A quilometragem não pode ser vazia \n";
        else if(km <= 0)
            message += "A quilometragem precisa ser maior que 0 \n";
    }

    private void validateTempoDuracao(LocalTime tempo){
        if(tempo == null)
            message += "O tempo não pode ser vazio";
    }

    private void validateValorPassagem(Double valorPassagem){
        if(valorPassagem == null)
            message += "O valor da passagem não pode ser vazio \n";
        else if(valorPassagem <= 0)
            message += "O valor da passagem precisa ser maior que 0 \n";
    }

    private void valiadateTaxaEmbarque(Double taxaEmbarque){
        if(taxaEmbarque == null)
            message += "A taxa de embarque não pode ser vazia \n";
        else if(taxaEmbarque <= 0)
            message += "A taxa de embarque precisa ser maior que 0 \n";
    }

    private void validateValorSeguro(Double valorSeguro){
        if(valorSeguro != null && valorSeguro < 0)
            message += "O valor do seguro precisa ser maior ou igual a 0 \n";
    }
}
