package br.edu.ifsp.utils.validators;

import br.edu.ifsp.domain.entities.linha.Linha;

public interface IValidator <T>{

    public String validateFields(T object);

}
