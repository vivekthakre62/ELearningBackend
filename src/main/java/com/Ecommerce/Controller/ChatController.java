package com.Ecommerce.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.Ecommerce.entity.Message;
import com.Ecommerce.repository.MessageRepo;

@Controller
public class ChatController {

    private final MessageRepo messageRepo;

    public ChatController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message sendMessage(@Payload Message message) {
        // Save message to DB
        messageRepo.save(message);
        return message;
    }
}



