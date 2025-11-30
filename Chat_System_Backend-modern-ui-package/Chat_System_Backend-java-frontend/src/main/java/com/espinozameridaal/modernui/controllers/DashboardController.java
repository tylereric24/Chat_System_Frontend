package com.espinozameridaal.modernui.controllers;

import com.espinozameridaal.modernui.models.Chat;
import com.espinozameridaal.modernui.models.Message;
import com.espinozameridaal.modernui.services.ChatService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;


public class DashboardController {

    @FXML
    private Label currentUserLabel;

    @FXML
    private TextField searchChatsField;

    @FXML
    private ListView<Chat> chatsListView;

    @FXML
    private ListView<Message> messagesListView;

    @FXML
    private TextField messageField;

    @FXML
    private Label activeChatNameLabel;

    @FXML
    private Label activeChatSubtitleLabel;

    private final ChatService chatService = new ChatService();
    private Chat activeChat;

    public void setCurrentUsername(String username) {
        if (currentUserLabel != null) {
            currentUserLabel.setText(username);
        }
    }

    @FXML
    private void initialize() {
        ObservableList<Chat> chats = FXCollections.observableArrayList(chatService.getRecentChats());
        chatsListView.setItems(chats);

        chatsListView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Chat item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getName() + "  •  " + item.getLastMessagePreview());
                }
            }
        });

        chatsListView.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                loadChat(sel);
            }
        });

        if (!chats.isEmpty()) {
            chatsListView.getSelectionModel().select(0);
        }
    }

    private void loadChat(Chat chat) {
    this.activeChat = chat;
    if (activeChatNameLabel != null) {
        activeChatNameLabel.setText(chat.getName());
    }
    if (activeChatSubtitleLabel != null) {
        activeChatSubtitleLabel.setText("Active now");
    }

    ObservableList<Message> messages = FXCollections.observableArrayList(
            chatService.getMessagesForChat(chat.getId())
    );
    messagesListView.setItems(messages);

    // CHAT BUBBLES
    messagesListView.setCellFactory(list -> new ListCell<>() {
        @Override
        protected void updateItem(Message msg, boolean empty) {
            super.updateItem(msg, empty);

            if (empty || msg == null) {
                setGraphic(null);
                return;
            }

            // Outer container so we can align left/right
            HBox container = new HBox();
            container.setPadding(new Insets(4, 12, 4, 12));
            container.setFillHeight(false);

            // Spacer to push self messages to the right
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            // The bubble itself
            Label bubble = new Label(msg.getContent());
            bubble.setWrapText(true);
            bubble.setMaxWidth(380);
            bubble.setPadding(new Insets(8, 12, 8, 12));

            if (msg.isFromCurrentUser()) {
                // Right-aligned (you)
                container.setAlignment(Pos.CENTER_RIGHT);
                bubble.setStyle(
                        "-fx-background-color: linear-gradient(to bottom right, #4C6FFF, #7B5CFF);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-font-size: 13px;"
                );
                container.getChildren().addAll(spacer, bubble);
            } else {
                // Left-aligned (other person)
                container.setAlignment(Pos.CENTER_LEFT);
                bubble.setStyle(
                        "-fx-background-color: #F4F4F5;" +
                        "-fx-text-fill: #111111;" +
                        "-fx-background-radius: 16;" +
                        "-fx-font-size: 13px;"
                );
                container.getChildren().addAll(bubble, spacer);
            }

            setGraphic(container);
        }
    });
}


    @FXML
    private void handleSendMessage() {
        if (activeChat == null) return;
        String text = messageField.getText().trim();
        if (text.isEmpty()) return;

        Message msg = chatService.sendMessage(activeChat.getId(), text);
        messagesListView.getItems().add(msg);
        messageField.clear();
    }
}
