package tn.esprit.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.lostandfound.dao.ObjetPerduDao;
import tn.esprit.lostandfound.entity.ObjetPerdu;
import tn.esprit.lostandfound.entity.User;
import tn.esprit.lostandfound.service.ImageService;
import tn.esprit.lostandfound.service.ObjetPerduService;
import tn.esprit.lostandfound.service.dto.ObjetPerduDTO;
import tn.esprit.lostandfound.service.dto.UserDTO;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@RestController
public class ObjetPerduController {

    @Autowired
    private ObjetPerduService objetPerduService;

    @Autowired
    private ObjetPerduDao objetPerduDao;

    @PostConstruct
    public void initRoleAndUser() {
        objetPerduService.initObjet();
    }

    @GetMapping("/objet/getAll")
    public ResponseEntity<List<ObjetPerduDTO>> getAllObjet(Pageable pageable) {

        final Page<ObjetPerduDTO> page = objetPerduService.getlistObjet(pageable);


        //
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    @PostMapping({"/addNewObject"})
    public ObjetPerdu registerNewUser(@RequestBody ObjetPerdu objet)
    {
        return objetPerduService.registerNewObjet(objet);
    }


}
