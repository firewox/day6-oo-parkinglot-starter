package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingBoyTest {
    //story3：case2- Given a parking log, and a car, and a parking boy, When park car, Then return a parking ticket
    @Test
    void should_return_a_parking_ticket_when_park_car_given_a_parking_log_and_a_car_and_a_parking_boy() {
        //Given
        Car car = new Car("123");
        ParkingBoy parkingBoy = new ParkingBoy();
        //When
        ParkingTicket ticket = parkingBoy.parkCar(car);
        //Then
        ParkingTicket parkingTicket = new ParkingTicket(car, 1, parkingBoy.getParkingLot().get(0));
        assertEquals(parkingTicket,ticket);
    }

    //story3：case3- Given a parking log, and a ticket, and a parking boy, When fetch car, Then return a car
    @Test
    void should_return_a_car_when_fetch_car_given_a_parking_log_and_a_ticket_and_a_parking_boy() {
        //Given
        Car car = new Car("123");
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket ticket = parkingBoy.parkCar(car);
        //When
        Car pickupCar = parkingBoy.fetchCar(ticket);
        //Then
        assertEquals(car, pickupCar);
    }

    //story3：case4- Given a parking log with two parked cars and two parking tickets, When fetch car twice, Then return right car with each ticket
    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_the_car_twice_given_a_parking_lot_with_two_parked_cars_and_two_parking_tickets_and_parking_boy() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car("123");
        Car car2 = new Car("456");
        ParkingTicket ticket1 = new ParkingTicket(car1, 1, parkingBoy.getParkingLot().get(0));
        ParkingTicket ticket2 = new ParkingTicket(car2, 2, parkingBoy.getParkingLot().get(0));
        parkingBoy.parkCar(car1);
        parkingBoy.parkCar(car2);
        //When
        Car pickUpCar1  = parkingBoy.fetchCar(ticket1);
        Car pickUpCar2  = parkingBoy.fetchCar(ticket2);
        //Then
        assertEquals(car1, pickUpCar1);
        assertEquals(car2, pickUpCar2);
        assertEquals(null, parkingBoy.fetchCar(ticket1));
        assertEquals(null, parkingBoy.fetchCar(ticket2));
    }



    // story4: case1- Given: two parking lots, and a parking boy; When first parking lot is full; Then park car to second parking lot;
    @Test
    void should_park_car_to_second_parking_lot_when_first_parking_lot_is_full_given_two_parking_lots_and_a_parking_boy(){
        //Given
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(1), new ParkingLot(2)));
        Car car = new Car("123");
        Car car2 = new Car("456");
        ParkingTicket carParkingTicket = parkingBoy.parkCar(car);
        //When
        ParkingTicket car2ParkingTicket = parkingBoy.parkCar(car2);
        //Then
        assertEquals(car, parkingBoy.getParkingLot().get(0).getTicketCars().get(carParkingTicket));
        assertEquals(car2, parkingBoy.getParkingLot().get(1).getTicketCars().get(car2ParkingTicket));
    }


}
