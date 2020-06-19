package com.company;

import com.company.airplane.Airplane;
import com.company.airplane.Silver;
import com.company.tickets.City;
import com.company.tickets.Ticket;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Request {
    private List<Ticket> ticketList;
    private List<Airplane> airplanesList;

    public Request() {
        ticketList = new ArrayList<>();
        airplanesList = new ArrayList<>();
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

    private void showAvailableAirplanes(int passengers,LocalDate date){
        int i = 0;
        for(Airplane plane : airplanesList){
            if(!(plane.getDates().contains(date)) && plane.getMaxPassengers() >= passengers){
                System.out.println(i+":"+plane.toString());
            }
            i++;
        }
    } //Muestra los aviones disponibles en una fecha indicada

    private Airplane chooseAirplane(int passengers,LocalDate date){
        showAvailableAirplanes(passengers,date);
        int op;
        do{
            op = enterNumber();
            if(op<0 && op>airplanesList.size()){
                System.out.println("Ingrese un valor valido");
            }
        }while (op<0 && op>airplanesList.size()-1);
        airplanesList.get(op).addDate(date);
        return airplanesList.get(op);
    } //Se elige un avion de la lista, se le asigna la fecha y se retorna ese avion

    //4. Ahora el usuario debe seleccionar un avión. El sistema se encargará de
    //mostrar los aviones disponibles para esa fecha y el usuario elige el deseado.
    //5. Por último, el sistema debe mostrar el costo total del vuelo y el usuario
    //deberá confirmar para generar el vuelo.

    public void generateTicket(){
        LocalDate date = chooseDate();
        City origin = chooseOriginTest();
        City destination = chooseDestination(origin);
        int passengers = choosePassengersQuantity();
        Airplane airplane = chooseAirplane(passengers,date);
        Ticket ticket = new Ticket(date,origin,destination,passengers,airplane);
        System.out.println(ticket.toString());
        System.out.println("GENERATE TICKET?");
        System.out.println("1:YES \n2:NO");
        int op;
        do{
            op = enterNumber();
        }while(op<1 && op>2);
        if(op == 1){
            this.ticketList.add(ticket);
        }else{
            System.out.println("TICKET DISCARDED");
        }
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
        System.out.println(city);
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
        System.out.println(city.name());
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
        option = Integer.parseInt(scanner.nextLine());
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

    public void showFlightsByDate(LocalDate date){
        for(Ticket ticket : ticketList){
            if(ticket.getDate().equals(date)){
                System.out.println(ticket.toString());
            }
        }
    }

}
