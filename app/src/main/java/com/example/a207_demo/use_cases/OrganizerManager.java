package com.example.a207_demo.use_cases;

import com.example.a207_demo.entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.OrganizerManager class, this is the use case class to manage the Organizers for this conference.
 */

public class OrganizerManager extends UserManager implements Serializable {

    private List<Organizer> organizers;

    /**
     * Creates an use_cases.OrganizerManager
     */
    public OrganizerManager() {
        organizers = new ArrayList<>();
    }

    /**
     * Reset the organizers: no user exists
     */
    public void reset() {
        organizers = new ArrayList<>();
    }

    public boolean checkOrganizer(String userID){
        return getOrganizerIDs().contains(userID);
    }

    public ArrayList<String> getOrganizerIDs(){
        ArrayList<String> organizerIDs = new ArrayList<>();
        for(Organizer organizer : organizers){
           organizerIDs.add(organizer.getUserID());
        }
        return organizerIDs;
    }



    /**
     * Create an organizer account and store it in the organizers and users lists
     *
     * @param userName userName of this organizer
     * @param email    email of this organizer
     * @param password password of this organizer
     */
    public void createOrganizer(String userName, String email, String password) {
        Organizer organizer = new Organizer(userName, email, password);
        this.organizers.add(organizer);
        UserManager.users.add(organizer);
    }

    /**
     * Create an organizer account (with parameter: ID) and store it in the organizers and users lists
     *
     * @param userName userName of this organizer
     * @param email    email of this organizer
     * @param password password of this organizer
     * @param ID       user ID of this organizer
     */
    public void loadOrganizer(String userName, String email, String password, String ID,
                              ArrayList<String> friendsID, ArrayList<String> announcements) {
        Organizer organizer = new Organizer(userName, email, password, ID, friendsID, announcements);
        this.organizers.add(organizer);
        UserManager.users.add(organizer);
    }


    @Override
    public ArrayList<ArrayList<String>> generateAccountInfo(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(Organizer organizer : organizers){
            ArrayList<String> info = new ArrayList<>();
            info.add(organizer.getUserName());
            info.add(organizer.getType());
            info.add(organizer.getEmail());
            info.add(organizer.getUserID());
            result.add(info);
        }
        return result;
    }


}
