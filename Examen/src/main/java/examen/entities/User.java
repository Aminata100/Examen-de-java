package newstart.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import newstart.enums.Role;

@Data
@EqualsAndHashCode(callSuper = false, of = {"login"})
@ToString(exclude = "client")
@Entity
@Table(name = "user")
public class User extends AbstractEntity {


    @Basic
    @Column(length = 20, nullable = false)
    private String prenom;

    @Basic
    @Column(length = 20, nullable = false)
    private String nom;

    @Basic
    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Basic
    @Column(length = 20, nullable = false)
    private String password;

    @OneToOne(mappedBy = "compte") // Vérifiez ici
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Basic
    @Column(length = 20, nullable = false)
    private String statut = "Activer";
}
