package com.company.airplane;

public class Bronze extends Airplane {
    public Bronze(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        super(fuelCapacity, costPerKm, maxPassengers, maxVelocity, motorType);
        setFixedFee(3000);
    }

}
