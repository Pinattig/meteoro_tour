package br.edu.ifsp.application.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

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

    }
}
