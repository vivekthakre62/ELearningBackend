package com.Ecommerce.Controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.entity.Message;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.MessageRepo;
import com.Ecommerce.repository.UserRepo;
import com.Ecommerce.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final MessageService messageService;

    public MessageRestController(MessageRepo messageRepo, UserRepo userRepo, MessageService messageService) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        this.messageService = messageService;
    }

    @GetMapping("/{roomId}")
    public List<Message> getMessages(@PathVariable String roomId) {
        return messageRepo.findByRoomIdOrderByTimestampAsc(roomId);
    }

    @GetMapping("/teachers/{studentId}")
    public List<User> getTeachersForStudent(@PathVariable Long studentId) {
        List<Message> messages = messageRepo.findBySenderIdOrReceiverId(studentId, studentId);

        Set<Long> teacherIds = messages.stream()
                .map(msg -> msg.getSenderId().equals(studentId) ? msg.getReceiverId() : msg.getSenderId())
                .collect(Collectors.toSet());

        return userRepo.findAllById(teacherIds);
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/conversation/{user1}/{user2}")
    public List<Message> getConversation(@PathVariable Long user1, @PathVariable Long user2) {
        return messageRepo.findConversation(user1, user2);
    }
}
