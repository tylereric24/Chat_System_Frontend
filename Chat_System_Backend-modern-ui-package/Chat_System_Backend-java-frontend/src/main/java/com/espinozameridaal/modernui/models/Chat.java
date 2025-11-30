package com.espinozameridaal.modernui.models;

public class Chat {
    private final String id;
    private final String name;
    private final String lastMessagePreview;
    private final String avatar;

    public Chat(String id, String name, String lastMessagePreview, String avatar) {
        this.id = id;
        this.name = name;
        this.lastMessagePreview = lastMessagePreview;
        this.avatar = avatar;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getLastMessagePreview() { return lastMessagePreview; }
    public String getAvatar() { return avatar; }

    @Override
    public String toString() {
        return name;
    }
}
