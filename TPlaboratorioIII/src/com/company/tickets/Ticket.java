package com.company.tickets;

import com.company.airplane.Airplane;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ticket implements Comparable{
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
}


