package tn.esprit.lostandfound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.lostandfound.dao.RoleDao;
import tn.esprit.lostandfound.dao.UserDao;
import tn.esprit.lostandfound.entity.Role;
import tn.esprit.lostandfound.entity.User;

import java.util.HashSet;
import java.util.Set;

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
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User adminUser1 = new User();
        adminUser1.setId("213JMT1112");
        adminUser1.setUserPassword(getEncodedPassword("aicha123"));
        adminUser1.setUserFirstName("Aicha");
        adminUser1.setUserLastName("Salhi");
        Set<Role> userRoles1 = new HashSet<>();
        userRoles1.add(userRole);
        adminUser1.setRole(userRoles1);
        userDao.save(adminUser1);

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

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
