package br.edu.ifsp.application.main;

import br.edu.ifsp.domain.entities.trecho.AssentosTrechoLinha;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*
        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println("===================");
        Date date = new Date();
        System.out.println(date);
        System.out.println("===================");
        LocalDate data = LocalDate.now();
        System.out.println(data);
        System.out.println("===================");

        Date data2 = new Date();
        System.out.println("Data Agora: "+data2);
        System.out.println("===================");
        System.out.println(UUID.randomUUID());
        
        AssentosTrechoLinha assentos = new AssentosTrechoLinha(LocalDate.now());
        Map<String, Boolean> map = assentos.getAssentosVendidos();
        map.put("1", true);
        Map<String, Boolean> map2 = map.entrySet().stream().filter(x -> x.getValue() == false).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        System.out.println(map2.toString());
         */
        
        UUID teste = UUID.randomUUID();
        //System.out.println("teste = " + teste);

        String lUUID = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        System.out.println("lUUID = " + lUUID);
    }
}
