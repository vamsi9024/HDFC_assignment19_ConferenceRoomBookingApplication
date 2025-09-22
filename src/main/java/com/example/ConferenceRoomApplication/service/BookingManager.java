package com.example.ConferenceRoomApplication.service;

import com.example.ConferenceRoomApplication.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class BookingManager {

    private List<Booking> bookingList=new ArrayList<>();
    private Map<String, Building> buildings=new HashMap<>();

    public void addBuilding(Building building){
        buildings.put(building.getBuildingId(), building);
    }

    public List<ConferenceRoom> viewAvailableRooms(LocalDate date, LocalTime startTime, LocalTime endTime, int capacity, List<String> requiredEquipment){
        TimeSlot requestedSlot=new TimeSlot(date,startTime,endTime);
        List<ConferenceRoom> availableRooms=new ArrayList<>();
        for(Building building:buildings.values()){
            for(ConferenceRoom room:building.getRooms()){
               boolean isBooked=bookingList.stream()
                        .anyMatch(b->b.getConferenceRoom().getRoomId().equals(room.getRoomId()) && b.getTimeSlot().overlaps(requestedSlot));

               if(!isBooked && room.matchRequirements(capacity,requiredEquipment)){
                   availableRooms.add(room);
               }
            }
        }
//        System.out.println(availableRooms);
        return availableRooms;
    }

    public Booking bookRoom(User user, String roomId, TimeSlot slot){

        if(slot.getStartTime().isBefore(LocalTime.of(9,0)) || slot.getEndTime().isAfter(LocalTime.of(18,0))){
            throw new IllegalArgumentException("Booking must be within the working hours(9 AM to 6 PM)");
        }

        for(Booking booking:bookingList){
            if(booking.getConferenceRoom().getRoomId().equals(roomId) && booking.getTimeSlot().overlaps(slot)){
                throw new IllegalArgumentException("Room already Booked for this time slot");
            }
        }

        ConferenceRoom room=buildings.values().stream()
                .flatMap(b->b.getRooms().stream())
                .filter(r->r.getRoomId().equals(roomId))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Room Not Found"));

        Booking booking=new Booking(UUID.randomUUID().toString(),user,slot,room);
        bookingList.add(booking);

        return booking;
    }

    public boolean cancelBooking(String bookingId,User user){
        Optional<Booking> bookingOpt=bookingList.stream()
                .filter(b->b.getBookingId().equals(bookingId) && b.getUser().getUserId().equals(user.getUserId()))
                .findFirst();

        if(bookingOpt.isPresent()){
            bookingList.remove(bookingOpt.get());
            return true;
        }
        return false;
    }

    public List<Booking> getUserBookings(User user){
        return bookingList.stream()
                .filter(b->b.getUser().getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }

}
