package com.Ecommerce.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.Ecommerce.entity.Message;
import com.Ecommerce.repository.MessageRepo;

@Service
public class MessageService {

    private static final long DUPLICATE_WINDOW_SECONDS = 2L;

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Message saveMessage(Message message) {
        if (message.getTimestamp() == null) {
            message.setTimestamp(LocalDateTime.now());
        }

        boolean duplicate = messageRepo.existsBySenderIdAndReceiverIdAndRoomIdAndContentAndTimestampAfter(
                message.getSenderId(),
                message.getReceiverId(),
                message.getRoomId(),
                message.getContent(),
                LocalDateTime.now().minusSeconds(DUPLICATE_WINDOW_SECONDS));

        if (duplicate) {
            return message;
        }

        return messageRepo.save(message);
    }
}
