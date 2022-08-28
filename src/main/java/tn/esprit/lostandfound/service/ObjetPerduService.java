package tn.esprit.lostandfound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.esprit.lostandfound.dao.ObjetPerduDao;
import tn.esprit.lostandfound.dao.RoleDao;
import tn.esprit.lostandfound.entity.ObjetPerdu;
import tn.esprit.lostandfound.entity.Role;
import tn.esprit.lostandfound.service.dto.ObjetPerduDTO;
import tn.esprit.lostandfound.service.dto.UserDTO;

import java.sql.Date;
import java.util.stream.Collectors;


@Service
public class ObjetPerduService {

    @Autowired
    private ObjetPerduDao objetPerduDao;


    public void initObjet() {
        ObjetPerdu ob = new ObjetPerdu();
        ob.setNom("objet1");
        ob.setDescription("pc perdu le 2 juillet ");
        ob.setEtat("Rendu");
        String str="2015-03-31";
        Date date= java.sql.Date.valueOf(str);//converting string into sql date.
        ob.setDate(date);
        objetPerduDao.save(ob);


        ObjetPerdu ob1 = new ObjetPerdu();
        ob1.setNom("objet2");
        ob1.setDescription("tel perdu le 2 juillet ");
        ob1.setEtat("en attente");
        String str2="2020-03-31";
        Date date2= java.sql.Date.valueOf(str);//converting string into sql date.
        ob1.setDate(date2);
        objetPerduDao.save(ob1);
    }

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
                                .build();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("eerreeur");
                    return null ;
                }
        );

    }


}