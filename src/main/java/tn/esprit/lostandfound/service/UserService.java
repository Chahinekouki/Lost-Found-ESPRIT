package tn.esprit.lostandfound.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.lostandfound.dao.ImageRepository;
import tn.esprit.lostandfound.dao.RoleDao;
import tn.esprit.lostandfound.dao.UserDao;
import tn.esprit.lostandfound.entity.ImageModel;
import tn.esprit.lostandfound.entity.Role;
import tn.esprit.lostandfound.entity.User;
import tn.esprit.lostandfound.service.dto.UserDTO;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private ImageService imageService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setId("213JMT1111");
        adminUser.setUserPassword(getEncodedPassword("chahine123"));
        adminUser.setUserFirstName("chahine");
        adminUser.setUserLastName("kouki");
        adminUser.setCreatedBy("Serveur");
        adminUser.setEmail("chahinekouki1998@gmail.com");
        adminUser.setTel("+21653000000");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User adminUser1 = new User();
        adminUser1.setId("213JMT1112");
        adminUser1.setUserPassword(getEncodedPassword("aicha123"));
        adminUser1.setUserFirstName("Aicha");
        adminUser1.setCreatedBy("Serveur");
        adminUser1.setUserLastName("Salhi");
        adminUser1.setEmail("AichaSalhi@gmail.com");
        adminUser1.setBanned(Boolean.TRUE);
        adminUser1.setTel("+21653000000");
        Set<Role> userRoles1 = new HashSet<>();
        userRoles1.add(userRole);
        adminUser1.setRole(userRoles1);
        userDao.save(adminUser1);

        User User2 = new User();
        User2.setId("213JMT1113");
        User2.setUserPassword(getEncodedPassword("behija123"));
        User2.setUserFirstName("behija");
        User2.setCreatedBy("Serveur");
        User2.setUserLastName("ben ghorbel");
        User2.setEmail("Behijabenghorbel@gmail.com");
        User2.setBanned(Boolean.TRUE);
        User2.setTel("+21653000000");
        Set<Role> userRoles2 = new HashSet<>();
        userRoles2.add(userRole);
        User2.setRole(userRoles2);
        userDao.save(User2);

//        User user = new User();
//        user.setid("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {

        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }
    public Page<UserDTO> getlistUsers(Pageable pageable) {
        return  userDao.findAll(pageable).map(u-> {
            System.out.println("idddddddddd:  " +u.getImage().getId());
                    try {

                        return UserDTO.builder()
                                .identifiant(u.getId())
                                .userFirstName(u.getUserFirstName())
                                .userLastName(u.getUserLastName())
                                .tel(u.getTel())
                                .email(u.getEmail())
                                .isBanned(u.getBanned())
                                .image(this.imageService.decImage(Long.valueOf(u.getImage().getId())))
                                .authorities(u.getRole().stream()
                                        .map(Role::getRoleName)
                                        .collect(Collectors.toSet()))
                                .build();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("eerreeur");
                    return null ;
                }
        );

    }




    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    public void banUser(String id) throws Exception {
        Optional<User> user = userDao.findById(id);
        if(user.isPresent() ){
            user.get().setBanned(Boolean.TRUE);
            userDao.save(user.get());
            log.debug("User banned", user);
        } else throw new Exception(String.valueOf(HttpStatus.NOT_ACCEPTABLE));
    }

    public void allowUser(String id) throws Exception {

        Optional<User> user = userDao.findById(id);
        if(user.isPresent() ){
            user.get().setBanned(Boolean.FALSE);
            userDao.save(user.get());
            log.debug("User allowed", user);

        } else throw new Exception(String.valueOf(HttpStatus.NOT_ACCEPTABLE));
    }

    public UserDTO getUser(String id) throws Exception {
        Optional<User> user = userDao.findById(id);
        if (!user.isPresent()) return UserDTO.builder().build();
        return UserDTO.builder()
                .identifiant(user.get().getId())
                .userFirstName(user.get().getUserFirstName())
                .userLastName(user.get().getUserLastName())
                .tel(user.get().getTel())
                .email(user.get().getEmail())
                .isBanned(user.get().getBanned())
                .authorities(user.get().getRole().stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toSet()))
                .build();
    }



    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
