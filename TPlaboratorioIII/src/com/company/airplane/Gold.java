package com.company.airplane;

public class Gold extends Silver {
    private boolean wifiConnection;

    public Gold(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        super(fuelCapacity, costPerKm, maxPassengers, maxVelocity, motorType);
        setFixedFee(6000);
    }

    @Override
    public String toString() {
        return "Gold " +super.toString()+
                "wifiConnection=" + wifiConnection +
                "} ";
    }
}
