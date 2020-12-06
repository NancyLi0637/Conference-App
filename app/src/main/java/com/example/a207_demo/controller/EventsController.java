package com.example.a207_demo.controller;

import com.example.a207_demo.eventSystem.Event;
import com.example.a207_demo.eventSystem.EventManager;
import com.example.a207_demo.roomSystem.Room;
import com.example.a207_demo.roomSystem.RoomManager;
import com.example.a207_demo.use_cases.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main controller for Attendee.
 */
public class EventsController {
    private final EventManager eventManager;
    private final RoomManager roomManager;
    private final SpeakerManager speakerManager;


    /**
     * the constructor for this EventsController
     */
    public EventsController() {
        this.eventManager = new EventManager();
        this.roomManager = new RoomManager();
        this.speakerManager = new SpeakerManager();

    }

    /**
     * Getter method for this eventManager
     *
     * @return this eventManager
     */
    public EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Getter method for this RoomManager
     *
     * @return this RoomManager
     */
    public RoomManager getRoomManager() {
        return this.roomManager;
    }

    /**
     * Getter method for this SpeakerManager()
     *
     * @return this RoomManager
     */
    public SpeakerManager getSpeakerManager() {
        return this.speakerManager;
    }

    /**
     * Get a list of all events
     *
     * @return List<Event>, which is a list of all events
     */
    public List<Event> getAllExistingEvents() {
        return this.eventManager.getAllEvent();
    }

    /**
     * Get All Events For The Attendee with userID
     *
     * @param userID userID String
     * @return ArrayList<String>, which is a list of all events for this Attendee
     */
    public ArrayList<String> getALLAttendeeEvents(String userID) {
        return this.eventManager.getEventsFromAttendee(userID);
    }

    /**
     * Get All Events For The Speaker with userID
     *
     * @param userID userID String
     * @return ArrayList<String>, which is a list of all events for this Speaker
     */
    public ArrayList<String> getAllEventsForTheSpeaker(String userID) {
        return this.eventManager.getEventsFromSpeaker(userID);
    }

    /**
     * Cancel event with eventID for this user with userID
     *
     * @param userID  userID
     * @param eventID eventID
     * @return true iff the cancellation is successful
     */
    public boolean signOutEvent(String userID, String eventID) {
        return this.eventManager.removeAttendeeFromEvent(userID, eventID, this.roomManager);
    }

    /**
     * Return a list of all available rooms at a specific time
     *
     * @param time starting time String
     * @return ArrayList<String>, which is the list of all available rooms
     */
//    public ArrayList<String> getAvailableRoom(String time) {
//        return this.roomManager.getAvailableRoom(time, this.eventManager);
//    }

    /**
     * Create an event
     *
     * @param title title
     * @param roomID roomID
     * @param speakerName speakerName
     * @param startTime startTime
     * @return true iff this event is created successfully
     */
//    public boolean createEvent(String title, String roomID, String speakerName, String startTime, String duration,
//                               String restriction, String type) {
//        ArrayList<String> speakerID = new ArrayList<>();
//        speakerID.add(speakerManager.getIdFromName(speakerName));
//        Event newEvent = this.eventManager.createEvent(title, roomID, speakerID, startTime, duration, restriction, type);
//        if (newEvent == null) {
//            return false;
//        }
//        this.roomManager.addEventToRoom(newEvent.getEventID(), roomID);
//        return true;
//    }

    /**
     * Return a list of String with information about this event with event ID: title, start time, speakers, current
     * number of people for this room, and capacity for this room
     *
     * @param eventID eventID
     * @return ArrayList<String> a information about this event with event ID
     */
    public ArrayList<String> getEventInfo(String eventID) {
        ArrayList<String> info = new ArrayList<>();
        Event event = this.eventManager.getEventFromID(eventID);
        Room room = this.roomManager.getRoomBasedOnItsID(event.getRoomID());
        info.add(event.getTitle());
        info.add(event.getStartTime());
//        for (String speaker : event.getSpeakers()) {
//            info.add(speakerManager.getSpeakerNameFromID(speaker));
//        }
        info.add(Integer.toString(room.getCurrentNum()));
        info.add(Integer.toString(room.getCapacity()));
//        info.add(event.getRestriction());
        return info;
    }

    /**
     * Return a list of String which is the available speakers for a specific time
     *
     * @param time time String
     * @return a list of String which is the available speakers
     */
//    public ArrayList<String> getAllAvailableSpeaker(String time, String duration) {
//        return this.speakerManager.getAllAvailableSpeaker(time, eventManager, duration);
//    }

    /**
     * Return a list of Attendees from an event with the given ID
     *
     * @param eventID eventID
     * @return a list of Attendees
     */
    public ArrayList<String> getAttendeesFromEvent(String eventID) {
        return eventManager.getAttendeesFromEvent(eventID);
    }

    /**
     * Return all Attendees' ids and user names.
     *
     * @return a list of two list containing user ids and user names respectively.
     */
    public ArrayList<ArrayList<String>> getAllIDAndName() {
        return eventManager.getAllIDAndName();
    }

//    public ArrayList<String> getAllVIPEvents() { return eventManager.getAllVIPEvents();}

    public boolean cancelEvent(String eventID) {
        Event currentEvent = eventManager.getEventFromID(eventID);
        List<Event> eventList = eventManager.getAllEvent();
        eventList.remove(currentEvent);
        ArrayList<String> attendeeList = currentEvent.getAttendees();
        for (String attendee : attendeeList) {
            eventManager.removeAttendeeFromEvent(attendee, eventID, roomManager);
        }
        return roomManager.getRoomBasedOnItsID(currentEvent.getRoomID()).getCurrentNum() == 0;
    }
}
