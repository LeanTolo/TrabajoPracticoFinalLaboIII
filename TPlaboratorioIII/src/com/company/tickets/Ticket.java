package com.company.tickets;

import com.company.IjsonManagement.IjsonManagement;

import com.company.airplane.Airplane;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class Ticket implements Comparable,IjsonManagement<Ticket> {
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private City origin;
    private City destination;
    private int passengers;
    private double price;
    private int distance;
    private Airplane airplane;


    public Ticket(){};

    public Ticket(LocalDate date, City origin, City destination, int passengers, Airplane airplane) {
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.passengers = passengers;
        this.airplane = airplane;
        setDistance();
        setPrice();
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

    //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo
    //
    //de avión)

    private void setPrice() {
        this.price = distance*airplane.getCostPerKm()+passengers*3500+airplane.getFixedFee();
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

    public Airplane getAirplane() {
        return airplane;
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
                ", airplane=" + airplane +
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
                System.out.println("--- Imprimiento en archivo ---\n");
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


}


