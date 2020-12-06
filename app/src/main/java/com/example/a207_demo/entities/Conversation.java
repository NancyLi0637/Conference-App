package com.example.a207_demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Conversation implements Serializable {
    private HashSet<String> userIds = new HashSet<String>(); //Store uerIds of the two users
    private ArrayList<String[]> messages = new ArrayList<String[]>(); //Store the messages of the conversation.
    private ArrayList<String[]> unreadMessages = new ArrayList<>(); // Store the unread messages of the conversation
    private ArrayList<String[]> archiveMessages = new ArrayList<>(); // Store the archive messages of the conversation.

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
     * Add a select message from messages list to the unread message list
     * @param index the selected message
     */
    public void addUnreadMessage(int index){
        String[] archiveMessage = messages.get(index);
        unreadMessages.add(archiveMessage);
    };

    /**
     * Getter of the unread message list
     * @return the unread message list
     */
    public ArrayList<String[]> unreadMessageGetter(){
        return unreadMessages;
    }

    /**
     *
     * Clear all the unread messages from the list
     */
    public void emptyUnreadMessage(){
        unreadMessages.removeAll(unreadMessages);
    }

    /**
     * Get the messages of this conversation.
     *
     * @return the whole messages list of this conversation.
     */
    public ArrayList<String[]> getMessages(){
        return messages;
    }

    /**
     * Delete a message of a given index
     * @param index the index of the message in the messages list the user want to delete.
     */
    public void deleteMessage(int index){
        messages.remove(index);
    }

    /**
     * Add a select message from messages list to the archive message list
     * @param index the selected message
     */
    public void addArchiveMessages(int index){
        String[] archiveMessage = messages.get(index);
        archiveMessages.add(archiveMessage);
    }

    /**
     * remove a select archive message
     * @param index the selected message
     */
    public void deleteArchiveMessage(int index){
        archiveMessages.remove(index);
    }

    /**
     * Getter of the archive message list
     * @return the archive message list
     */
    public ArrayList<String[]> archivedMessageGetter() {
        return archiveMessages;
    }

    /**
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
