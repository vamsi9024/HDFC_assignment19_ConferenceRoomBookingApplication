package com.example.ConferenceRoomApplication.entity;

import java.util.List;

public class ConferenceRoom {

    private String roomId;
    private String name;
    private int capacity;
    private List<String> equipment;
    private String location;

    public ConferenceRoom(String roomId, String name, int capacity, List<String> equipment, String location) {
        this.roomId = roomId;
        this.name = name;
        this.capacity = capacity;
        this.equipment = equipment;
        this.location = location;
    }


    public ConferenceRoom(){}

    public boolean matchRequirements(int requiredCapacity,List<String> requiredEquipments){
        return this.capacity>=requiredCapacity && this.equipment.containsAll(requiredEquipments);
    }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    @Override
    public String toString() {
        return "ConferenceRoom{" +
                "roomId='" + roomId + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", equipment=" + equipment +
                ", location='" + location + '\'' +
                '}';
    }
}
