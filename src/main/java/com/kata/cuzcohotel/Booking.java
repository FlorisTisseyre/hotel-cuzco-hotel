package com.kata.cuzcohotel;

import java.util.Calendar;

public class Booking {
    private Room room;
    private Calendar checkinDate;
    private Calendar checkoutDate;

    public Booking(Calendar checkinDate, Calendar checkoutDate, Room room) {

        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public Calendar getCheckinDate() {
        return checkinDate;
    }

    public Calendar getCheckoutDate() {
        return checkoutDate;
    }
}
