package br.edu.ifsp.domain.entities.relatorio;

import br.edu.ifsp.utils.PropertiesReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Relatorio {
    private LocalDate dataInicio;
    private LocalDate dataFinal;

    private List<InfoLinhaRelatorio> infolinhaRelatorios;

    public Relatorio() {
    }

    public Relatorio(LocalDate dataInicio, LocalDate dataFinal, List<InfoLinhaRelatorio> infolinhaRelatorios) {
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.infolinhaRelatorios = infolinhaRelatorios;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void salvarEmArquivo() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader("config.properties");
        try{
            String path = propertiesReader.getPropertieValue("SaveRelatorioFilePath");
            UUID idRelatorio = UUID.randomUUID();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fileName = "Relatorio_"+idRelatorio+"_"+LocalDate.now().format(formatter)+".csv";
            PrintWriter writer = new PrintWriter(path+fileName, "UTF-8");
            writer.println("Nome da linha;Data da viagem; Horario do trecho; Nome do trecho; Fluxo de uso; Valor total");

            for (InfoLinhaRelatorio infolinhaRelatorio : infolinhaRelatorios) {

                for (InfoTrechoRelatorio infoTrechoRelatorio : infolinhaRelatorio.getInfoTrechoRelatorios()) {

                    writer.println(
                            infolinhaRelatorio.getNomeLinha() + ";" +
                            infolinhaRelatorio.getData() + ";" +
                            infoTrechoRelatorio.getHorario() + ";" +
                            infoTrechoRelatorio.getNomeTrecho() + ";" +
                            infoTrechoRelatorio.getFluxoUso() + ";" +
                            infoTrechoRelatorio.getLucro()
                    );
                }
            }

            writer.close();
        }catch (IOException e){
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "dataInicio=" + dataInicio +
                ", dataFinal=" + dataFinal +
                ", infolinhaRelatorios=" + infolinhaRelatorios +
                '}';
    }
}
