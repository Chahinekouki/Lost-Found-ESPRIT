package tn.esprit.lostandfound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.lostandfound.entity.Chat;
import tn.esprit.lostandfound.entity.ImageModel;
import tn.esprit.lostandfound.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ChatDAO extends JpaRepository<Chat,Long> {

    Chat findByName(String name);

    @Query("SELECT DISTINCT c FROM Chat c WHERE c.name like %:user% ")
    List<Chat> findByPartecipant(String user);
}
