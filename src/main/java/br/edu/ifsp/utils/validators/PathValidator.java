package br.edu.ifsp.utils.validators;

import br.edu.ifsp.domain.entities.trecho.Trecho;

import java.time.LocalTime;
import java.util.UUID;

public class PathValidator implements IValidator<Trecho> {

    private String mensage;

    @Override
    public String validateFields(Trecho object) {
        validateObject(object);
        if(!mensage.equals(""))
            return mensage;
        validateId(object.getId());
        validateName(object.getNome());
        validateCidadeOrigem(object.getCidadeOrigem());
        validateCidadeDestino(object.getCidadeDestino());
        validateKm(object.getQuilometragem());
        validateTempoDuracao(object.getTempoDuracao());
        validateValorPassagem(object.getValorPassagem());
        validateValorSeguro(object.getValorSeguro());
        valiadateTaxaEmbarque(object.getTaxaEmbarque());

        return mensage;
    }

    private void validateObject(Trecho trecho){
        if(trecho == null)
            mensage += "O trecho é nulo \n";
    }

    private void validateId(UUID id){
        if(id == null)
            mensage += "O id não pode ser vazio \n";
    }

    private void validateName(String name){
        if(name == null || name.equals(""))
            mensage += "O nome não pode ser vazio \n";
    }

    private void validateCidadeOrigem(String origem){
        if(origem == null || origem.equals(""))
            mensage += "A cidade de origem não pode ser vazia \n";
    }

    private void validateCidadeDestino(String destino){
        if(destino == null || destino.equals(""))
            mensage += "A cidade de destino não pode ser vazia \n";
    }

    private void validateKm(Double km){
        if(km == null)
            mensage += "A quilometragem não pode ser vazia \n";
        else if(km <= 0)
            mensage += "A quilometragem precisa ser maior que 0 \n";
    }

    private void validateTempoDuracao(LocalTime tempo){
        if(tempo == null)
            mensage += "O tempo não pode ser vazio";
    }

    private void validateValorPassagem(Double valorPassagem){
        if(valorPassagem == null)
            mensage += "O valor da passagem não pode ser vazio \n";
        else if(valorPassagem <= 0)
            mensage += "O valor da passagem precisa ser maior que 0 \n";
    }

    private void valiadateTaxaEmbarque(Double taxaEmbarque){
        if(taxaEmbarque == null)
            mensage += "A taxa de embarque não pode ser vazia \n";
        else if(taxaEmbarque <= 0)
            mensage += "A taxa de embarque precisa ser maior que 0 \n";
    }

    private void validateValorSeguro(Double valorSeguro){
        if(valorSeguro != null && valorSeguro < 0)
            mensage += "O valor do seguro precisa ser maior ou igual a 0 \n";
    }
}
