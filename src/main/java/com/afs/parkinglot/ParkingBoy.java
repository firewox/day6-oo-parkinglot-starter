package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    private final ParkingLot PARKINGLOT = new ParkingLot();
    private List<ParkingLot> parkingLot;
    private Car car;
    private String notifyMessage;

    public ParkingBoy() {
        if (Objects.isNull(this.parkingLot)) {
            this.parkingLot = new ArrayList<>(Collections.singleton(PARKINGLOT));
        }
    }

    public ParkingBoy(Car car, ParkingLot parkingLot) {
        this.parkingLot.add(parkingLot);
        this.car = car;
    }

    public ParkingBoy(Car car) {
        if (Objects.isNull(this.parkingLot)) {
            this.parkingLot.add(PARKINGLOT);
        }
        this.car = car;
    }


    public ParkingTicket parkCar() {
        if (Objects.isNull(this.car)) {
            this.notifyMessage = "no car";
            return null;
        }
        ParkingLot parkingLot1 = this.parkingLot.stream().filter(lot ->
                lot.getTicketCars().size() + 1 <= lot.getCapacity()
        ).findFirst().orElse(null);
        return parkingLot1 != null ? parkingLot1.parkCar(this.car) : null;
    }

    public ParkingTicket parkCar(Car car) {
        if (Objects.isNull(car)) {
            this.notifyMessage = "no car";
            return null;
        }
        ParkingLot parkingLot1 = this.parkingLot.stream().filter(lot ->
                lot.getTicketCars().size() + 1 <= lot.getCapacity()
        ).findFirst().orElse(null);
        return parkingLot1 != null ? parkingLot1.parkCar(car) : null;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot currentParkingLot = this.parkingLot.stream().filter(lot -> {
            return lot.getTicketCars().containsKey(parkingTicket);
        }).findFirst().orElse(null);
        if (Objects.isNull(currentParkingLot)) {
            this.notifyMessage = "no existing car";
            return null;
        }
        return currentParkingLot.fetchCar(parkingTicket);
    }

    public String getNotifyMessage() {
        return this.notifyMessage;
    }

    public List<ParkingLot> getParkingLot() {
        return this.parkingLot;
    }

    public void setParkingLot(List<ParkingLot> parkingLot) {
        this.parkingLot = parkingLot;
    }
}
