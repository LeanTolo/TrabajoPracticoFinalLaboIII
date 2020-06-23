package com.company.ticket;

import com.company.IjsonManagement.IjsonManagement;

import com.company.airplane.Airplane;

import com.company.Management;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Ticket implements Comparable,IjsonManagement<Ticket> {

    private LocalDate date;
    private City origin;
    private City destination;
    private int passengers;
    private double price;
    private int distance;
    private int userDni;
    private boolean canceled;
    private String airplaneSerialNumber;



    public Ticket(){};

    public Ticket(LocalDate date, City origin, City destination, int passengers,int userDni, String airplaneSerialNumber) throws IOException {
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.passengers = passengers;
        this.airplaneSerialNumber = airplaneSerialNumber;
        this.userDni = userDni;
        setDistance();
        setPrice();
        canceled = false;
    }

    public boolean getCanceled(){
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getAirplaneSerialNumber() {
        return airplaneSerialNumber;
    }

    private void setDistance() {
        HashSet<City> originDestination = new HashSet<>();
        originDestination.add(origin);
        originDestination.add(destination);
        if (originDestination.contains(City.BUENOSAIRES)) {
            if (originDestination.contains(City.MONTEVIDEO)) {
                distance = 950;
            } else if (originDestination.contains(City.CORDOBA)) {
                distance = 695;
            } else {
                distance = 1400;
            }
        } else if (originDestination.contains(City.MONTEVIDEO)) {
            if (originDestination.contains(City.CORDOBA)) {
                distance = 1190;
            } else {
                distance = 2100;
            }
        } else {
            distance = 1050;
        }
    }

    private void setPrice() throws IOException {
        Management helper = new Management();
        List<Airplane> airplanes = helper.readfileAirplanes();
        Airplane plane = new Airplane();
        plane.setSerialNumber(airplaneSerialNumber);
        for(Airplane airplane : airplanes){
            if(airplane.equals(plane)){
                plane = airplane;
            }
        }
        this.price = distance*plane.getCostPerKm()+passengers*3500+plane.getFixedFee();
    }

    public double getPrice() {
        return price;
    }

    public int getDistance() {
        return distance;
    }

    public LocalDate getDate() {
        return date;
    }

    public City getDestination() {
        return destination;
    }

    public City getOrigin() {
        return origin;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getUserDni() {
        return userDni;
    }


    @Override
    public int compareTo(Object o) {
        int rta = this.date.compareTo(((Ticket) o).date);
        return rta;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "date=" + date +
                ", origin=" + origin +
                ", destination=" + destination +
                ", passengers=" + passengers +
                ", price=" + price +
                ", distance=" + distance +
                ", airplane=" + airplaneSerialNumber +
                '}';
    }


    @Override
    public List<Ticket> readFile () throws IOException {

        List<Ticket> ticketsFromJson = null;
        File file = new File("Ticket.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if(file.exists()) {
            try {
                Ticket[] ticketsArray = mapper.readValue(file,Ticket[].class); // convert JSON array to Array objects
                ticketsFromJson = Arrays.asList(mapper.readValue(file, Ticket[].class)); // convert JSON array to List of objects
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("File is Empty");
        }
        return ticketsFromJson;
    }

    @Override
    public void addToFile() {
        File file = new File("Ticket.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            if (file.createNewFile()) {
                ArrayList<Ticket> ticketsArrayList = new ArrayList<>();
                ticketsArrayList.add(this);
                mapper.writeValue(file, ticketsArrayList);
            } else {
                ArrayList<Ticket> ticketsArrayList = new ArrayList<Ticket>(readFile());
                ticketsArrayList.add(this);
                mapper.writeValue(file, ticketsArrayList);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFile () throws IOException {
        File file = new File("Ticket.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if(file.exists()) {
            System.out.println("--- Contenido del Archivo ---");
            try {

                Ticket[] ticketsArray = mapper.readValue(file,Ticket[].class); // convert JSON array to Array objects
                List<Ticket> tickets = Arrays.asList(mapper.readValue(file, Ticket[].class)); // convert JSON array to List of objects
                tickets.stream().forEach(x -> System.out.println(x)); // show lists of objects

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Archivo vacio");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return userDni == ticket.userDni &&
                date.equals(ticket.date) &&
                airplaneSerialNumber.equals(ticket.airplaneSerialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, userDni, airplaneSerialNumber);
    }
}


