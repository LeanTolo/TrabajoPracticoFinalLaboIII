package com.company.airplane;

import com.company.tickets.City;
import com.company.tickets.Ticket;

import java.time.LocalDate;

public abstract class Airplane{


    private double fuelCapacity;
    private double costPerKm;
    private int maxPassengers;
    private double maxVelocity;
    private MotorType motorType;
    private int fixedFee;
    private City location;

    protected Airplane(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.maxPassengers = maxPassengers;
        this.maxVelocity = maxVelocity;
        this.motorType = motorType;
    }

    protected void setFixedFee(int fixedFee) {
        this.fixedFee = fixedFee;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public int getFixedFee() {
        return fixedFee;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "fuelCapacity=" + fuelCapacity +
                ", costPerKm=" + costPerKm +
                ", maxPassengers=" + maxPassengers +
                ", maxVelocity=" + maxVelocity +
                ", motorType=" + motorType +
                ", fixedFee=" + fixedFee +
                ", location=" + location +
                '}';
    }
}
