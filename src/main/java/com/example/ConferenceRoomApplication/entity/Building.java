package com.example.ConferenceRoomApplication.entity;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private String buildingId;
    private String buildingName;
    private List<ConferenceRoom> rooms;

    public Building(String buildingId, String buildingName) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        rooms=new ArrayList<>();
    }

    public Building(){}

    public void addRoom(ConferenceRoom room){
        this.rooms.add(room);
    }

    public String getBuildingId() {
        return buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public List<ConferenceRoom> getRooms() {
        return rooms;
    }

}
