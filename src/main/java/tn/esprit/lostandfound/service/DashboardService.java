package tn.esprit.lostandfound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.lostandfound.dao.ObjetPerduDao;
import tn.esprit.lostandfound.dao.UserDao;
import tn.esprit.lostandfound.entity.Role;
import tn.esprit.lostandfound.entity.User;
import tn.esprit.lostandfound.service.dto.DashboardDTO;
import tn.esprit.lostandfound.service.dto.UserDTO;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DashboardService {

    @Autowired
    UserDao userDao;

    @Autowired
    ObjetPerduDao objetPerduDao;


    public DashboardDTO getDashboard()  {
        long totalUser =  StreamSupport.stream(userDao.findAll().spliterator(), false).count();
        int totalObject =  Integer.valueOf((int) StreamSupport.stream(objetPerduDao.findAll().spliterator(), false).count());
        return DashboardDTO.builder()
                .totalUser(totalUser)
                .totalObjectP(totalObject)
                .newUser(0)
                .totalObjectT(0)
                .totalObjectRet(0)
                .build();
    }



}
