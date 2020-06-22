package com.company.user;

import com.company.IjsonManagement.IjsonManagement;
import com.company.Request;
import com.company.airplane.*;
import com.company.tickets.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    public void showUserTickets(int userDni) throws IOException {
        List<Ticket> ticketList = readFileTickets();
        for(Ticket ticket : ticketList){
            if(ticket.getUserDni() == userDni){
                System.out.println(ticket.toString());
            }
        }
    }

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
        if(golds != null){
            for (Gold a: golds){
                airplanes.add(a);
            }
        }
        return airplanes;
    }

    public List AddSilvertoArray (List<Airplane> airplanes) throws IOException {
        Silver silv = new Silver();
        List<Silver> silvers = silv.readFile();
        if(silvers != null){
            for (Silver a: silvers){
                airplanes.add(a);
            }
        }
        return airplanes;
    }

    public List AddBronzetoArray (List<Airplane> airplanes) throws IOException {
        Bronze bron = new Bronze();
        List<Bronze> bronzes = bron.readFile();
        if(bronzes != null){
            for (Bronze a: bronzes){
                airplanes.add(a);
            }
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
        if(tickets != null){
            for (Ticket ticket1 : tickets){
                ticketList.add(ticket1);
            }
        }
        return ticketList;
    }

    public void updateUser (User toupdate){
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            ArrayList<User> userArrayList = new ArrayList<User>(readFile());
            int i = 0;
            for (User a:userArrayList) {
                if(a.getDni()==toupdate.getDni()){
                    userArrayList.set(i,toupdate);
                }
                i++;
            }
            for (User a:userArrayList) {
                System.out.println(a.toString());
            }
            mapper.writeValue(file, userArrayList);
        }catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean AddGoldPlane (){
        boolean res = false;
        Scanner data = new Scanner(System.in);
        Gold golden = new Gold();

        golden.setDatesEmpty();

        System.out.println("\nIngrese SerialNumber:");
        golden.setSerialNumber(data.nextLine());
        System.out.println("\nIngrese capacidad de combustible: ");
        golden.setFuelCapacity(data.nextDouble());
        System.out.println("\nIngrese costo por kilometros: ");
        golden.setCostPerKm(data.nextDouble());
        System.out.println("\nIngrese cantidad maxima de pasajeros: ");
        golden.setMaxPassengers(data.nextInt());
        System.out.println("\nIngrese velocidad maxima:");
        golden.setMaxVelocity(data.nextDouble());
        System.out.println("\nIngrese Fixed Fee: ");
        golden.setFixedFee(data.nextInt());
        System.out.println("\nPosee WIFI? 1-SI  AnyNumber-NO:");
        if (data.nextInt()==1){
            golden.setWifiConnection(true);
        }else{
            golden.setWifiConnection(false);
        }
        System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3-PISTONES:");
        int motor = data.nextInt();

        while (optionCheck(motor) == false){
            System.out.println("\nOpcion ingresada no valida.");
            System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3PISTONES:");
            motor = data.nextInt();
        }
        golden.setMotorType(getMotor(motor));
        System.out.println("\nLos datos Ingresados son:\n"+golden.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            golden.addToFile();
            res = true;
        } else {
            AddGoldPlane();
        }
        return res;
    }

    public boolean AddSilverPlane (){
        boolean res = false;
        Scanner data = new Scanner(System.in);
        Silver silverNew  = new Silver();

        silverNew.setDatesEmpty();

        System.out.println("\nIngrese SerialNumber:");
        silverNew.setSerialNumber(data.nextLine());
        System.out.println("\nIngrese capacidad de combustible: ");
        silverNew.setFuelCapacity(data.nextDouble());
        System.out.println("\nIngrese costo por kilometros: ");
        silverNew.setCostPerKm(data.nextDouble());
        System.out.println("\nIngrese cantidad maxima de pasajeros: ");
        silverNew.setMaxPassengers(data.nextInt());
        System.out.println("\nIngrese velocidad maxima:");
        silverNew.setMaxVelocity(data.nextDouble());
        System.out.println("\nIngrese Fixed Fee: ");
        silverNew.setFixedFee(data.nextInt());
        System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3-PISTONES:");
        int motor = data.nextInt();
        while (optionCheck(motor) == false){
            System.out.println("\nOpcion ingresada no valida.");
            System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3PISTONES:");
            motor = data.nextInt();
        }
        silverNew.setMotorType(getMotor(motor));
        System.out.println("\nLos datos Ingresados son:\n"+silverNew.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            silverNew.addToFile();
            res = true;
        } else {
            AddSilverPlane();
        }
        return res;
    }

    public boolean AddBronzePlane (){
        boolean res = false;
        Scanner data = new Scanner(System.in);
        Bronze bronzeNew  = new Bronze();

        bronzeNew.setDatesEmpty();

        System.out.println("\nIngrese SerialNumber:");
        bronzeNew.setSerialNumber(data.nextLine());
        System.out.println("\nIngrese capacidad de combustible: ");
        bronzeNew.setFuelCapacity(data.nextDouble());
        System.out.println("\nIngrese costo por kilometros: ");
        bronzeNew.setCostPerKm(data.nextDouble());
        System.out.println("\nIngrese cantidad maxima de pasajeros: ");
        bronzeNew.setMaxPassengers(data.nextInt());
        System.out.println("\nIngrese velocidad maxima:");
        bronzeNew.setMaxVelocity(data.nextDouble());
        System.out.println("\nIngrese Fixed Fee: ");
        bronzeNew.setFixedFee(data.nextInt());
        System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3-PISTONES:");
        int motor = data.nextInt();
        while (optionCheck(motor) == false){
            System.out.println("\nOpcion ingresada no valida.");
            System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3PISTONES:");
            motor = data.nextInt();
        }
        bronzeNew.setMotorType(getMotor(motor));
        System.out.println("\nLos datos Ingresados son:\n"+bronzeNew.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            bronzeNew.addToFile();
            res = true;
        } else {
            AddBronzePlane();
        }
        return res;
    }



    public Boolean optionCheck (int choice){
        if (choice == 1 || choice == 2 || choice == 3){
            return true;
        }
        return false;
    }

    public MotorType getMotor(int choice){
        MotorType res = null;
        if (choice == 1){
            res = MotorType.REACCION;
        }else {
            if (choice == 2) {
                res = MotorType.HELICE;
            } else {
                if (choice == 3) {
                    res = MotorType.PISTONES;
                }
            }
        }
        return res;
    }

    @Override
    public List readFile() throws IOException {
        List<User> usersFromJson = null;
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
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
        mapper.registerModule(new JavaTimeModule());
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
