package tn.esprit.lostandfound.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.lostandfound.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
    Page<User> findAll(Pageable pageable);
}
