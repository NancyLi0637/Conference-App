package com.example.a207_demo.eventSystem;

import com.example.a207_demo.roomSystem.Room;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.roomSystem.RoomManager;
import com.example.a207_demo.use_cases.SpeakerManager;
import com.example.a207_demo.use_cases.UserManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The use_cases.EventManager class, this is the use case class to manage all events.
 */
public class EventManager implements Serializable{

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
     * Reset events list
     */
    public void reset(){
        events = new ArrayList<>();
    }

    /**
     * Add an event to event list
     * @param event
     */
    public void setEvents(Event event){
        this.events.add(event);
    }

    /**
     * Return a list of all events.
     *
     * @return a list of all events
     */
    public List<Event> getAllEvent() {
        return events;
    }

    public ArrayList<String> getAllEventType (){
        return this.allEventType;
    }

    /**
     * Return a list all the VIP-only event id's
     * @return ArrayList<String> containing the event ID of all VIP-only events
     */
    public ArrayList<String> getAllVIPEvents() {
        List<Event> allEvents = this.getAllEvent();
        ArrayList<String> vipEvents = new ArrayList<>();
        for (Event event : allEvents){
            if (event.getRestriction().equals("VIP-ONLY")){
                vipEvents.add(event.getEventID());
            }
        }
        return vipEvents;
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
    public boolean createEvent(String title, String roomID, ArrayList<String> speakerID, String startTime, String duration,
                             String restriction, String type) {
//        for (Event event : this.events) {
//            for (String speaker: event.getSpeakers()) {
//                if ((event.getSpeakers().contains(speaker) || roomID.equals(event.getRoomID())) &&
//                        (!((Integer.parseInt(event.getStartTime()) + Integer.parseInt(event.getDuration())<= Integer.parseInt(startTime)) ||
//                                (Integer.parseInt(startTime) + Integer.parseInt(duration)<= Integer.parseInt(event.getStartTime()))))) {
//                    return false;
//                }
//            }
//        }

        for(Event event : this.events){
            if(event.timeConflict(startTime, duration)){
                return false;
            }
        }
        // create this new event:
        Event newEvent = eventFactory.createEvent(title, roomID, speakerID, startTime, duration, restriction, type);
        // update the events list:
        events.add(newEvent);

        return true;
    }


    /**
     * Create a new event (full version)
     *
     * @param title title
     * @param roomName roomID
     * @param speakerID speakerID
     * @param startTime startTime
     * @param eventID eventID
     * @param attendeeID attendeeID
     * @param roomManager roomManager
     * @return  the newly created event
     */
    public Event loadEvent(String title, String roomName, ArrayList<String> speakerID, String startTime, String eventID,
                           String duration, String restriction, String type, ArrayList<String> attendeeID,
                           RoomManager roomManager, AttendeeManager attendeeManager) {
        // create this new event:
        Event newEvent = eventFactory.createEvent(title, roomName, speakerID, startTime,duration, restriction, type);
        // update the events list:
        events.add(newEvent);

        // add attendee's IDs to this event
        for (String ID : attendeeID) {
            addAttendeeToEvent(ID, eventID, roomManager, attendeeManager);
        }
        return newEvent;
    }

    //Todo: eventId?
    public void loadEvent(String type, String title, String eventID, String roomID, ArrayList<String> speakerID,
                          String startTime, String duration, String restriction) {
        // create this new event:
        Event newEvent = eventFactory.createEvent(title, roomID, speakerID, startTime,duration, restriction, type);
        // update the events list:
        events.add(newEvent);

        // add attendee's IDs to this event
//        for (String ID : attendeeID) {
//            addAttendeeToEvent(ID, eventID, roomManager);
//        }

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
    public boolean addAttendeeToEvent(String userID, String eventID, RoomManager roomManager, AttendeeManager attendeeManager) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            String restriction = event.getRestriction();
            String userType = attendeeManager.getUserType(userID);
            if (!(restriction.equals("VIP-ONLY") && !userType.equals("VIPUser"))) {
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
        }
        return false;
    }

    /**
     * Try to add a speaker to a list of events
     *
     * @param speakerID speakerID String
     * @param events    a list of events
     */
    public boolean addSpeakerToEvent(String speakerID, List<Event> events, Event event) {
        for (Event currentEvent : events) {
            //Todo: implement has Speaker in Event
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
            //Room room = roomManager.getRoomBasedOnItsID(event.getRoomID());
            //room.decreaseCurrentNum();
            //Todo: check remove success first
            return event.removeAttendee(userID);
        }
        return false;
    }

    /**
     * Try to remove a speaker from a list of events
     *
     * @param speakerID String
     * @return boolean true if person existed in attendee list
     */
    public boolean removeSpeakerFromEvent(String speakerID, Event event) {
        ArrayList<String> speakerList = event.getSpeakers();
        if (speakerList.contains(speakerID)) {
            //Todo: implement removeSpeaker for Event (do not directly change list from getSpeakers())
            return speakerList.remove(speakerID);
        }
        return false;
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
     * Return a list all Speakers from event with eventID. If event not found, return empty array list.
     *
     * @param eventID String object
     * @return arraylist of attendees' ID from given event, return empty arraylist if event not found.
     */
    public ArrayList<String> getSpeakersFromEvent(String eventID) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            return event.getSpeakers();
        }
        return new ArrayList<>();
    }

    /**
     * Return ArrayList contains all events that attendee has signed up.
     *
     * @param userID String object,
     * @return ArrayList<String> contains all events that attendee has
     */
    public ArrayList<String> getEventsFromAttendee(String userID) {
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
    public ArrayList<String> getEventsFromSpeaker(String userID) {
        ArrayList<String> eventList = new ArrayList<>();
        for (Event event : events) {
            if (event.getSpeakers().contains(userID)) {
                eventList.add(event.getEventID());
            }
        }
        return eventList;
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


    public String getEventRestrictionWithID(String eventID) {
        Event event = getEventFromID(eventID);
        return event.getRestriction();
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

    public boolean checkValidTime(String time){
        return checkValidTimeFormat(time) && checkValidFutureTime(time);
    }

    private boolean checkValidTimeFormat(String time) {
        if (time.length() == 13 && time.charAt(4) == '/' && time.charAt(7) == '/' && time.charAt(10) == '/') {
            String[] timeList = time.split("/");
            try {
                int month = Integer.parseInt(timeList[1]);
                int date = Integer.parseInt(timeList[2]);
                int hour = Integer.parseInt(timeList[3]);
                if (hour < 9 || hour > 16 || month > 12 || month < 1 || date < 1 || date > 31) {
                    return false;
                }
                switch (month) {
                    case 2:
                        return date <= 28;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        return date < 30;
                }
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean checkValidFutureTime(String time) {
        String eventTime = time.replace("/", "");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/HH");
        Date date = new Date();
        String currentTime = formatter.format(date).replace("/", "");
        return Long.parseLong(eventTime) > Long.parseLong(currentTime);
    }

    //Todo: title is larger than 3 characters and not the same as other events

    /**
     * checkValidTitle
     * @param title String title
     * @return boolean
     */
    public boolean checkValidTitle(String title){
        if (title.length() < 3) {
            return false;
        }
        for (Event event : events) {
            if (event.getTitle().equals(title)) {
                return false;
            }
        }
        return true;
    }


    //Todo: duration is integer and larger than 0
    public boolean checkValidDuration(String duration){
        return true;
    }

    /**
     * Generate a formatted string representation of the start time String.
     * @param startTime startTime String
     * @return a formatted string representation
     */
    public String generateFormattedStartTime(String startTime) {
        int HourTime = Integer.parseInt(startTime.substring(11, 13));
        //Todo: change i.e. 17 to 5
        String Ending = String.format("%s", (HourTime >= 12) ? "PM" : "AM");
        return String.format("%s/%s/%s/%s%s", startTime.substring(0, 4), startTime.substring(5, 7),
                startTime.substring(8, 10), startTime.substring(11, 13), Ending);
    }

    /**
     * Generate the formatted event's information for writing into database.
     * @param eventID the id of an event.
     * @return a string of formatted event's information.
     */
    public String generateFormattedEventInfo(String eventID){
        for (Event event : events){
            if (event.getEventID().equals(eventID)){
                return event.getType() + " " + event.getTitle().replace(" ", "_")
                        + " " + eventID + " " + event.getRoomID() + " {" + event.getSpeakers() + "} "
                        + event.getStartTime() + " " + event.getDuration() + " " + event.getRestriction();
            }
        }
        return "NULL";
    }

    /**
     * Generate the event info for laoding into event activity
     * @return
     */
    public ArrayList<ArrayList<String>> generateAllInfo(ArrayList<String> eventIDs){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(String eventID : eventIDs){
            ArrayList<String> info = new ArrayList<>();

            Event event = getEventFromID(eventID);
            info.add(event.getTitle());
            info.add(event.getRoomID());
            info.add(event.getStartTime());
            info.add(event.getDuration());
            info.add(event.getType());
            info.add(event.getRestriction());
            info.add(""+event.getSpeakers());
            result.add(info);
        }
        return result;
    }

}
