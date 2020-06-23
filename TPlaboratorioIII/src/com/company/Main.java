package com.company;

//import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.IOException;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws IOException {

//        Menu menu = new Menu();
//        menu.startMenu();
        LocalDate date = LocalDate.now();
        LocalDate date1 = LocalDate.of(2020,06,24);
        int i = date1.compareTo(date);
        if(i == 1) {
            if (date.getYear() == date1.getYear()) {
                if (date.getMonth() == date1.getMonth()) {
                    if (date.getDayOfMonth()+1 == date1.getDayOfMonth()) {
                        System.out.println("No se puede");
                    }
                }
            }
        }
        System.out.println(i);

    }
}
