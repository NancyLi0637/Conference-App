package com.example.a207_demo.gateway;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a207_demo.controller.EventsController;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.use_cases.OrganizerManager;
import com.example.a207_demo.use_cases.SpeakerManager;
import com.example.a207_demo.use_cases.UserManager;

import java.io.*;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReadWriter {
    private EventsController eventsController;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private final UserManager userManager;

    /**
     * Reads and saves data to and from txt files
     */
    public FileReadWriter() {
        userManager = new UserManager();
        eventsController = new EventsController();
        attendeeManager = new AttendeeManager();
        organizerManager = new OrganizerManager();
    }
    public void reset(){
        this.userManager.reset();
        this.eventsController = new EventsController();
        this.attendeeManager = new AttendeeManager();
        this.organizerManager = new OrganizerManager();
    }

    /**
     * Method reads User.txt file and loads in any Users stored in said file. Each line in the file represent one user
     * and the type of user is identified with a title of "SPEAKER", "ATTENDEE", or "ORGANIZER".
     */
    public void UserReader(AppCompatActivity context) {
        ArrayList<String> lines = new ArrayList();
        try {
            FileInputStream in = context.openFileInput("Users.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
//            System.out.println("Users.txt File Not Found");
            printMessage(context, "Users.txt File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpeakerManager speakermanager = eventsController.getSpeakerManager();
        ArrayList<ArrayList> LineList = new ArrayList();
        for (int i = 0; i < lines.size(); i++) {
            ArrayList<String> wordList = new ArrayList<String>();
            for (String word : lines.get(i).split(" ")) {
                wordList.add(word);

            }

            if (wordList.get(0).equals("SPEAKER")) {
                speakermanager.loadSpeaker(wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4));
            } else if (wordList.get(0).equals("ATTENDEE")) {
                attendeeManager.loadAttendee(wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4));
            } else if (wordList.get(0).equals("ORGANIZER")) {
                organizerManager.loadOrganizer(wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4));
            }
            LineList.add(wordList);
        }
        for (ArrayList<String> wordList : LineList){
            if (wordList.size() > 5){
                for (int index = 5; index < wordList.size(); index++){
                    userManager.addFriend(wordList.get(4), wordList.get(index));
                }
            }
        }
    }

    /**
     * Saves a user's all relevant data to Users.txt. User info can be extracted using the UserReader() method if
     * needed.
     */
    public void UserWriter(AppCompatActivity context){
        try {
            //PrintWriter pw = new PrintWriter("Users.txt");
            FileOutputStream out = context.openFileOutput("Users.txt", Context.MODE_APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String userID : userManager.UsersIdsGetter()){
                String line = userManager.getUserType(userID) + " " + userManager.getUserEmail(userID) + " " +
                        userManager.getUserName(userID) + " "+ userManager.getUserPassword(userID) + " " + userID;
                for (String friendID : userManager.friendListGetter(userID)){
                    line += " " + friendID;
                }
                line += "\n";
                writer.write(line);
            }
            writer.close();
        } catch (FileNotFoundException e){
//            System.out.println("Users.txt File Not Found.");
            printMessage(context, "Users.txt File Not Found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print error message as a toast
     * @param context AppCompatActivity
     * @param msg message string
     */
    public void printMessage(AppCompatActivity context, String msg) {
        Toast.makeText(context,
                msg,
                Toast.LENGTH_LONG).show();
    }

    /**
     * Reads rooms.txt file and loads saved rooms.
     */
    public void RoomReader(){
        ArrayList<String> lines = new ArrayList<>();
        try {
            File UserFile = new File("./phase1/rooms.txt");
            Scanner myReader = new Scanner(UserFile);
            while (myReader.hasNextLine()) {
                while (myReader.hasNextLine()) {
                    lines.add(myReader.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("rooms.txt File Not Found");
        }
        for (String line : lines) {
            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
            eventsController.getRoomManager().loadRoom(wordList.get(0), wordList.get(1));
        }
    }

    /**
     * Saves created room/rooms to rooms.txt file.
     */
    public void RoomWriter(){
        ArrayList<String> NumList = eventsController.getRoomManager().getAllRoomNum();
        ArrayList<String> IDList = eventsController.getRoomManager().getAllRoomID();
        try {
            PrintWriter pw = new PrintWriter("./phase1/rooms.txt");
            for (int index = 0; index < NumList.size(); index++){
                String line = NumList.get(index) + " " + IDList.get(index);
                line += "\n";
                pw.write(line);
            }
            pw.close();
        } catch (FileNotFoundException e){
            System.out.println("rooms.txt File Not Found.");
        }
    }

    /**
     * Reads Events.txt and loads saved events.
     */
    public void EventReader(){
        ArrayList<String> lines = new ArrayList<>();
        try {
            File UserFile = new File("./phase1/Events.txt");
            Scanner myReader = new Scanner(UserFile);
            while (myReader.hasNextLine()) {
                while (myReader.hasNextLine()) {
                    lines.add(myReader.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Events.txt File Not Found");
        }

        for (String line : lines) {
            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
            ArrayList<String> Attendees = new ArrayList<>();
            if (wordList.size() > 5){
                for (int index = 5; index < wordList.size(); index++){
                    Attendees.add(wordList.get(index));
                }
            }
            eventsController.getRoomManager().addEventToRoom(wordList.get(4), wordList.get(1));
//            eventsController.getEventManager().loadEvent(wordList.get(0).replace("_", " "),
//                    wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4), Attendees,
//                    GetEventsController().getRoomManager());
        }
    }

    /**
     * Saves created events to Events.txt.
     */
    public void EventWriter(){
        List<String> IDList = eventsController.getEventManager().getAllIDAndName().get(0);
        try {
            PrintWriter pw = new PrintWriter("./phase1/Events.txt");
            for (String ID : IDList) {
                String line = eventsController.getEventManager().generateFormattedEventInfo(ID);
                for (String attendee : eventsController.getEventManager().getAttendeesFromEvent(ID)) {
                    line += " " + attendee;
                }
                line += "\n";
                pw.write(line);
            }
            pw.close();
        } catch (FileNotFoundException e){
            System.out.println("Events.txt File Not Found.");
        }
    }

    /**
     * Getters that return EventsController, OrganizerManager, AttendeeManager and UserManager.
     */
    public EventsController GetEventsController(){
        return eventsController;
    }
    public OrganizerManager GetOrganizerManager(){
        return organizerManager;
    }
    public AttendeeManager GetAttendeeManager(){
        return attendeeManager;
    }
    public UserManager GetUserManager(){
        return userManager;
    }

    public void connectReaders(AppCompatActivity context) {
        UserReader(context);
        //RoomReader();
        //EventReader();
    }

    public void connectWriters(AppCompatActivity context) {
        UserWriter(context);
        //RoomWriter();
        //EventWriter();
    }
}
//    public EventManager readFromEventFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//
//            // deserialize the use_cases.EventManager
//            EventManager sm = (EventManager) input.readObject();
//            input.close();
//            return sm;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new use_cases.EventManager.", ex);
//            return new EventManager();
//        }
//    }
//
//    public SpeakerManager readFromSpeakerFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//
//            // deserialize the use_cases.SpeakerManager
//            SpeakerManager sm = (SpeakerManager) input.readObject();
//            input.close();
//            return sm;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new use_cases.SpeakerManager.", ex);
//            return new SpeakerManager();
//        }
//    }
//
//    public RoomManager readFromRoomFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//
//            // deserialize the use_cases.RoomManager
//            RoomManager sm = (RoomManager) input.readObject();
//            input.close();
//            return sm;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new use_cases.RoomManager.", ex);
//            return new RoomManager();
//        }
//    }
//
//    public ArrayList<User> readFromUserFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//
//            ArrayList<User> objList = new ArrayList<>();
//            boolean load = true;
//            while (load)
//            {
//                try (ObjectInput input = new ObjectInputStream(buffer))
//                {
//                    User sm = (User) input.readObject();
//                    if (sm != null) {
//                        objList.add(sm);
//                    } else {
//                        load = false;
//                    }
//                }
//                catch (Exception e)
//                {
//                }
//            }
//            input.close(); //can't close input TODO
//            return objList;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new entities.User.", ex);
//            return new ArrayList<>();
//        }
//    }
