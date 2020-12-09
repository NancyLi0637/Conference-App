package com.example.a207_demo.gateway;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a207_demo.entities.Conversation;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.eventSystem.EventManager;
import com.example.a207_demo.use_cases.ConversationManager;
import com.example.a207_demo.use_cases.OrganizerManager;
import com.example.a207_demo.roomSystem.RoomManager;
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
public class FileReadWriter implements Serializable {

    private final AppCompatActivity context;

    public FileReadWriter(AppCompatActivity context) {
        this.context = context;
    }

    /**
     * Reset the managers so that the list of objects are empty
     */
    public void reset(EventManager eventManager, UserManager userManager,
                      AttendeeManager attendeeManager, OrganizerManager organizerManager,
                      SpeakerManager speakerManager, RoomManager roomManager,
                      ConversationManager conversationManager) {
        userManager.reset();
        eventManager.reset();
        attendeeManager.reset();
        organizerManager.reset();
        speakerManager.reset();
        roomManager.reset();
        conversationManager.reset();
    }

    /**
     * Method reads User.txt file and loads in any Users stored in said file. Each line in the file represent one user
     * and the type of user is identified with a title of "SPEAKER", "ATTENDEE", or "ORGANIZER".
     */
    public void UserReader(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                           SpeakerManager speakerManager) {
        ArrayList<String> lines = new ArrayList();
        try {
            FileInputStream in = context.openFileInput("Users.txt");
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
            int separate = -1;
            ArrayList<String> friendsId = new ArrayList<>();
            if (line.contains(";")){
                int start = line.indexOf(";");
                int end = line.lastIndexOf(";");
                separate = start;
                String friends = line.substring(start+1, end);
                friendsId = processAttendees(friends);
            }

            ArrayList<String> announcements = new ArrayList<>();
            //find inbox of announcements
            if (line.contains("&")) {
                int start = line.indexOf("&");
                int end = line.lastIndexOf("&");
                separate = start;
                String announcement = line.substring(start + 1, end);
                //same method as if processAnnouncements
                announcements = processAttendees(announcement);
            }
            line = line.substring(0, separate-1);

            String[] wordList = line.split(" ");
            String type = wordList[0];
            String username = wordList[1] + " " + wordList[2];
            String email = wordList[3];
            String password = wordList[4];
            String userId = wordList[5];

            if(type.equals("ATTENDEE")) {
                attendeeManager.loadAttendee(username, email, password, userId, friendsId, announcements);
            }else if(type.equals("VIPUser")) {
                attendeeManager.loadVIPUser(username, email, password, userId, friendsId, announcements);
            }else if (type.equals("ORGANIZER")) {
                organizerManager.loadOrganizer(username, email, password, userId, friendsId, announcements);
            }else{
                speakerManager.loadSpeaker(username, email, password, userId, friendsId, announcements);
            }
            LineList.add(wordList);
        }
    }

    /**
     * Saves a user's all relevant data to Users.txt. User info can be extracted using the UserReader() method if
     * needed.
     */
    public void UserWriter(UserManager userManager) {
        List<String> userIDs = userManager.getUserIDs();
        try {
            FileOutputStream out = context.openFileOutput("Users.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String userID : userIDs) {
                String line = userManager.generateFormattedUserInfo(userID);
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
            int separate = -1;
            //find speakerId list
            ArrayList<String> speakerId = new ArrayList<>();
            if (line.contains("{")) {
                int start = line.indexOf("{");
                int end = line.indexOf("}");
                separate = start;
                String speakers = line.substring(start + 1, end);
                speakerId = processSpeakers(speakers);
            }
            //find attendeeID list
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
            FileInputStream in = context.openFileInput("Rooms.txt");
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
            FileOutputStream out = context.openFileOutput("Rooms.txt", Context.MODE_PRIVATE);
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

    public void ConversationReader(ConversationManager conversationManager){
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileInputStream in = context.openFileInput("Conversations.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            printMessage(context, "Conversations.txt File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            ArrayList<String> userIds = new ArrayList<>();
            //find userIDs
            int start = line.indexOf("[");
            int end = line.indexOf("]");
            String[] IDs = line.substring(start+1, end).split(", ");
            userIds = new ArrayList<>(Arrays.asList(IDs));


            //find messages
            int marker = line.indexOf("MESSAGES") + 9;
            int msgEnd = line.indexOf("&", marker);
            ArrayList<ArrayList<String>> messages = processMessages(line.substring(marker, msgEnd));

            conversationManager.loadConversation(userIds, messages);

        }
    }

    private ArrayList<ArrayList<String>> processMessages(String conversation){
        ArrayList<ArrayList<String>> messages = new ArrayList<>();
        if(conversation.equals("[]") || conversation.equals("[null]") || conversation.equals("null")){
            return messages;
        }
        String content = conversation.substring(1, conversation.length()-1);
        while(content.indexOf("]") < content.length()-1){
            String message = content.substring(content.indexOf("[")+1, content.indexOf("]"));
            messages.add(individualMsg(message));
            content = content.substring(content.indexOf("]")+3);
        }
        messages.add(individualMsg(content.substring(1, content.length()-1)));

        return messages;
    }

    private ArrayList<String> individualMsg(String content){
        //String message = content.substring(content.indexOf("[")+1, content.indexOf("]"));
        String id = content.substring(0, content.indexOf(","));
        String msg = content.substring(content.indexOf(",")+2);
        ArrayList<String> eachMsg = new ArrayList<>();
        eachMsg.add(id);
        eachMsg.add(msg);
        return eachMsg;
    }

    public void ConversationWriter(ConversationManager conversationManager){
        ArrayList<ArrayList<String>> conversationIds = conversationManager.getAllUserIds();
        try{
            FileOutputStream out = context.openFileOutput("Conversations.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (ArrayList<String> conversationId : conversationIds){
                String line = conversationManager.generateFormattedConversationInfo(conversationId);
                line += "\n";
                writer.write(line);
            }
            writer.close();
        }catch (FileNotFoundException e) {
            System.out.println("Conversations.txt File Not Found.");
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
