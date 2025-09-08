package com.afs.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ParkingLot {
    private Integer capacity;
    private final Integer CAPACITY = 10;
    private Map<ParkingTicket, Car> ticketCars = new HashMap<>();
    private String notifyMessage = "";

    public String getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = CAPACITY;
    }

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket parkCar(Car car) {
        if (this.ticketCars.size() + 1 > this.capacity) {
            setNotifyMessage("no available position message");
            return null;
        }
        return IntStream.rangeClosed(1, capacity).boxed().filter(position -> ticketCars.keySet()
                        .stream().noneMatch(ticket -> ticket.position() == position)).findFirst()
                .map(position -> {
                    ParkingTicket ticket = new ParkingTicket(car, position, this);
                    ticketCars.put(ticket, car);
                    return ticket;
                }).orElse(null);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (ticketCars.containsKey(parkingTicket)) {
            return ticketCars.remove(parkingTicket);
        }
        setNotifyMessage("unrecognized parking ticket");
        return null;
    }

    public Map<ParkingTicket, Car> getTicketCars() {
        return ticketCars;
    }

    public void setTicketCars(Map<ParkingTicket, Car> ticketCars) {
        this.ticketCars = ticketCars;
    }
}
