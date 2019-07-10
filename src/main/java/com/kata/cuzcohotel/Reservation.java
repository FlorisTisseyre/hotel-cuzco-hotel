package com.kata.cuzcohotel;

import java.util.Calendar;

public class Reservation {
    public Room room;
    public Calendar checkinDate;
    public Calendar checkoutDate;

    public Reservation(Calendar checkinDate, Calendar checkoutDate, Room room) {

        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.room = room;
    }
}
