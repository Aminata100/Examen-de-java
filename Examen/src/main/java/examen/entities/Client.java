package newstart.entities;

import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false, of = {"surnom"})
@ToString(exclude = "dettes")
@Entity
@Table(name="client")
public class Client extends AbstractEntity {

    @Basic
    @Column(length=30, nullable=false)
    private String surnom;

    @Basic
    @Column(length=20, nullable=false)
    private String telephone;

    @Basic
    @Column(length=100, nullable=false)
    private String adresse;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "compteId", nullable=true)
    private User compte;

    @OneToMany(mappedBy="client")
    private List<Dette> dettes;
}
