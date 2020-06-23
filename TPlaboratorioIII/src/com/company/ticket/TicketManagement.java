package com.company.ticket;

import com.company.airplane.Airplane;
import com.company.airplane.type.Gold;
import com.company.airplane.type.Silver;
import com.company.airplane.type.Bronze;
import com.company.Management;
//import jdk.vm.ci.meta.Local;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class TicketManagement {
    private List<Ticket> ticketList;
    private List<Airplane> airplanesList;

    public TicketManagement() throws IOException {
        Management helper = new Management();
        ticketList = helper.readFileTickets();
        airplanesList = helper.readfileAirplanes();
    }

    public void addAirplaneToList(Airplane airplane){
        boolean flag = true;
        for (Airplane plane:airplanesList) {
            if(plane == airplane){
                flag = false;
            }
        }
        if(flag){
            airplanesList.add(airplane);
        }else{
            System.out.println("Airplane already added beforehand");
        }
    } //Agrega un avion a la lista

    private HashSet showAvailableAirplanes(int passengers,LocalDate date){
        int i = 0;
        HashSet<Integer> o = new HashSet<>();
        for(Airplane plane : airplanesList){
            if(!(plane.getDates().contains(date)) && plane.getMaxPassengers() >= passengers){
                System.out.println(i+":"+plane.toString());
                o.add(i);
            }
            i++;
        }
        return o;
    } //Muestra los aviones disponibles en una fecha indicada

    private Airplane chooseAirplane(int passengers,LocalDate date){
        HashSet<Integer> o = showAvailableAirplanes(passengers,date);
        int op;
        Airplane plane;
        if(!o.isEmpty()){
            do{
                op = enterNumber();
                if(!o.contains(op)){
                    System.out.println("Ingrese un valor valido");
                }
            }while (!o.contains(op));
            airplanesList.get(op).addDate(date);
            plane = airplanesList.get(op);
        }else{
            plane = null;
        }
        return plane;
    } //Se elige un avion de la lista, se le asigna la fecha y se retorna ese avion, si no se pudo retorna null

    private Ticket createTicket(int userDni) throws IOException {
        LocalDate date = chooseDate();
        City origin = chooseOriginTest();
        City destination = chooseDestination(origin);
        int passengers = choosePassengersQuantity();
        Airplane airplane = chooseAirplane(passengers,date);
        Ticket ticket;
        if(airplane == null){
            System.out.println("No tenemos aviones disponibles con esa capacidad de pasajeros");
            ticket = null;
        }else{
            ticket = new Ticket(date,origin,destination,passengers,userDni,airplane.getSerialNumber());
        }
        return ticket;
    } //Genera un ticket, si no se pudo retorna null;

    private void addTicketToList(Ticket ticket){
        System.out.println(ticket.toString());
        System.out.println("GENERATE TICKET?");
        System.out.println("1:YES \n2:NO");
        int op = 0;
        while((op<1) || (op>2)){
            op = enterNumber();
        }
        if(op == 1){
            this.ticketList.add(ticket);
        }else{
            System.out.println("TICKET DISCARDED");
        }
    }//Nos da la opcion de agregar o no un ticket a la lista luego de mostrarnoslo

    private void updatePlaneList(String serialNumber, LocalDate date){
        Management helper = new Management();
        for(Airplane plane : airplanesList){
            if(serialNumber.equals(plane.getSerialNumber())){
                plane.addDate(date);
                if (plane instanceof Gold) {
                    helper.updateGold((Gold) plane);
                }else if(plane instanceof Silver){
                    helper.updateSilver((Silver) plane);
                }else if(plane instanceof Bronze){
                    helper.updateBronze((Bronze) plane);
                }
            }
        }
    }

    public void removeDatePlane(String serialNumber, LocalDate date){
        Management helper = new Management();
        for(Airplane plane : airplanesList){
            if(serialNumber.equals(plane.getSerialNumber())){
                plane.removeDate(date);
                if (plane instanceof Gold) {
                    helper.updateGold((Gold) plane);
                }else if(plane instanceof Silver){
                    helper.updateSilver((Silver) plane);
                }else if(plane instanceof Bronze){
                    helper.updateBronze((Bronze) plane);
                }
            }
        }
    }

    public double generateTicket(int userDni) throws IOException {
        Management updater = new Management();
        Ticket ticket = createTicket(userDni);
        double amountSpent = 0;
        if(ticket!=null){
            addTicketToList(ticket);
            ticket.addToFile();
            updatePlaneList(ticket.getAirplaneSerialNumber(),ticket.getDate());
            updater.updateUserCategory(ticket.getAirplaneSerialNumber(),userDni);
            amountSpent += ticket.getPrice();
        }else{
            System.out.println("Desea probar otra fecha?");
            System.out.println("1:YES \n2:NO");
            int op = 0;
            while((op<1) || (op>2)){
                op = enterNumber();
            }
            if(op == 1){
                generateTicket(userDni);
            }
        }
        return amountSpent;
    }

    public void showTicketList(){
        ticketList.forEach(ticket ->System.out.println(ticket.toString()));
    }

    private int choosePassengersQuantity(){
        System.out.println("Ingrese cantidad de pasajeros:");
        int rta;
        do{
            rta = enterNumber();
            if(rta < 1){
                System.out.println("Ingrese una cantidad valida");
            }
        }while(rta < 1);
        return rta;
    } //3. El usuario debe indicar la cantidad de acompañantes que tendrá en el vuelo.

    private City chooseDestination(City origin){
        int op;
        int i = 1;
        City city;
        EnumSet<City> enumSet = EnumSet.allOf(City.class);
        enumSet.remove(origin);
        Iterator<City> iterator = enumSet.iterator();
        for (City value: enumSet) {
            System.out.println(i+":"+value);
            i++;
        }
        System.out.println("Seleccione la ciudad de destino:");
        do{
            op = enterNumber();
            city = iterateNumberOfTimes(iterator,op);
            if(city == null){
                System.out.println("Ciudad no existente");
                iterator = enumSet.iterator();
            }
        }while(city == null) ;
        System.out.println("Usted desea ir a "+city+"\n_________________");
        return city;
    }
    private City chooseOriginTest(){
        int op;
        int i = 1;
        City city;
        EnumSet<City> enumSet = EnumSet.allOf(City.class);
        for (City value: enumSet) {
            System.out.println(i+":"+value);
            i++;
        }
        Iterator<City> iterator = enumSet.iterator();
        System.out.println("Seleccione la ciudad de origen:");
        do{
            op = enterNumber();
            city = iterateNumberOfTimes(iterator,op);
            if(city == null){
                System.out.println("Ciudad no existente");
                iterator = enumSet.iterator();
            }
        }while(city == null);
        System.out.println("Usted esta saliendo de "+city.name()+"\n__________________");
        return city;
    } //Evolucion de un codigo horrible a algo hermoso y escalable

    private LocalDate chooseDate(){ //1. Inicialmente indicar la fecha deseada para realizar un vuelo.
        int year = pickYear();
        int month = pickMonth();
        int day = pickDay();
        LocalDate date = LocalDate.of(year,month,day);
        return date;
    }
    private int pickYear(){
        System.out.println("Ingrese el año que desea viajar");
        int op = enterNumber();
        while(op < 2020 || op > 2030){
            System.out.println("Fecha no valida\n Ingrese una valida:");
            op = enterNumber();
        }
        return op;
    }
    private int pickMonth(){
        System.out.println("Ingrese el mes que desea viajar");
        int op = enterNumber();
        while(op < 1 || op > 12){
            System.out.println("Fecha no valida\n Ingrese una valida:");
            op = enterNumber();
        }
        return op;
    }
    private int pickDay(){
        System.out.println("Ingrese el dia que desea viajar");
        int op = enterNumber();
        while(op < 1 || op > 31){
            System.out.println("Fecha no valida\n Ingrese una valida:");
            op = enterNumber();
        }
        return op;
    }

    private int enterNumber(){
        int option;
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();
        return option;
    }

    private City iterateNumberOfTimes(Iterator<City> iterator,int n){ //Esta funcion itera en el enumset la cantidad de veces pedida
        City city = null;                                               //Y retorna la ciudad indicada, o null.
        for(int i = 0; i<n;i++){
            if(!iterator.hasNext()){
                i=n;
                city = null;
            }else{
                city = iterator.next();
            }
        }
        return city;
    }

    public void showFlightsByDate(){
        LocalDate date = chooseDate();
        if(ticketList!= null) {
            System.out.println("--- Tickets by Date: "+date+"---\n");
            for (Ticket ticket : ticketList) {
                if (ticket.getDate().equals(date) && !ticket.getCanceled()) {
                    System.out.println(ticket.toString());
                }
            }
        }else {
            System.out.println("Actualmente no hay tickets para mostrar");
        }
    }

}
