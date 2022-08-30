package tn.esprit.lostandfound.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.lostandfound.dao.ImageRepository;
import tn.esprit.lostandfound.dao.ObjetPerduDao;
import tn.esprit.lostandfound.dao.UserDao;
import tn.esprit.lostandfound.entity.ImageModel;
import tn.esprit.lostandfound.entity.ObjetPerdu;
import tn.esprit.lostandfound.entity.User;
import tn.esprit.lostandfound.service.ImageService;

@RestController


public class ImageUploadController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ObjetPerduDao objetPerduDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ImageService imageService;

    @PostMapping("/image/upload/{id}")
    public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file,@PathVariable("id") String id) throws IOException {

      ImageModel img = imageService.compressedImage(file);
        imageRepository.save(img);
        User user = userDao.findById(id).get();
        user.setImage(img);
        userDao.save(user);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping("/upload/image")
    public BodyBuilder uplaodImageObjet(@RequestParam("imageFile") MultipartFile file) throws IOException {

        ImageModel img = imageService.compressedImage(file);
        imageRepository.save(img);
        List<ObjetPerdu> objetPerdus =
                StreamSupport.stream(objetPerduDao.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        Long idd = objetPerdus.get(objetPerdus.size() - 1).getId();
        System.out.println(idd+"ddddddddd");
        ObjetPerdu objet =objetPerduDao.findById(idd).get();
        objet.setImage(img);
        objetPerduDao.save(objet);
        return ResponseEntity.status(HttpStatus.OK);
    }

}
