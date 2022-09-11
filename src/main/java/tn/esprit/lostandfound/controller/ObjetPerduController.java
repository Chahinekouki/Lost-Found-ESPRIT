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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ObjetPerduController {

    @Autowired
    private ObjetPerduService objetPerduService;

    @Autowired
    private ObjetPerduDao objetPerduDao;



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

    @DeleteMapping("/deleteObjet/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id){

        ObjetPerdu objet = objetPerduDao.findById(id).get();


        objetPerduDao.delete(objet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
