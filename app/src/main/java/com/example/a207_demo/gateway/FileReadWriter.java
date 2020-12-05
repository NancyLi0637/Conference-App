package com.example.a207_demo.gateway;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a207_demo.entities.Organizer;
import com.example.a207_demo.eventSystem.Event;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.eventSystem.EventManager;
import com.example.a207_demo.use_cases.OrganizerManager;
import com.example.a207_demo.use_cases.SpeakerManager;
import com.example.a207_demo.use_cases.UserManager;

import java.io.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.List;

/**
 * Read from and write to text files
 */
public class FileReadWriter {

    private final EventManager eventManager;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private final UserManager userManager;
    private AppCompatActivity context;

    /**
     * Constructor for the FileReadWriter class: Reads and saves data to and from txt files
     * This constructor is used in CleanArchActivity.java
     *
     * @param context AppCompatActivity
     * @param eventManager EventManager
     * @param userManager UserManager
     * @param attendeeManager AttendeeManager
     * @param organizerManager OrganizerManager
     * @param speakerManager SpeakerManager
     */
    public FileReadWriter(AppCompatActivity context, EventManager eventManager, UserManager userManager,
                          AttendeeManager attendeeManager, OrganizerManager organizerManager,
                          SpeakerManager speakerManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.context = context;
    }

    /**
     * Reset the managers so that the list of objects are empty
     */
    public void reset() {
        this.userManager.reset();
        this.eventManager.reset();
        this.attendeeManager.reset();
        this.organizerManager.reset();
        this.speakerManager.reset();
    }

    /**
     * Method reads User.txt file and loads in any Users stored in said file. Each line in the file represent one user
     * and the type of user is identified with a title of "SPEAKER", "ATTENDEE", or "ORGANIZER".
     */
    public void UserReader() {
        ArrayList<String> lines = new ArrayList();
        try {
            FileInputStream in = context.openFileInput("Users");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            printMessage(context, "Users.txt File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String[]> LineList = new ArrayList();
        for (String line : lines) {
            String[] wordList = line.split(" ");
            String type = wordList[0];
            String username = wordList[1];
            String email = wordList[2];
            String password = wordList[3];
            String userId = wordList[4];


            if (type.equals("SPEAKER")) {
                speakerManager.loadSpeaker(username, email, password, userId);
            } else if (type.equals("ATTENDEE")) {
                attendeeManager.loadAttendee(username, email, password, userId);
            } else if (type.equals("ORGANIZER")) {
                organizerManager.loadOrganizer(username, email, password, userId);
            }
            LineList.add(wordList);
        }
//        for (ArrayList<String> wordList : LineList){
//            if (wordList.size() > 5){
//                for (int index = 5; index < wordList.size(); index++){
//                    userManager.addFriend(wordList.get(4), wordList.get(index));
//                }
//            }
//        }
    }

    /**
     * Saves a user's all relevant data to Users.txt. User info can be extracted using the UserReader() method if
     * needed.
     */
    public void UserWriter() {
        List<String> userIDs = userManager.UsersIdsGetter();
        try {
            FileOutputStream out = context.openFileOutput("Users", Context.MODE_APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String userID : userIDs) {
                String line = userManager.generateFormattedUserInfo(userID);
//                for (String friendID : userManager.friendListGetter(userID)){
//                    line += " " + friendID;
//                }
                line += "\n";
                writer.write(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
//            System.out.println("Users.txt File Not Found.");
            printMessage(context, "Users.txt File Not Found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * print Message as a toast
     * @param context AppCompatActivity
     * @param msg String
     */
    private void printMessage(AppCompatActivity context, String msg) {
        Toast.makeText(context,
                msg,
                Toast.LENGTH_LONG).show();
    }

    /**
     * Reads rooms.txt file and loads saved rooms.
     */
//    public void RoomReader(){
//        ArrayList<String> lines = new ArrayList<>();
//        try {
//            File UserFile = new File("./phase1/rooms.txt");
//            Scanner myReader = new Scanner(UserFile);
//            while (myReader.hasNextLine()) {
//                while (myReader.hasNextLine()) {
//                    lines.add(myReader.nextLine());
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("rooms.txt File Not Found");
//        }
//        for (String line : lines) {
//            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
//            eventsController.getRoomManager().loadRoom(wordList.get(0), wordList.get(1));
//        }
//    }

    /**
     * Saves created room/rooms to rooms.txt file.
     */
//    public void RoomWriter(){
//        ArrayList<String> NumList = eventsController.getRoomManager().getAllRoomNum();
//        ArrayList<String> IDList = eventsController.getRoomManager().getAllRoomID();
//        try {
//            PrintWriter pw = new PrintWriter("./phase1/rooms.txt");
//            for (int index = 0; index < NumList.size(); index++){
//                String line = NumList.get(index) + " " + IDList.get(index);
//                line += "\n";
//                pw.write(line);
//            }
//            pw.close();
//        } catch (FileNotFoundException e){
//            System.out.println("rooms.txt File Not Found.");
//        }
//    }

    /**
     * Reads Events.txt and loads saved events.
     */
    //Todo: accessing Event?
    public void EventReader() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileInputStream in = context.openFileInput("Events");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            printMessage(context, "Events File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            String[] wordList = line.split(" ");
            String type = wordList[0];
            String title = wordList[1].replace("_", " ");
            String eventId = wordList[2];
            String roomId = wordList[3];
            //Todo: use String[] instead?
            ArrayList<String> speakerId = new ArrayList<>(Arrays.asList(wordList[4]));
            String startTime = wordList[5];
            String duration = wordList[6];
            String restriction = wordList[7];

//            ArrayList<String> Attendees = new ArrayList<>();
//            if (wordList.size() > 5){
//                for (int index = 5; index < wordList.size(); index++){
//                    Attendees.add(wordList.get(index));
//                }
//            }
//            eventsController.getRoomManager().addEventToRoom(wordList.get(4), wordList.get(1));
            eventManager.loadEvent(type, title, eventId, roomId, speakerId, startTime, duration, restriction);
        }
    }


    /**
     * Saves created events to Events.txt.
     */
    public void EventWriter() {
        List<String> eventIds = eventManager.getAllIDAndName().get(0);
        System.out.println(eventManager.getAllEvent().get(0).getTitle());
        try {
            FileOutputStream out = context.openFileOutput("Events", Context.MODE_APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String eventId : eventIds) {
                String line = eventManager.generateFormattedEventInfo(eventId);
//                for (String attendee : eventsController.getEventManager().getAttendeesFromEvent(ID)) {
//                    line += " " + attendee;
//                }
                line += "\n";
                writer.write(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Events.txt File Not Found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getters that return EventsController, OrganizerManager, AttendeeManager and UserManager.
     */
//    public EventsController GetEventsController(){
//        return eventsController;
//    }
//    public OrganizerManager GetOrganizerManager() {
//        return organizerManager;
//    }
//
//    public AttendeeManager GetAttendeeManager() {
//        return attendeeManager;
//    }
//
//    public UserManager GetUserManager() {
//        return userManager;
//    }

    public void connectReaders(AppCompatActivity context) {
        UserReader();
        //RoomReader();
        //EventReader();
    }

    public void connectWriters(AppCompatActivity context) {
        UserWriter();
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
