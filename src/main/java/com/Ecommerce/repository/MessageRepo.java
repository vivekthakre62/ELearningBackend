package com.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.entity.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
	List<Message> findByRoomIdOrderByTimestampAsc(String roomId);
	
	List<Message> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

}
