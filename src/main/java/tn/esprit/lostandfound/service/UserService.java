package tn.esprit.lostandfound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.lostandfound.dao.RoleDao;
import tn.esprit.lostandfound.dao.UserDao;
import tn.esprit.lostandfound.entity.Role;
import tn.esprit.lostandfound.entity.User;
import tn.esprit.lostandfound.service.dto.UserDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

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
        return  userDao.findAll(pageable).map(u-> UserDTO.builder()
                .identifiant(u.getId())
                .userFirstName(u.getUserFirstName())
                .userLastName(u.getUserLastName())
                .tel(u.getTel())
                .email(u.getEmail())
                .isBanned(u.getBanned())
                .authorities(u.getRole().stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toSet()))
                .build()
        );

    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
