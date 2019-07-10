package com.kata.cuzcohotel;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;


public class BookingSystemTest {

    Calendar FIRST_DAY = new GregorianCalendar(2019, 07, 01);
    Calendar SECOND_DAY = new GregorianCalendar(2019, 07, 02);
    Calendar THIRD_DAY = new GregorianCalendar(2019, 07, 03);
    Calendar FOURTH_DAY = new GregorianCalendar(2019, 07, 04);


    @Test
    public void shouldReturnAllRoomsWhenNoBooking() {
        BookingSystem sut = new BookingSystem(Arrays.asList(new Room(101)));
        assertEquals(sut.findAvailableRooms(FIRST_DAY, SECOND_DAY).size(), 1);
    }

    @Test
    public void shouldReturnNoRoomsWhenAllIsBooked() {
        BookingSystem sut = new BookingSystem(Arrays.asList(new Room(101)));
        sut.makeAReservation(FIRST_DAY, SECOND_DAY, 101);
        assertEquals(sut.findAvailableRooms(FIRST_DAY, SECOND_DAY).size(), 0);
    }

    @Test
    public void shouldReturnOneRoomLessWhen1HasBeenBooked() {
        BookingSystem sut = new BookingSystem(Arrays.asList(new Room(101), new Room(102)));
        sut.makeAReservation(FIRST_DAY, SECOND_DAY, 101);
        assertEquals(sut.findAvailableRooms(FIRST_DAY, SECOND_DAY).size(), 1);
    }

    @Test
    public void shouldReturnAvailableTodayIfBookedTomorrow() {
        BookingSystem sut = new BookingSystem(Arrays.asList(new Room(101)));
        sut.makeAReservation(SECOND_DAY, THIRD_DAY, 101);

        assertEquals(sut.findAvailableRooms(FIRST_DAY, SECOND_DAY).size(), 1);
        assertEquals(sut.findAvailableRooms(SECOND_DAY, THIRD_DAY).size(), 0);
        assertEquals(sut.findAvailableRooms(THIRD_DAY, FOURTH_DAY).size(), 1);
        assertEquals(sut.findAvailableRooms(FIRST_DAY, FOURTH_DAY).size(), 0);
        assertEquals(sut.findAvailableRooms(FIRST_DAY, THIRD_DAY).size(), 0);
    }
    @Test
    public void shouldReturnAvailableIfNoBooking() {
        BookingSystem sut = new BookingSystem(Arrays.asList(new Room(101)));
        assertEquals(sut.findAvailableRooms(FIRST_DAY, SECOND_DAY).size(), 1);
    }

    @Test
    public void shouldBeAvailableBetweenTwoBookings(){
        BookingSystem sut = new BookingSystem(Arrays.asList(new Room(101)));
        sut.makeAReservation(FIRST_DAY, SECOND_DAY,101);
        sut.makeAReservation(THIRD_DAY, FOURTH_DAY,101);

        assertEquals(1, sut.findAvailableRooms(SECOND_DAY, THIRD_DAY).size());
        assertEquals(0, sut.findAvailableRooms(FIRST_DAY, SECOND_DAY).size());
        assertEquals(0, sut.findAvailableRooms(THIRD_DAY, FOURTH_DAY).size());
    }

}