package tn.esprit.lostandfound.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.lostandfound.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

}
