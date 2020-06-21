package com.company.user;

import com.company.IjsonManagement.IjsonManagement;
import com.company.Request;
import com.company.airplane.Airplane;
import com.company.airplane.Bronze;
import com.company.airplane.Gold;
import com.company.airplane.Silver;
import com.company.tickets.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class Functions implements IjsonManagement<Functions> {

//    public List readfileAirplanes () throws IOException {
//        List<Airplane> airplanes = new ArrayList<>();
//        Gold gold = new Gold();
//        Silver silver = new Silver();
//        Bronze bronze = new Bronze();
//        List<Gold> golds = gold.readFile();
//        for (Gold a: golds){
//            airplanes.add(a);
//        }
//        List<Silver> silvers = silver.readFile();
//        for (Silver a: silvers){
//            airplanes.add(a);
//        }
//        List<Bronze> bronzes = bronze.readFile();
//        for (Bronze a: bronzes){
//            airplanes.add(a);
//        }
//
//        return airplanes;
//    }

    public List readfileAirplanes () throws IOException {
        List<Airplane> airplanes = new ArrayList<>();
        airplanes = AddGoldtoArray(airplanes);
        airplanes = AddSilvertoArray(airplanes);
        airplanes = AddBronzetoArray(airplanes);

        return airplanes;
    }

    public List AddGoldtoArray (List<Airplane> airplanes) throws IOException {
        Gold gold = new Gold();
        List<Gold> golds = gold.readFile();
        for (Gold a: golds){
            airplanes.add(a);
        }
        return airplanes;
    }

    public List AddSilvertoArray (List<Airplane> airplanes) throws IOException {
        Silver silv = new Silver();
        List<Silver> silvers = silv.readFile();
        for (Silver a: silvers){
            airplanes.add(a);
        }
        return airplanes;
    }

    public List AddBronzetoArray (List<Airplane> airplanes) throws IOException {
        Bronze bron = new Bronze();
        List<Bronze> bronzes = bron.readFile();
        for (Bronze a: bronzes){
            airplanes.add(a);
        }
        return airplanes;
    }

    public List readFileTickets() throws IOException {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList = addTicketToList(ticketList);
        return ticketList;
    }

    public List addTicketToList(List<Ticket> ticketList) throws IOException{
        Ticket ticket = new Ticket();
        List<Ticket> tickets = ticket.readFile();
        for (Ticket ticket1 : tickets){
            ticketList.add(ticket1);
        }
        return ticketList;
    }

//    Reimplementado en Request
//    public void ShowTicketsByDate(LocalDate date){
//        List<Ticket> ticketList = readFileTickets();
//        System.out.println("--- Ticket  by Date "+date+"---");
//        for (Ticket ticket : ticketList) {
//            if (ticket.getDate().equals(date)) {
//                System.out.println(ticket.toString()+"\n------------");
//            }
//        }
//    }

    public boolean AddPlane (){
        System.out.println("Selecciona el tipo de avion a crear");
        return true;
    }

    @Override
    public List readFile() throws IOException {
        List<User> usersFromJson = null;
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();

        if(file.exists()) {
            try {
                User[] userArray = mapper.readValue(file,User[].class); // convert JSON array to Array objects
                usersFromJson = Arrays.asList(mapper.readValue(file, User[].class)); // convert JSON array to List of objects
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("File is Empty");
        }
        return usersFromJson;
    }

    @Override
    public void addToFile() {

    }

    @Override
    public void showFile () throws IOException {
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();

        if(file.exists()) {
            System.out.println("--- Contenido del Archivo ---");
            try {

                User[] userArray = mapper.readValue(file,User[].class); // convert JSON array to Array objects
                List<User> users = Arrays.asList(mapper.readValue(file, User[].class)); // convert JSON array to List of objects
                users.stream().forEach(x -> System.out.println(x)); // show lists of objects

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Archivo vacio");
        }
    }
}
