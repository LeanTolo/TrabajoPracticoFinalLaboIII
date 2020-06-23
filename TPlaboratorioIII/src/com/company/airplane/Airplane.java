package com.company.airplane;



import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public   class Airplane{


    private HashSet<LocalDate> dates;
    private String serialNumber;
    private double fuelCapacity;
    private double costPerKm;
    private int maxPassengers;
    private double maxVelocity;
    private MotorType motorType;
    private int fixedFee;



    public Airplane(String serialNumber,double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        this.dates = new HashSet<>();
        this.serialNumber = serialNumber;
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.maxPassengers = maxPassengers;
        this.maxVelocity = maxVelocity;
        this.motorType = motorType;
    }

    public Airplane() {

    }

    public void setFixedFee(int fixedFee) {
        this.fixedFee = fixedFee;
    }


    public void setDatesEmpty() {
        this.dates = new HashSet<>();
    }

    public void setFuelCapacity(double fuel){
        this.fuelCapacity = fuel;
    }

    public void setCostPerKm (double cost){
        this.costPerKm = cost;
    }

    public void setMaxPassengers (int max){
        this.maxPassengers = max;
    }

    public void setMaxVelocity (double vel){
        this.maxVelocity = vel;
    }

    public void setMotorType ( MotorType mot){
        this.motorType = mot;
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

    public void removeDate(LocalDate date){
        dates.remove(date);
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
                "\nSerialNumber:" + serialNumber +
                "\nFuelCapacity:" + fuelCapacity +
                "\nCostPerKm:" + costPerKm +
                "\nMaxPassengers:" + maxPassengers +
                "\nMaxVelocity:" + maxVelocity +
                "\nMotorType:" + motorType +
                "\nFixedFee:" + fixedFee +
                "\nDates:"+dates;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber(){
        return this.serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return serialNumber.equals(airplane.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber);
    }
}
