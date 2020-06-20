package com.company;

import com.company.airplane.*;
//import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        //Menu menu = new Menu();
        //menu.startMenu();


//        User testUSUARIO = new User("Lean", "Tolo", 111111, 22, "123456");
//        User testUSUARIO2 = new User("Lau", "Nieves", 222222, 22, "789456");
       // User testUSUARIO3 = new User("Carlete", "Vago", 333333, 22, "123789");
//
     //   testUSUARIO3.addToFile();




       // testUSUARIO3.showFile();




        Gold avion = new Gold(150,300,5,5000, MotorType.HELICE);
        //Silver avion1 = new Silver(0,0,5,0, MotorType.HELICE);
        Bronze avion2 = new Bronze(0,0,5,0, MotorType.HELICE);
        Request request = new Request();

        //request.addAirplaneToList(avion);
        //request.generateTicket();
       // System.out.println(avion.toString());
        avion.addToFile();
        //avion.showFile();
        //request.generateTicket();
        //request.generateTicket();
        //request.generateTicket();

        //request.showFlightsByDate(LocalDate.of(2020,1,1));

        request.addAirplaneToList(avion);
        //request.addAirplaneToList(avion1);
        request.addAirplaneToList(avion2);

        Menu menu = new Menu();
        menu.startMenu();

    }
}
