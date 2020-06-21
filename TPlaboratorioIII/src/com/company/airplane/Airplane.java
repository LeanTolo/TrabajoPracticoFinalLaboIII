package com.company.airplane;



import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public abstract class Airplane{


    private HashSet<LocalDate> dates;
    private String serialNumber;
    private double fuelCapacity;
    private double costPerKm;
    private int maxPassengers;
    private double maxVelocity;
    private MotorType motorType;
    private int fixedFee;



    protected Airplane(String serialNumber,double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        this.serialNumber = serialNumber;
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.maxPassengers = maxPassengers;
        this.maxVelocity = maxVelocity;
        this.motorType = motorType;
        this.dates = new HashSet<>();
    }

    public Airplane() {

    }

    protected void setFixedFee(int fixedFee) {
        this.fixedFee = fixedFee;
    }

    public HashSet<LocalDate> getDates() {
        return dates;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void addDate(LocalDate date){
        dates.add(date);
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public int getFixedFee() {
        return fixedFee;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }


    public double getMaxVelocity() {
        return maxVelocity;
    }

    public MotorType getMotorType() {
        return motorType;
    }

    public void setDates() {
        this.dates.add(LocalDate.of(2020,1,1));
    } //TESTING

    @Override
    public String toString() {
        return "--- Airplane ---" +
                "\nFuelCapacity:" + fuelCapacity +
                "\nCostPerKm:" + costPerKm +
                "\nMaxPassengers:" + maxPassengers +
                "\nMaxVelocity:" + maxVelocity +
                "\nMotorType:" + motorType +
                "\nFixedFee:" + fixedFee +
                "\nDates:"+dates+
                "----------------";
    }
}
