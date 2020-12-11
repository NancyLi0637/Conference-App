package com.example.a207_demo.roomSystem;


import com.example.a207_demo.eventSystem.Event;
import com.example.a207_demo.eventSystem.EventManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The use_cases.RoomManager class, this is the use case class to manage all rooms.
 */
public class RoomManager implements Serializable {

    private List<Room> rooms;
    private List<String> events;

    private final Map<String, List<String>> eventsMap;  // Map from roomID to events

    /**
     * Creates an empty room manager
     */
    public RoomManager() {
        rooms = new ArrayList<>();
        events = new ArrayList<>();
        eventsMap = new HashMap<>();
    }

    /**
     * Reset the room list.
     */
    public void reset() {
        this.rooms = new ArrayList<>();
    }

    /**
     * Check if there are any rooms
     * @return true iff there is at least one room
     */
    public boolean hasRooms() {
        return this.rooms.size() > 0;
    }

    /**
     * Input roomID, output its room number
     *
     * @param roomID roomID
     * @return roomNum
     */
    public String changeIdTONum(String roomID) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) {
                return room.getRoomNum();
            }
        }
        return "NULL";
    }

    /**
     * Input a list of roomID, output all of the names
     *
     * @param  roomIDs roomIDs
     * @return roomNums
     */
    public ArrayList<String> changeIdsTONums(ArrayList<String> roomIDs){
        ArrayList<String> roomNums = new ArrayList<>();
        for (String roomID : roomIDs){
            roomNums.add(changeIdTONum(roomID));
        }
        return roomNums;
    }

    /**
     * Input String room number, output its ID
     *
     * @param roomNum roomNum
     * @return roomID
     */
    public String changeNumTOID(String roomNum) {
        for (Room room : rooms) {
            if (room.getRoomNum().equals(roomNum)) {
                return room.getRoomID();
            }
        }
        return "NULL";
    }

    /**
     * Search through the list of rooms, return room given its ID
     *
     * @param roomID roomID
     * @return a Room object
     */
    public Room getRoomFromID(String roomID) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Get an ArrayList<String> of all room numbers
     *
     * @return ArrayList<String> of all room numbers
     */
    public ArrayList<String> getAllRoomNum() {
        ArrayList<String> roomNumbers = new ArrayList<>();
        for (Room room : this.rooms) {
            roomNumbers.add(room.getRoomNum());
        }
        return roomNumbers;
    }


    /**
     * Get an ArrayList<String> of all room IDs
     *
     * @return ArrayList<String> of all room IDs
     */
    public ArrayList<String> getAllRoomID() {
        ArrayList<String> roomIDs = new ArrayList<>();
        for (Room room : this.rooms) {
            roomIDs.add(room.getRoomID());
        }
        return roomIDs;
    }

    public int getRoomCapFromEvent(String eventID){
        for(String roomID : eventsMap.keySet()){
            for(String event : eventsMap.get(roomID)){
                if(event.equals(eventID)){
                   return getRoomFromID(roomID).getCapacity();
                }
            }
        }
        return -1;
    }


    /**
     * Get an ArrayList<String> of room numbers that are available for the given time
     *
     * @param time         the starting time
     * @param eventManager an EventManager object
     * @return an ArrayList<String> of room numbers that are available for the given time
     */
    public ArrayList<String> getAvailableRoom(String time, String duration, int capacity, EventManager eventManager) {
        ArrayList<String> roomList = new ArrayList<>();

        // First step, add all room numbers to the roomList
        for (Room room : rooms) {
            if(room.getCapacity() >= capacity){
                roomList.add(room.getRoomID());
            }
        }

        // Next step, remove unavailable room numbers from the roomList
        for (String roomID : eventsMap.keySet()) {
            if(roomList.contains(roomID)){
                // Loop though the list of eventIDs of the current room's events:
                for (String eventID : eventsMap.get(roomID)) {
                    // Find the event object with this event ID
                    Event event = eventManager.getEventFromID(eventID);

                    // if the time conflicts, then the room is not available
                    if (!event.timeConflict(time, duration)) {
                        roomList.remove(roomID);
                    }
                }
            }
        }
        return changeIdsTONums(roomList);
    }

    /**
     * Creates a new room
     *
     * @param roomNum the room to create and add
     * @return true iff successfully creates and adds the room
     */
    public boolean createRoom(String roomNum, int capacity) {
        for (Room room : this.rooms) {
            if (room.getRoomNum().equals(roomNum)) {
                return false;
            }
        }
        Room room = new Room(roomNum, capacity);
        this.rooms.add(room);
        return true;
    }


    /**
     * Creates a new room and add it to rooms list
     *
     * @param roomNum the room to create and add
     * @param roomID  the room ID
     */
    public void loadRoom(String roomNum, String roomID, int capacity) {
        Room room = new Room(roomNum, roomID, capacity);
        this.rooms.add(room);
    }

    /**
     * Adds an event to the specified room and update events list and eventsMap
     *
     * @param roomID the room to create and add
     */
    public void addEventToRoom(String event, String roomID) {
        if(!events.contains(event)){
            if (!eventsMap.containsKey(roomID)) {
                events = new ArrayList<>();
            } else {
                events = eventsMap.get(roomID);
            }

            events.add(event);
            eventsMap.put(roomID, events);
        }
    }

    /**
     * checkValidNum
     *
     * @param roomNum String
     * @return boolean
     */
    public boolean checkValidNum(String roomNum) {
        try {
            int num = Integer.parseInt(roomNum);
            return num > 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    public String generateFormattedRoomInfo(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomId)) {
                return "Room" + room.getRoomNum() + " " + roomId + " " + room.getCapacity();
            }
        }
        return null;
    }

    /**
     * Generate the event info for loading into event activity
     *
     * @return
     */
    public ArrayList<ArrayList<String>> generateAllInfo() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (Room room : rooms) {
            ArrayList<String> info = new ArrayList<>();
            info.add("Room" + room.getRoomNum());
            info.add(String.valueOf(room.getCapacity()));
            result.add(info);
        }
        return result;
    }

}