package tn.esprit.lostandfound.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@DynamicInsert
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class Objettrouve implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String nom;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetrouve;

  /*  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "objet_image",joinColumns = {
            @JoinColumn(name = "objet_id")}

,inverseJoinColumns = {
            @JoinColumn(name = "image_id")
    }
    )

   */

    private String iduser;


}
