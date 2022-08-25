package tn.esprit.lostandfound.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.lostandfound.entity.ImageModel;


public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByName(String name);

}
