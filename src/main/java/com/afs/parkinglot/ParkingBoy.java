package com.afs.parkinglot;

import java.util.Objects;

public class ParkingBoy {
    private final ParkingLot PARKINGLOT = new ParkingLot();
    private ParkingLot parkingLot;
    private Car car;
    private String notifyMessage;

    public ParkingBoy() {
        this.parkingLot = this.parkingLot == null ? PARKINGLOT : this.parkingLot;
    }

    public ParkingBoy(Car car, ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.car = car;
    }

    public ParkingBoy(Car car) {
        this.parkingLot = this.parkingLot == null ? PARKINGLOT : this.parkingLot;
        this.car = car;
    }


    public ParkingTicket parkCar() {
        if (Objects.isNull(this.car)) {
            this.notifyMessage = "no car";
            return null;
        }
        return this.parkingLot.parkCar(this.car);
    }

    public ParkingTicket parkCar(Car car) {
        if (Objects.isNull(car)) {
            this.notifyMessage = "no car";
            return null;
        }
        return this.parkingLot.parkCar(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return this.parkingLot.fetchCar(parkingTicket);
    }

    public String getNotifyMessage() {
        return this.parkingLot.getNotifyMessage();
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
