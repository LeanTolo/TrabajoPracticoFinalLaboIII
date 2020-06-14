package com.company.airplane;

public class Gold extends Airplane {
    private boolean wifiConnection;
    protected Gold(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        super(fuelCapacity, costPerKm, maxPassengers, maxVelocity, motorType, 6000);
    }
}
