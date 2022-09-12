package tn.esprit.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.lostandfound.entity.Objettrouve;
import tn.esprit.lostandfound.service.ObjettrouveService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/objettrouve")
public class ObjettrouveController {
    private final ObjettrouveService objettrouveService;
    @Autowired
    ServletContext context;
    public ObjettrouveController(ObjettrouveService objettrouveService) {
        this.objettrouveService = objettrouveService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Objettrouve>> getAllObjettrouve(){
        List<Objettrouve> objettrouves =objettrouveService.findAllObjettrouve();
        return new ResponseEntity<>(objettrouves, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Objettrouve> getObjettrouveById(@PathVariable("id") Long id){
        Objettrouve objettrouve =objettrouveService.findObjettrouveById(id);
        return new ResponseEntity<>(objettrouve, HttpStatus.OK);
    }

    @PostMapping(value="/add",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Objettrouve> addObjettrouve(@RequestPart("objet") Objettrouve objetTrouve,@RequestPart("image") MultipartFile image){

        objetTrouve.setDatetrouve(LocalDateTime.now());
        Objettrouve newObjetTrouve =objettrouveService.addObjetTrouve(objetTrouve);
        return new ResponseEntity<>(newObjetTrouve, HttpStatus.CREATED);
    }


    @PutMapping ("/update")
    public ResponseEntity<Objettrouve> updateObjettrouve(@RequestBody Objettrouve objetTrouve){

           objettrouveService.updateObjettrouve(objetTrouve.getNom(),objetTrouve.getDescription(),objetTrouve.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteObjettrouve(@PathVariable("id") Long id){
         objettrouveService.deleteObjettrouve(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
