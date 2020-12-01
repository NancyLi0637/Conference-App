package com.example.a207_demo.use_cases;

import com.example.a207_demo.entities.Room;
import com.example.a207_demo.entities.Event;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.EventManager class, this is the use case class to manage all events.
 */
public class EventManager implements Serializable {

    private List<Event> events;
    private EventFactory eventFactory;
    private ArrayList<String> allEventType;


    /**
     * Creates an empty event manager
     */
    public EventManager() {
        events = new ArrayList<>();
        eventFactory = new EventFactory();
        this.allEventType = new ArrayList<>();
        allEventType.add("TALK");
        allEventType.add("PARTY");
        allEventType.add("DISCUSSION");
    }

    /**
     * Try to add a speaker to a list of events
     *
     * @param speakerID speakerID String
     * @param events    a list of events
     */
    public boolean addSpeaker(String speakerID, List<Event> events, Event event) {
        for (Event currentEvent : events) {
            for (String speaker : currentEvent.getSpeakers()){
                if (speaker.equals(speakerID) && event.getStartTime().equals(currentEvent.getStartTime())) {
                    return false;
                }
            }
        }
        //Todo: implement addSpeaker for Event (do not directly change list from getSpeakers())
        event.getSpeakers().add(speakerID);
        return true;
    }

    /**
     * Try to remove a speaker from a list of events
     *
     * @param speakerID String
     * @return boolean true if person existed in attendee list
     */
    public boolean removeSpeaker(String speakerID, Event event) {
        ArrayList<String> speakerList = event.getSpeakers();
        if (speakerList.contains(speakerID)) {
            //Todo: implement removeSpeaker for Event (do not directly change list from getSpeakers())
            return speakerList.remove(speakerID);
        }
        return false;
    }

    /**
     * Creates a new event
     *
     * @param title title of the event
     * @param roomID roomID of the event
     * @param speakerID speakerID of the event
     * @param  startTime startTime of the event
     * @return the newly created event or null
     */
    public Event createEvent(String title, String roomID, ArrayList<String> speakerID, String startTime, String duration, String type) {
        for (Event event : this.events) {
            for (String speaker: event.getSpeakers()) {
                if ((event.getSpeakers().contains(speaker) || roomID.equals(event.getRoomID())) &&
                        (!((Integer.parseInt(event.getStartTime()) + Integer.parseInt(event.getDuration())<= Integer.parseInt(startTime)) ||
                                (Integer.parseInt(startTime) + Integer.parseInt(duration)<= Integer.parseInt(event.getStartTime()))))) {
                    return null;
                }
            }
        }
        if (title.length() <= 3) {
            return null;
        }
        // create this new event:
        Event newEvent = eventFactory.createEvent(title, roomID, speakerID, startTime,duration, type);
        // update the events list:
        events.add(newEvent);

        return newEvent;
    }

    /**
     * Create a new event (full version)
     *
     * @param title title
     * @param roomID roomID
     * @param speakerID speakerID
     * @param startTime startTime
     * @param eventID eventID
     * @param attendeeID attendeeID
     * @param roomManager roomManager
     * @return  the newly created event
     */
    public Event loadEvent(String title, String roomID, ArrayList<String> speakerID, String startTime, String eventID,String duration, String type,
                           ArrayList<String> attendeeID, RoomManager roomManager) {
        // create this new event:
        Event newEvent = eventFactory.createEvent(title, roomID, speakerID, startTime,duration, type);
        // update the events list:
        events.add(newEvent);

        // add attendee's IDs to this event
        for (String ID : attendeeID) {
            addAttendeeToEvent(ID, eventID, roomManager);
        }
        return newEvent;
    }

    /**
     * Return a list of all events.
     *
     * @return a list of all events
     */
    public List<Event> getAllEvent() {
        return events;
    }

    /**
     * Adds an attendee to the event
     *
     * Hint (Piazza question @652):
     * "When the user says they want to sign up for an event, they have a username and they know the event name.
     * So the controller method calls the use_cases.EventManager method with two String parameters:
     * userName and eventName.
     * The the use_cases.EventManager, looks through the list of entities.Event objects and finds the one with the
     * correct name and calls addUser(userName).
     * Note: This would add a String with the username to a list of Strings inside the entities.Event, not the
     * entities.User object itself.
     * If you want to add the Even to the entities.User, the Controller would send only the String eventName to the
     * UserManager, to store the entities.Event's name in a list of Strings inside the entities.User object."
     *
     * @param userID userID
     * @param eventID eventID
     * @param roomManager a RoomManager object
     * @return true iff the user has been successfully added to this event
     */
    public boolean addAttendeeToEvent(String userID, String eventID, RoomManager roomManager) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            //Todo: update room in room manager
            //Todo: i.e. if (roommanager.updateSuccessful(room id)) then add attendee to list
            Room room = roomManager.getRoomBasedOnItsID(event.getRoomID());
            if (room.getCurrentNum() < room.getCapacity()) {
                if (event.addAttendee(userID, events)) {
                    room.increaseCurrentNum();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * return an event based on its ID
     *
     * @param eventID eventID String object
     * @return event if eventID existed in events otherwise return null
     */
    public Event getEventFromID(String eventID) {
        for (Event event : events) {
            if (event.getEventID().equals(eventID)) {
                return event;
            }
        }
        return null;
    }

    /**
     * Return a list all Attendees from event with eventID. If event not found, return empty array list.
     *
     * @param eventID String object
     * @return arraylist of attendees' ID from given event, return empty arraylist if event not found.
     */
    public ArrayList<String> getAttendeesFromEvent(String eventID) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            return event.getAttendees();
        }
        return new ArrayList<>();
    }

    /**
     * Return true if we remove the attendee from a event.
     *
     * @param userID userID String object
     * @param eventID eventID String object
     * @param roomManager roomManager
     * @return true  iff the user has been successfully removed from this event
     */
    public boolean removeAttendeeFromEvent(String userID, String eventID, RoomManager roomManager) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            Room room = roomManager.getRoomBasedOnItsID(event.getRoomID());
            room.decreaseCurrentNum();
            return event.removeAttendee(userID);
        }
        return false;
    }

    /**
     * Return ArrayList contains all events that attendee has signed up.
     *
     * @param userID String object,
     * @return ArrayList<String> contains all events that attendee has
     */
    public ArrayList<String> getAllEventForTheAttendee(String userID) {
        ArrayList<String> eventList = new ArrayList<>();
        for (Event event : events) {
            if (event.getAttendees().contains(userID)) {
                eventList.add(event.getEventID());
            }
        }
        return eventList;
    }

    /**
     * Return ArrayList contains all events that speaker has.
     *
     * @param userID String object,
     * @return ArrayList<String>
     */
    public ArrayList<String> getAllEventForTheSpeaker(String userID) {
        ArrayList<String> eventList = new ArrayList<>();
        for (Event event : events) {
            if (event.getSpeakers().contains(userID)) {
                eventList.add(event.getEventID());
            }
        }
        return eventList;
    }

//    public String changeEventIDIntoEventTitle(String eventID) {
//        for (Event event : events) {
//            if (event.getEventID().equals(eventID)) {
//                return event.getTitle();
//            }
//        }
//        return "NULL";
//    }

    /**
     * Given a String representing the title of this event, return the ID of this event, or "NULL"
     * @param eventTitle title String of an event
     * @return event ID
     */
    public String changeEventTitleIntoEventID(String eventTitle) {
        for (Event event : events) {
            if (event.getTitle().equals(eventTitle)) {
                return event.getEventID();
            }
        }
        return "NULL";
    }

    /**
     * Return all Attendees' ids and user names.
     * @return a list of two list containing user ids and user names respectively.
     */
    public ArrayList<ArrayList<String>> getAllIDAndName(){
        ArrayList<String> IDs = new ArrayList<>();
        ArrayList<String> Names = new ArrayList<>();
        for (Event event : events) {
            IDs.add(event.getEventID());
            Names.add(event.getTitle());
            }
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        arrayList.add(IDs);
        arrayList.add(Names);
        return arrayList;
    }

    /**
     * Generate a formatted string representation of the start time String.
     * @param startTime startTime String
     * @return a formatted string representation
     */
    public String generateFormattedStartTime(String startTime) {
        int HourTime = Integer.parseInt(startTime.substring(8, 10));
        String Ending = String.format("%s", (HourTime >= 12) ? "PM" : "AM");
        return String.format("%s/%s/%s/%s%s", startTime.substring(0, 4), startTime.substring(4, 6),
                startTime.substring(6, 8), startTime.substring(8, 10), Ending);
    }

    /**
     * Generate the formatted event's information.
     * @param eventID the id of an event.
     * @return a string of formatted event's information.
     */
    public String generateFormattedEventInfo(String eventID){
        for (Event event : events){
            if (event.getEventID().equals(eventID)){
                return event.getTitle().replace(" ", "_") + " " + event.getRoomID() + " " +
                        event.getSpeakers() + " " + event.getStartTime() + " " +
                        event.getEventID();
            }
        }
        return "NULL";
    }

    public ArrayList<String> getAllEventType (){
        return this.allEventType;
    }

}
