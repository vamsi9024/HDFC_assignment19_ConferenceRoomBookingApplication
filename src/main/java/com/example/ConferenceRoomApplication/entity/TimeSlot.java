package com.example.ConferenceRoomApplication.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlot {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot(){}

    public boolean overlaps(TimeSlot other){
        return this.date.equals(other.date) &&
                !(this.endTime.isBefore(other.startTime) || this.startTime.isAfter(other.endTime));
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
