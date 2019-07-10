package com.kata.cuzcohotel;

import com.kata.cuzcohotel.repository.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class BookingSystem {
    private final List<Room> rooms;
    private Repository<Booking> repository;

    public BookingSystem(List<Room> rooms, Repository<Booking> repository) {
        this.rooms = rooms;
        this.repository = repository;
    }

    public List<Room> findAvailableRooms(Calendar checkinDate, Calendar checkoutDate) {
        List<Room> bookedRooms = findBookedRooms(checkinDate, checkoutDate);
        return ListUtils.substract(rooms, bookedRooms);
    }

    private List<Room> findBookedRooms(Calendar checkinDate, Calendar checkoutDate) {
        return repository.getAll().stream()
                .filter(booking -> !isAvailable(booking, checkinDate, checkoutDate))
                .map(booking -> booking.getRoom()).collect(Collectors.toList());
    }

    private boolean isAvailable(Booking booking, Calendar checkinDate, Calendar checkoutDate) {
        return (checkoutDate.before(booking.getCheckinDate())
                || checkoutDate.equals(booking.getCheckinDate())
                || checkinDate.after(booking.getCheckoutDate())
                || checkinDate.equals(booking.getCheckoutDate()));
    }

    public void makeAReservation(Calendar checkinDate, Calendar checkoutDate, int roomNumber) {
        repository.save(new Booking(checkinDate, checkoutDate, findRoom(roomNumber)));
    }

    private Room findRoom(int roomNumber) {
        return rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().get();
    }

}
