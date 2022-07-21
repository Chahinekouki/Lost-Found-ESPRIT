package tn.esprit.lostandfound.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.lostandfound.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
