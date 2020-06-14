package com.company.tickets;

import com.company.airplane.Airplane;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ticket {
    private LocalDate date;
    private City origin;
    private City destination;
    private int passengers;
    private double price;
    private int distance;
    private Airplane airplane;



    public Ticket(LocalDate date, City origin, City destination, int passengers, Airplane airplane) {
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.passengers = passengers;
        //this.price = price;
        this.airplane = airplane;
    }

    private void setDistance(){
        HashSet<City> originDestination = null;
        originDestination.add(origin);
        originDestination.add(destination);
        if(originDestination.contains(City.BUENOSAIRES)){
            if(originDestination.contains(City.MONTEVIDEO)) {
                distance = 950;
            }else if(originDestination.contains(City.CORDOBA)){
                distance = 695;
            }else{
                distance = 1400;
            }
        }else if(originDestination.contains(City.MONTEVIDEO)){
            if(originDestination.contains(City.CORDOBA)){
                distance = 1190;
            }else{
                distance = 2100;
            }
        }else{
            distance = 1050;
        }


    }


}
