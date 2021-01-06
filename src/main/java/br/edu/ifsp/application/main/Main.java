package br.edu.ifsp.application.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        System.out.println(time);

        Date date = new Date();
        System.out.println(date);

        LocalDate data = LocalDate.now();
        System.out.println(data);
    }
}
