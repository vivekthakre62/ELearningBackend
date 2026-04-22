package com.Ecommerce.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ecommerce.entity.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
	List<Message> findByRoomIdOrderByTimestampAsc(String roomId);
	
	List<Message> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

	@Query("""
			SELECT m FROM Message m
			WHERE ((m.senderId = :user1 AND m.receiverId = :user2)
			    OR (m.senderId = :user2 AND m.receiverId = :user1))
			ORDER BY m.timestamp ASC
			""")
	List<Message> findConversation(@Param("user1") Long user1, @Param("user2") Long user2);

	boolean existsBySenderIdAndReceiverIdAndRoomIdAndContentAndTimestampAfter(
			Long senderId, Long receiverId, String roomId, String content, LocalDateTime timestamp);

}
