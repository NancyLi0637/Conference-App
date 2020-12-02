package com.example.a207_demo.use_cases;

import com.example.a207_demo.entities.*;

import java.io.Serializable;
import java.util.*;

/**
 * The entities.Conversation Manager class, is a use case class to manage conversations.
 */
public class ConversationManager implements Serializable{

    private HashMap<HashSet<String>, Conversation> conversations;
    private Conversation currentConversation;

    public ConversationManager(){
        conversations = new HashMap<>();
    }

    /**
     * The method to create a new conversation between different users.
     * Precondition: these doesn't already exist a conversation with the same two users
     *
     * @param userId1 the user id of the first user
     * @param userId2 the user id of the second user
     */
    public void createConversation(String userId1, String userId2){
        Conversation newConversation = new Conversation(userId1, userId2);
        HashSet<String> newConversationId = newConversation.getUserIds();
        conversations.put(newConversationId, newConversation);
    };

    /**
     * Check if the Conversation between two users has been created.
     *
     * @param talkersList the Hashset of two users.
     * @return true iff the Conversation between two users from talkersList has been created.
     */
    public boolean existConversation(HashSet<String> talkersList){
        return conversations.containsKey(talkersList);
    }

    /**
     * Set the current conversation to be the conversation between the two users of talkersList.
     *
     * @param talkersList the Hashset of two users.
     */
    public void currentConversationSetter(HashSet<String> talkersList){
        this.currentConversation = conversations.get(talkersList);
    }

    /**
     * Send a message to another user
     *
     * @param senderId the id of the user we want to send out message to.
     * @param text the content of message we want to send
     */
    public void sendMessage(String senderId, String text){
        currentConversation.addMessage(senderId, text);
//        conversations.replace(getUserIds(currentConversation), currentConversation);
    }

    /**
     * Get all the messages from the current conversation.
     *
     * @return the whole messages list of the currentConversation.
     */
    public ArrayList<String[]> getMessagesOfCurrentConversation(){
        return currentConversation.getMessages();
    }

    /**
     * Get the list of conversations of a certain user
     * @return Arraylist of array of conversation informations.
     */
    public ArrayList<String[]> getUserConversations(String userId){
        ArrayList<String[]> UserConversations = new ArrayList<String[]>();
        for (HashSet<String> key: conversations.keySet()){
            if (key.contains(userId)){
                UserConversations.add(conversations.get(key).getLastMessage());
            }
        }
        return UserConversations;
    }

//    public void loadConversation(String userId1, String userId2, ArrayList<String[]> messageHistory){
//        HashSet<String> users = new HashSet<>();
//        users.add(userId1);
//        users.add(userId2);
//        Iterator value = users.iterator();
//        Conversation addConversation = new Conversation((String)value.next(),(String)value.next());
//        addConversation.loadAllMessage(messageHistory);
//        conversations.put(users,addConversation);
//    }

    /**
     * The getter of all conversations and their associated users
     *
     * @return Hash map of the users (key) and the conversation between them(content).
     */
    public HashMap<HashSet<String>, Conversation> conversationsGetter(){
        return conversations;
    }


    /**
     * Given a conversation, to find which users this conversation belongs to.
     *
     * @param conversation a given conversation.
     * @return the hashset of strings which are 2 user ids.
     */
    public HashSet<String> getUserIds(Conversation conversation){
        return conversation.getUserIds();
    }

    /**
     * Put a conversation into our conversations Hashmap.
     *
     * @param key hashset with two elements, each of the element are an userid.
     * @param conversation the entity with store all messages between two users.
     */
    public void addConversation(HashSet<String> key,Conversation conversation){
        conversations.put(key, conversation);
    }
}
