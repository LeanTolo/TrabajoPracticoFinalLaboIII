package com.company;

import com.company.airplane.*;
import com.company.tickets.City;
import com.company.tickets.Ticket;
//import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {


//        Menu menu = new Menu();
//        menu.startMenu();
        Gold avion = new Gold("sd",15,15,1,5,MotorType.HELICE);
        Silver avion2 = new Silver("saa",15,15,1,5,MotorType.HELICE);
        Bronze avion3 = new Bronze("sd213",15,15,1,5,MotorType.HELICE);
        avion.setDates();
        avion.addToFile();
//        Ticket ticket = new Ticket(LocalDate.of(2020,1,1),City.CORDOBA,City.SANTIAGO,4,null);
//        ticket.addToFile();
//        List<Ticket> ticketList = new ArrayList<>();
//        ticketList = ticket.readFile();
//        for (Ticket a : ticketList){
//            System.out.println(a.toString());
//        }
//
//        Request request = new Request();
//        request.generateTicket();

    }
}
