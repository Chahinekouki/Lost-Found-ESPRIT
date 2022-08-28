package tn.esprit.lostandfound.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.lostandfound.entity.ObjetPerdu;
import tn.esprit.lostandfound.entity.User;

@Repository
public interface ObjetPerduDao extends CrudRepository<ObjetPerdu, Long> {
    Page<ObjetPerdu> findAll (Pageable pageable);
}

