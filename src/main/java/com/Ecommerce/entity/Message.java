package com.Ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="messages")
public class Message {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Long senderId;
	    private Long receiverId;
	    private String senderName;
	    private String content;
	    private String roomId;

	    private LocalDateTime timestamp = LocalDateTime.now();

	    // Constructors
	    public Message() {}

	    public Message(Long senderId, Long receiverId, String senderName, String content, String roomId) {
	        this.senderId = senderId;
	        this.receiverId = receiverId;
	        this.senderName = senderName;
	        this.content = content;
	        this.roomId = roomId;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getSenderId() {
	        return senderId;
	    }

	    public void setSenderId(Long senderId) {
	        this.senderId = senderId;
	    }

	    public Long getReceiverId() {
	        return receiverId;
	    }

	    public void setReceiverId(Long receiverId) {
	        this.receiverId = receiverId;
	    }

	    public String getSenderName() {
	        return senderName;
	    }

	    public void setSenderName(String senderName) {
	        this.senderName = senderName;
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
	}


