package tn.esprit.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.lostandfound.dao.ObjetPerduDao;
import tn.esprit.lostandfound.dao.UserDao;
import tn.esprit.lostandfound.service.DashboardService;
import tn.esprit.lostandfound.service.dto.DashboardDTO;
import tn.esprit.lostandfound.service.dto.UserDTO;

import java.util.stream.StreamSupport;

@RestController
public class DashboardController {

    @Autowired
    UserDao userDao;

    @Autowired
    ObjetPerduDao objetPerduDao;

    @Autowired
    DashboardService dashboardService;


    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('Admin') ")
    public ResponseEntity<DashboardDTO>  Dashboard ()  {

        return new ResponseEntity<>(dashboardService.getDashboard(), HttpStatus.OK);



    }
}
