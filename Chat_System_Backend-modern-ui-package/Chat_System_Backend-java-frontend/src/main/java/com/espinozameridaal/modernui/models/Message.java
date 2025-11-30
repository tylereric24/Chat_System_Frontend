package com.espinozameridaal.modernui.models;

import java.time.LocalDateTime;

public class Message {
    private final String id;
    private final String sender;
    private final String content;
    private final LocalDateTime timestamp;
    private final boolean fromCurrentUser;

    public Message(String id, String sender, String content, LocalDateTime timestamp, boolean fromCurrentUser) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
        this.fromCurrentUser = fromCurrentUser;
    }

    public String getId() { return id; }
    public String getSender() { return sender; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public boolean isFromCurrentUser() { return fromCurrentUser; }

    @Override
    public String toString() {
        return sender + ": " + content;
    }
}
