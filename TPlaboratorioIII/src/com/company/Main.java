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

//        List<User> Usuarios = new ArrayList<>();
//        Usuarios = testUSUARIO3.readFile();
////        System.out.println("--- Datos del Array ---");
////        for (User a:Usuarios) {
////            System.out.println(a);
////        }
//        AeroTaxiManagement log = new AeroTaxiManagement();
//        User testeo = log.validateUser(222222, "789456",Usuarios);
//        System.out.println(testeo.toString());



/*
        Gold avion = new Gold(15,15,5,450, MotorType.HELICE);
        Ticket ticket = new Ticket(LocalDate.now(), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Ticket ticket2 = new Ticket(LocalDate.of(2020,12,6), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Ticket ticket3 = new Ticket(LocalDate.of(2020,12,3), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Ticket ticket4 = new Ticket(LocalDate.of(2020,6,7), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Ticket ticket5 = new Ticket(LocalDate.of(2019,1,1), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Request request = new Request();
        System.out.println(avion.toString());

*/

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
    }
}
