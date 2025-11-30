package com.espinozameridaal.modernui.services;

import com.espinozameridaal.modernui.models.Chat;
import com.espinozameridaal.modernui.models.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatService {

    public List<Chat> getRecentChats() {
        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat("1", "Alice Johnson", "Let\'s review the report tomorrow.", "AJ"));
        chats.add(new Chat("2", "Dev Team", "Pushed the latest build to staging ✅", "DT"));
        chats.add(new Chat("3", "Security Channel", "New login from unknown device.", "SC"));
        chats.add(new Chat("4", "Family Chat", "Who\'s bringing dessert?", "👨‍👩‍👦"));
        return chats;
    }

    public List<Message> getMessagesForChat(String chatId) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("m1", "You", "Hey, just testing the new UI.", LocalDateTime.now().minusMinutes(5), true));
        messages.add(new Message("m2", "Alice Johnson", "Looks super clean so far!", LocalDateTime.now().minusMinutes(4), false));
        messages.add(new Message("m3", "You", "Nice, I\'ll hook it up to the backend later.", LocalDateTime.now().minusMinutes(3), true));
        return messages;
    }

    public Message sendMessage(String chatId, String content) {
        return new Message("m" + System.currentTimeMillis(), "You", content, LocalDateTime.now(), true);
    }
}
