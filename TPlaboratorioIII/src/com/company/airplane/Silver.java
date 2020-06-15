package com.company.airplane;

public class Silver extends Airplane {
    private boolean cateringService;
    public Silver(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        super(fuelCapacity, costPerKm, maxPassengers, maxVelocity, motorType);
        setFixedFee(4000);
    }
}
