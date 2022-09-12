package tn.esprit.lostandfound.dao;

import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.lostandfound.entity.Objettrouve;

import javax.transaction.Transactional;
import java.util.Optional;


public interface ObjettrouveDao extends JpaRepository<Objettrouve,Long> {

    Optional<Objettrouve> findObjettrouveById(Long id);

    @Transactional
    @Modifying

    @Query("update Objettrouve o SET o.nom= :nom,o.description=:description where o.id =:id")
   void updateObjet(@Param("nom") String nom , @Param("description") String description ,@Param("id") Long id);


}
