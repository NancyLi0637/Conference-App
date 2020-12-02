package com.example.a207_demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Conversation implements Serializable {
    private HashSet<String> userIds = new HashSet<String>(); //Store uerIds of the two users
    private ArrayList<String[]> messages = new ArrayList<String[]>();

    /**
     * The constructor of the conversation class
     * @param userId1 first user id
     * @param userId2 second user id.
     */
    public Conversation(String userId1, String userId2){
        userIds.add(userId1);
        userIds.add(userId2);
    }

    /**
     * Add the sender's message to the list of messages.
     *
     * @param userId the user id of the sender of the message.
     * @param text the String of text the sender sends.
     */
    public void addMessage(String userId, String text){
        String[] message = {userId, text};
        messages.add(message);
    }

    /**
     * Get the messages of this conversation.
     *
     * @return the whole messages list of this conversation.
     */
    public ArrayList<String[]> getMessages(){
        return messages;
    }


    /**   没写完
     * Get the last messages of this conversation.
     *
     * @return the whole messages list of this conversation.
     */
    public String[] getLastMessage(){
        if (messages.size() > 0) {
            int lastIndex = messages.size() - 1;
            Object [] userInfo = userIds.toArray();
            String [] messageInfo = messages.get(lastIndex);
            return new String[]{(String)userInfo[0], (String)userInfo[1], messageInfo[0], messageInfo[1]};
        }else{
            Object [] userInfo = userIds.toArray();
            return new String[]{(String)userInfo[0], (String)userInfo[1], "", ""};
        }
    }

    /**
     * Get the userIds of the current conversation.
     *
     * @return the cloned Hashset of userIds.
     */
    public HashSet<String> getUserIds(){
//        HashSet<String> cloneSet = (HashSet<String>)userIds.clone();
        return userIds;
    }
}
