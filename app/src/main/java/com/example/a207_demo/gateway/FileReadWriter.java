package com.example.a207_demo.gateway;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a207_demo.entities.VIPUser;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.eventSystem.EventManager;
import com.example.a207_demo.use_cases.OrganizerManager;
import com.example.a207_demo.roomSystem.RoomManager;
import com.example.a207_demo.use_cases.SpeakerManager;
import com.example.a207_demo.use_cases.UserManager;
import com.example.a207_demo.use_cases.VIPUserManager;

import java.io.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.List;

/**
 * Read from and write to text files
 */
public class FileReadWriter implements Serializable {

    private final AppCompatActivity context;

    public FileReadWriter(AppCompatActivity context) {
        this.context = context;
    }

    /**
     * Reset the managers so that the list of objects are empty
     */
    public void reset(EventManager eventManager, UserManager userManager, AttendeeManager attendeeManager,
                      VIPUserManager vipUserManager, OrganizerManager organizerManager,
                      SpeakerManager speakerManager, RoomManager roomManager) {
        userManager.reset();
        eventManager.reset();
        attendeeManager.reset();
        vipUserManager.reset();
        organizerManager.reset();
        speakerManager.reset();
        roomManager.reset();
    }

    /**
     * Method reads User.txt file and loads in any Users stored in said file. Each line in the file represent one user
     * and the type of user is identified with a title of "SPEAKER", "ATTENDEE", or "ORGANIZER".
     */
    public void UserReader(AttendeeManager attendeeManager, VIPUserManager vipUserManager,
                           OrganizerManager organizerManager, SpeakerManager speakerManager) {
        ArrayList<String> lines = new ArrayList();
        try {
            FileInputStream in = context.openFileInput("Users");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            printMessage(context, "Users File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String[]> LineList = new ArrayList();
        for (String line : lines) {
            ArrayList<String> announcements = new ArrayList<>();
            int separate = -1;
            //find inbox of announcements
            if (line.contains("&")) {
                int start = line.indexOf("&");
                int end = line.lastIndexOf("&");
                separate = start;
                String announcement = line.substring(start + 1, end);
                //same method as if processAnnouncements
                announcements = processAttendees(announcement);
                line = line.substring(0, separate-1);
            }

            String[] wordList = line.split(" ");
            String type = wordList[0];
            String username = wordList[1] + " " + wordList[2];
            String email = wordList[3];
            String password = wordList[4];
            String userId = wordList[5];

            if(type.equals("ATTENDEE")) {
                attendeeManager.loadAttendee(username, email, password, userId, announcements);
            }else if(type.equals("VIPUser")) {
                vipUserManager.loadVIPUser(username, email, password, userId, announcements);
            }else if (type.equals("ORGANIZER")) {
                organizerManager.loadOrganizer(username, email, password, userId);
            }else{
                speakerManager.loadSpeaker(username, email, password, userId, announcements);
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
    public void UserWriter(UserManager userManager) {
        List<String> userIDs = userManager.getUserIDs();
        try {
            FileOutputStream out = context.openFileOutput("Users", Context.MODE_PRIVATE);
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
     * Reads Events.txt and loads saved events.
     */
    //Todo: accessing Event?
    public void EventReader(EventManager eventManager, RoomManager roomManager) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileInputStream in = context.openFileInput("Events.txt");
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
            ArrayList<String> speakerId = new ArrayList<>();
            //find speakerId list
            int separate = -1;
            if (line.contains("{")) {
                int start = line.indexOf("{");
                int end = line.indexOf("}");
                separate = start;
                String speakers = line.substring(start + 1, end);
                speakerId = processSpeakers(speakers);
            }
            ArrayList<String> attendeeId = new ArrayList<>();
            if (line.contains(";")){
                int start = line.indexOf(";");
                int end = line.lastIndexOf(";");
                String attendees = line.substring(start+1, end);
                attendeeId = processAttendees(attendees);
            }
            line = line.substring(0, separate-1);

            String[] wordList = line.split(" ");
            String type = wordList[0];
            String title = wordList[1].replace("_", " ");
            String eventID = wordList[2];
            String roomID = wordList[3];
            String startTime = wordList[4];
            String duration = wordList[5];
            String restriction = wordList[6];
            int capacity = Integer.parseInt(wordList[7]);

            eventManager.loadEvent(type, title, eventID, roomID, startTime, duration,
                    restriction, capacity, speakerId, attendeeId);
            roomManager.addEventToRoom(eventID, roomID);
        }
    }

    //necessary to separate in case attendee is empty while speaker has values
    private ArrayList<String> processSpeakers(String speakerId) {

        if (speakerId.equals("[]") || speakerId.equals("null")) {
            return new ArrayList<>();
        }

        return processUsers(speakerId);

    }

    private ArrayList<String> processAttendees(String attendeeId) {

        if (attendeeId.equals("[]") || attendeeId.equals("null") || attendeeId.equals("[null]")) {
            return new ArrayList<>();
        }

        return processUsers(attendeeId);

    }

    private ArrayList<String> processUsers(String userId){
        ArrayList<String> arrayList = new ArrayList<>();
        String content = userId.substring(1, userId.length()-1);

        if (content.contains(", ")) {
            String[] wordList = content.split(", ");
            for (String word : wordList) {
                arrayList.add(word);
            }
        }else {
            arrayList.add(content);
        }

        return arrayList;
    }


    /**
     * Saves created events to Events.txt.
     */
    public void EventWriter(EventManager eventManager) {
        List<String> eventIds = eventManager.getAllEventID();
        try {
            FileOutputStream out = context.openFileOutput("Events.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String eventId : eventIds) {
                String line = eventManager.generateFormattedEventInfo(eventId);
                line += " ;" + eventManager.getAttendeesFromEvent(eventId) + ";";
//                for (String attendee : eventManager.getAttendeesFromEvent(eventId)) {
//                    line += " ;" + attendee + ";";
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
     * Reads rooms.txt file and loads saved rooms.
     */
    public void RoomReader(RoomManager roomManager) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileInputStream in = context.openFileInput("Rooms");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            printMessage(context, "Rooms File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            String[] wordList = line.split(" ");
            String roomNum = wordList[0].substring(wordList[0].indexOf("m")+1);
            String roomId = wordList[1];
            int capacity = Integer.parseInt(wordList[2]);

            roomManager.loadRoom(roomNum, roomId, capacity);
        }
    }

    /**
     * Saves created room/rooms to rooms.txt file.
     */
    public void RoomWriter(RoomManager roomManager) {
        ArrayList<String> IDList = roomManager.getAllRoomID();
        try {
            FileOutputStream out = context.openFileOutput("Rooms", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String id : IDList) {
                String line = roomManager.generateFormattedRoomInfo(id);
                line += "\n";
                writer.write(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Rooms File Not Found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * print Message as a toast
     *
     * @param context AppCompatActivity
     * @param msg     String
     */
    private void printMessage(AppCompatActivity context, String msg) {
        Toast.makeText(context,
                msg,
                Toast.LENGTH_LONG).show();
    }

}

//    public void connectReaders() {
//        UserReader();
//        RoomReader();
//        //EventReader();
//    }
//
//    public void connectWriters() {
//        UserWriter();
//        RoomWriter();
//        //EventWriter();
//    }
//}
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
