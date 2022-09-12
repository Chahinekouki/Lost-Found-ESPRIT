package tn.esprit.lostandfound.service.dto;

import lombok.Builder;
import lombok.Data;
import tn.esprit.lostandfound.entity.ImageModel;
import tn.esprit.lostandfound.entity.Role;
import tn.esprit.lostandfound.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDTO {
    private String identifiant;
    private String userFirstName;
    private String userLastName;
    private String tel;
    private String email;
    private Boolean isBanned;
    private String adress;
    private ImageModel image;
    private Set<String> authorities ;

    public UserDTO() {
    }

    public UserDTO(String identifiant,String userFirstName, String userLastName, String tel, String email, Boolean isBanned,String adress,ImageModel imageModel, Set<String> authorities) {
        this.identifiant=identifiant;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.tel = tel;
        this.email = email;
        this.isBanned = isBanned;
        this.adress = adress;
        this.image=imageModel;
        this.authorities = authorities;
    }

    public UserDTO(User user) {
        this.userFirstName = user.getUserFirstName();
        this.userLastName = user.getUserLastName();
        this.tel = user.getTel();
        this.email = user.getEmail();
        this.isBanned = user.getBanned();
        this.authorities = user.getRole().stream()
        .map(Role::getRoleName)
        .collect(Collectors.toSet());
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", isBanned=" + isBanned +
                ", authorities=" + authorities +
                '}';
    }
}
