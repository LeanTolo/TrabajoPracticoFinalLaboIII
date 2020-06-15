package com.company;

import com.company.airplane.Gold;
import com.company.airplane.MotorType;
import com.company.tickets.City;
import com.company.tickets.Ticket;
import com.company.user.User;

import java.io.IOException;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws IOException {



        User testUSUARIO = new User("Lean", "Tolo", 111111, 22, "123456");
        User testUSUARIO2 = new User("Lau", "Nieves", 222222, 22, "789456");
        User testUSUARIO3 = new User("Pedro", "Vago", 333333, 22, "123789");

       // testUSUARIO.addUserToFile(testUSUARIO);
        testUSUARIO2.addUserToFile(testUSUARIO2);
        //testUSUARIO3.addUserToFile(testUSUARIO3);

        testUSUARIO3.readFile();


        Gold avion = new Gold(15,15,5,450, MotorType.HELICE);
        Ticket ticket = new Ticket(LocalDate.now(), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        System.out.println(ticket.getDistance());
        System.out.println(ticket.getPrice());
    }
}
