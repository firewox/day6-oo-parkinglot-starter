package com.afs.parkinglot;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParkingLotTest {
    //story1- Case 1 - Given a parking lot, and a car, When park the car, Then return a parking ticket.
    @Test
    void should_return_a_parking_ticket_when_parking_the_car_given_parking_log_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123");
        ParkingTicket ticket = new ParkingTicket(car, 1, parkingLot);
        //When
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //Then
        assertEquals(ticket, parkingTicket);
    }

    //story1- Case 2 - Given a parking lot with a parked car, and a parking ticket, When fetch the car, Then return the parked car.
    @Test
    void should_return_the_parked_car_when_fetch_the_car_given_a_parking_lot_with_a_picked_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("12");
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //When
        Car pickUpCar  = parkingLot.fetchCar(parkingTicket);
        //Then
        assertEquals(car, pickUpCar);
    }


    //story1- Case 3 - Given a parking lot with two parked cars, and two parking tickets, When fetch the car twice, Then return the right car with each ticket

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_the_car_twice_given_a_parking_lot_with_two_parked_cars_and_two_parking_tickets() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car("123");
        Car car2 = new Car("456");
        ParkingTicket ticket1 = new ParkingTicket(car1, 1, parkingLot);
        ParkingTicket ticket2 = new ParkingTicket(car2, 2, parkingLot);
        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        //When
        Car pickUpCar1  = parkingLot.fetchCar(ticket1);
        Car pickUpCar2  = parkingLot.fetchCar(ticket2);
        //Then
        assertEquals(car1, pickUpCar1);
        assertEquals(car2, pickUpCar2);
        assertEquals(null, parkingLot.fetchCar(ticket1));
        assertEquals(null, parkingLot.fetchCar(ticket2));
    }

    //story1- Case 4 - Given a parking lot, and a wrong parking ticket, When fetch the car, Then return nothing.
    @Test
    void should_return_nothing_when_fetch_car_given_a_parking_lot_and_a_wrong_parking_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123");
        ParkingTicket ticket = new ParkingTicket(car, 1, parkingLot);
        //When
        Car pickUpCar  = parkingLot.fetchCar(ticket);
        //Then
        assertEquals(null, pickUpCar);
    }

    //story1- Case 5 - Given a parking lot, and a used parking ticket, When fetch the car, Then return nothing.
    @Test
    void should_return_nothing_when_fetch_car_given_a_parking_lot_and_a_used_parking_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123");
        ParkingTicket ticket = new ParkingTicket(car, 1, parkingLot);
        parkingLot.parkCar(car);
        //When
        Car pickUpCar  = parkingLot.fetchCar(ticket);
        //Then
        assertEquals(null, parkingLot.fetchCar(ticket));
    }

    //story1- Case 6 - Given a parking lot without any position, and a car, When park the car, Then return nothing
    @Test
    void should_return_nothing_when_park_car_given_a_parking_lot_without_any_position_and_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123");
        parkingLot.setCapacity(0);
        //When
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //Then
        assertEquals(null, parkingTicket);
    }

    //story2- case1- Given a unrecognized ticket, When fetch the car, Then return unrecognized parking ticket
    @Test
    void should_return_unrecognized_ticket_message_when_fetch_car_given_a_unrecognized_parking_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123");
        parkingLot.setCapacity(0);
        //When
        Car pickupCar = parkingLot.fetchCar(new ParkingTicket(car,1,new ParkingLot()));
        String message = parkingLot.getNotifyMessage();
        //Then
        assertEquals("unrecognized parking ticket", message);
        assertNull(pickupCar);
    }

    //story2- case2- Given a unused ticket, When fetch the car, Then return unrecognized parking ticket
    @Test
    void should_return_unrecognized_ticket_message_when_fetch_car_given_a_unused_parking_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123");
        parkingLot.setCapacity(0);
        parkingLot.parkCar(car);
        //When
        Car car1 = parkingLot.fetchCar(new ParkingTicket(car, 2, parkingLot));
        String message = parkingLot.getNotifyMessage();
        //Then
        assertNull(car1);
        assertEquals("unrecognized parking ticket", message);
    }

    //story2:case3- Given a parking log, and a car, When park car and parking log without position, Then return no available position message
    @Test
    void should_return_no_available_position_ticket_message_when_park_car_and_parking_log_without_position_given_a_parking_log_and_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123");
        parkingLot.setCapacity(0);
        //When
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        String message = parkingLot.getNotifyMessage();
        //Then
        assertNull(parkingTicket);
        assertEquals("no available position message", message);
    }

}
