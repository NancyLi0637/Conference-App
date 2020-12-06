package com.example.a207_demo.messageSystem;

/**
 * Msg
 */
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;
    private int type;

    /**
     * Msg constructor
     *
     * @param content String
     * @param type    int
     */
    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    /**
     * getContent
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * getType
     *
     * @return int
     */
    public int getType() {
        return type;
    }
}
