package com.example.ConferenceRoomApplication.entity;

import java.sql.Time;

public class Booking {

    private String bookingId;
    private User user;
    private TimeSlot timeSlot;
    private ConferenceRoom conferenceRoom;

    public Booking(String bookingId, User user, TimeSlot timeSlot, ConferenceRoom conferenceRoom) {
        this.bookingId = bookingId;
        this.user = user;
        this.timeSlot = timeSlot;
        this.conferenceRoom = conferenceRoom;
    }

    public Booking(){};

    public boolean overlaps(Booking other) {
        return conferenceRoom.getRoomId().equals(other.conferenceRoom.getRoomId()) && timeSlot.overlaps(other.timeSlot);
    }


    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public ConferenceRoom getConferenceRoom() {
        return conferenceRoom;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", user=" + user +
                ", timeSlot=" + timeSlot +
                ", conferenceRoom=" + conferenceRoom +
                '}';
    }
}
