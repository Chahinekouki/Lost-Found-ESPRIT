package tn.esprit.lostandfound.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.lostandfound.entity.User;
import tn.esprit.lostandfound.service.UserService;
import tn.esprit.lostandfound.service.dto.UserDTO;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    /**
     * TODO Documentation
     *
     * @param pageable
     * @return
     */
    @GetMapping("/getAll/pagination")

    public ResponseEntity<List<UserDTO>> getAllQcmsP(Pageable pageable) {

        final Page<UserDTO> page = userService.getlistUsers(pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }
}
