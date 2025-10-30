package com.Ecommerce.Controller;

import org.springframework.web.bind.annotation.*;
import com.Ecommerce.entity.Message;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.MessageRepo;
import com.Ecommerce.repository.UserRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageRepo messageRepo;
    
    private final UserRepo userRepo;

    public MessageRestController(MessageRepo messageRepo,UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
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

    // ✅ Optional: send message API
    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageRepo.save(message);
    }

    // ✅ Optional: get all messages between two users
    @GetMapping("/conversation/{user1}/{user2}")
    public List<Message> getConversation(@PathVariable Long user1, @PathVariable Long user2) {
        List<Message> messages = messageRepo.findAll();
        return messages.stream()
                .filter(m -> (m.getSenderId().equals(user1) && m.getReceiverId().equals(user2)) ||
                             (m.getSenderId().equals(user2) && m.getReceiverId().equals(user1)))
                .sorted(Comparator.comparing(Message::getTimestamp))
                .collect(Collectors.toList());
    }
  

}
