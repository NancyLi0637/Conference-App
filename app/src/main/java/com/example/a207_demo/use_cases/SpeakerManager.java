package com.example.a207_demo.use_cases;

import com.example.a207_demo.entities.User;
import com.example.a207_demo.speakerSystem.Speaker;
import com.example.a207_demo.eventSystem.Event;
import com.example.a207_demo.eventSystem.EventManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.SpeakerManager class, this is the use case class to manage the entities.Speaker for this conference.
 */
public class SpeakerManager extends UserManager implements Serializable {

    private List<Speaker> speakers;

    /**
     * Creates an use_cases.SpeakerManager with lists of Events for a speaker that is empty
     */
    public SpeakerManager() {
        speakers = new ArrayList<>();
    }

    /**
     * Reset the attendees: no user exists
     */
    public void reset() {
        speakers = new ArrayList<>();
    }

    /**
     * Getter method for all speakers
     *
     * @return list of speaker
     */
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public ArrayList<String> getSpeakerIDs(){
        ArrayList<String> speakerIDs = new ArrayList<>();
        for(Speaker speaker : speakers){
            speakerIDs.add(speaker.getUserID());
        }
        return speakerIDs;
    }

    public boolean hasSpeakers(){return this.speakers.size() > 0;}

    /**
     * Creates a Speaker and adds it to the lists
     *
     * @param userName userName of this speaker
     * @param email email of this speaker
     * @param password password of this speaker
     */
    public void createSpeaker(String userName, String email, String password) {
        Speaker speaker = new Speaker(userName, email, password);
        speakers.add(speaker);
        UserManager.users.add(speaker);
    }

    /**
     * Load a Speaker from file and adds it to the lists
     *
     * @param userName userName of this speaker
     * @param email email of this speaker
     * @param password password of this speaker
     * @param ID user ID of this speaker
     * @param announcements inbox of annoucements of this speaker
     */
    public void loadSpeaker(String userName, String email, String password, String ID,
                            ArrayList<String> announcements) {
        Speaker speaker = new Speaker(userName, email, password, ID, announcements);
        speakers.add(speaker);
        UserManager.users.add(speaker);
    }

    /**
     * Get an ArrayList<String> of all Available Speakers given a starting time
     * @param time String, start time
     * @param eventManager an EventManager
     * @return ArrayList<String> of all Available Speaker
     */
    public ArrayList<String> getAllAvailableSpeaker(String time, String duration, EventManager eventManager) {
        ArrayList<String> availableSpeaker = new ArrayList<>();
        List<Event> events = eventManager.getAllEvent();

        for (Speaker speaker : speakers) {
            availableSpeaker.add(speaker.getUserID());
        }

        for (String speakerID : availableSpeaker){
            for (Event event : events) {
                for (String currentSpeaker: event.getSpeakers()) {
                    if (currentSpeaker.equals(speakerID) && event.timeConflict(time, duration)) {
                            availableSpeaker.remove(speakerID);
                        }
                    }
                }
        }

        return getUserNamesFromID(availableSpeaker);
    }

    public ArrayList<ArrayList<String>> generateAccountInfo(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(Speaker speaker : speakers){
            ArrayList<String> info = new ArrayList<>();
            info.add(speaker.getUserName());
            info.add(speaker.getType());
            info.add(speaker.getEmail());
            info.add(speaker.getUserID());
            result.add(info);
        }
        return result;
    }

}
