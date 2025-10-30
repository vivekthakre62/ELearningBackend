package com.Ecommerce.dta;

import java.time.LocalDateTime;

public class ChatMessage {
    private String senderId;
    private String receiverId;
    private String content;
    private String roomId;
    private LocalDateTime timestamp;

    // ✅ Default constructor
    public ChatMessage() {
    }

    // ✅ Parameterized constructor
    public ChatMessage(String senderId, String receiverId, String content, String roomId, LocalDateTime timestamp) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.roomId = roomId;
        this.timestamp = timestamp;
    }

    // ✅ Getters and Setters
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // ✅ toString method (optional, helpful for logging)
    @Override
    public String toString() {
        return "ChatMessage{" +
                "senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", content='" + content + '\'' +
                ", roomId='" + roomId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
