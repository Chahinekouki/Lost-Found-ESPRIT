package tn.esprit.lostandfound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.lostandfound.dao.ImageRepository;
import tn.esprit.lostandfound.dao.ObjetPerduDao;
import tn.esprit.lostandfound.dao.RoleDao;
import tn.esprit.lostandfound.entity.ImageModel;
import tn.esprit.lostandfound.entity.ObjetPerdu;
import tn.esprit.lostandfound.entity.Role;
import tn.esprit.lostandfound.entity.User;
import tn.esprit.lostandfound.service.dto.ObjetPerduDTO;
import tn.esprit.lostandfound.service.dto.UserDTO;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ObjetPerduService {

    @Autowired
    private ObjetPerduDao objetPerduDao;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;


    public Page<ObjetPerduDTO> getlistObjet(Pageable pageable) {
        return  objetPerduDao.findAll(pageable).map(u-> {

                    try {

                        return ObjetPerduDTO.builder()
                                .id(u.getId())
                                .nom(u.getNom())
                                .description(u.getDescription())
                                .type(u.getType())
                                .etat(u.getEtat())
                                .date(u.getDate())
                                .image(this.imageService.decImage(Long.valueOf(u.getImage().getId())))
                                .build();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("eerreeur");
                    return null ;
                }
        );

    }


    public ObjetPerdu registerNewObjet(ObjetPerdu objet)  {
        objet.setEtat("en attente");
        return objetPerduDao.save(objet);
    }


}
