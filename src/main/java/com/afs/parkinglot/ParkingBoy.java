package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    private List<ParkingLot> parkingLot;
    private String notifyMessage;

    public ParkingBoy() {
        this.parkingLot = Collections.singletonList(new ParkingLot());
    }

    public ParkingBoy(List<ParkingLot> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot.add(parkingLot);
    }

    public ParkingTicket parkCar(Car car) {
        if (Objects.isNull(car)) {
            this.notifyMessage = "car is empty";
            return null;
        }
        ParkingLot parkingLot1 = this.parkingLot.stream().filter(lot ->
                lot.getTicketCars().size() + 1 <= lot.getCapacity()
        ).findFirst().orElse(null);
        if (Objects.isNull(parkingLot1)) {
            this.notifyMessage = "no available position message";
            return null;
        }
        return parkingLot1.parkCar(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot currentParkingLot = this.parkingLot.stream().filter(lot -> {
            return lot.getTicketCars().containsKey(parkingTicket);
        }).findFirst().orElse(null);
        if (Objects.isNull(currentParkingLot)) {
            this.notifyMessage = "unrecognized parking ticket";
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
