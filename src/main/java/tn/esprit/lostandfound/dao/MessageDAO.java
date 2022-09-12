package tn.esprit.lostandfound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.lostandfound.entity.Chat;
import tn.esprit.lostandfound.entity.Message;

import java.util.List;

public interface MessageDAO extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m WHERE m.chat_id = :id ")
    List<Message> findAllByChat(Long id);
}
