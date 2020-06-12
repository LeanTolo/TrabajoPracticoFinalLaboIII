package com.company.airplane;

public abstract class Airplanes {


    private double fuelCapacity;
    private double costPerKm;
    private int maxPassengers;
    private double maxVelocity;
    private MotorType motorType;
    private int fixedFee;

    protected Airplanes(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType, int fixedFee) {
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.maxPassengers = maxPassengers;
        this.maxVelocity = maxVelocity;
        this.motorType = motorType;
        this.fixedFee = fixedFee;
    }

    @Override
    public String toString() {
        return "Airplane[fuelcapacity="+fuelCapacity+" costPerKm="+costPerKm+" maxPassengers="+maxPassengers+
                " maxVelocity="+maxVelocity+" motorType="+motorType.name()+"]";
    }
}
