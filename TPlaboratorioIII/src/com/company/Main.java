package com.company;

import com.company.airplane.Gold;
import com.company.airplane.MotorType;
import com.company.tickets.City;
import com.company.tickets.Ticket;
import com.company.user.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.TreeSet;


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
        Ticket ticket2 = new Ticket(LocalDate.of(2020,12,6), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Ticket ticket3 = new Ticket(LocalDate.of(2020,12,3), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Ticket ticket4 = new Ticket(LocalDate.of(2020,6,7), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Ticket ticket5 = new Ticket(LocalDate.of(2019,1,1), City.MONTEVIDEO,City.SANTIAGO,2,avion);
        Request request = new Request();
        TreeSet<Ticket> treeSet = new TreeSet<>();
        treeSet.add(ticket);
        treeSet.add(ticket2);
        treeSet.add(ticket3);
        treeSet.add(ticket4);
        treeSet.add(ticket5);
        treeSet.forEach(ticket1 -> System.out.println(ticket1.toString()));



    }
}
