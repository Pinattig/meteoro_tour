package br.edu.ifsp.domain.entities.vendedor;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.utils.PropertiesReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Vendedor {

    private Passagem passagem;
    private List<Viagem> viagens;

    public Vendedor() {
    }

    public void emitirPassagem(Passagem passagem) throws IOException {

        PropertiesReader propertiesReader = new PropertiesReader("config.properties");
        try{
            String path = propertiesReader.getPropertieValue("SavePassagemFilePath");
            String fileName = passagem.getNome()+"_"+passagem.getNumPassagem()+".txt";
            PrintWriter writer = new PrintWriter(path+fileName, "UTF-8");

            writer.println("-------- PASSAGEM RODOVIÁRIA --------");
            writer.println("Nome do passageiro: " + passagem.getNome());
            writer.println("CPF do passageiro: " + passagem.getCpf());

            writer.close();
        }catch (IOException e){
            throw e;
        }

    }

}
