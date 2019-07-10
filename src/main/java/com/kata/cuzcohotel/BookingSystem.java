package com.kata.cuzcohotel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingSystem {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public BookingSystem(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> findAvailableRooms(Calendar checkinDate, Calendar checkoutDate) {
        List<Room> bookedRooms = findBookedRooms(checkinDate, checkoutDate);
        return rooms.stream()
                .filter(room -> {
                    return bookedRooms
                            .stream()
                            .noneMatch(bookedRoomAtThoseDates -> bookedRoomAtThoseDates == room);
                })
                .collect(Collectors.toList());
    }

    private List<Room> findBookedRooms(Calendar checkinDate, Calendar checkoutDate) {
        return reservations.stream()
                .filter(reservation -> !isAvailable(reservation, checkinDate, checkoutDate))
                .map(reservation -> reservation.room).collect(Collectors.toList());
    }

    private boolean isAvailable(Reservation reservation, Calendar checkinDate, Calendar checkoutDate) {
        return (checkoutDate.before(reservation.checkinDate)
                || checkoutDate.equals(reservation.checkinDate)
                || checkinDate.after(reservation.checkoutDate)
                || checkinDate.equals(reservation.checkoutDate));
    }


    public void makeAReservation(Calendar checkinDate, Calendar checkoutDate, int roomNumber) {
        reservations.add(new Reservation(checkinDate, checkoutDate, findRoom(roomNumber)));
    }

    private Room findRoom(int roomNumber) {
        return rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().get();
    }

}
