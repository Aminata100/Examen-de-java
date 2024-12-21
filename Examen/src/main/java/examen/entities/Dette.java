package newstart.entities;

import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "dette")
public class Dette extends AbstractEntity {

    @Column(name = "montant_total")
    private Double montantTotal = 0.0;

    @Column(name = "montant_verse", nullable = false)
    private Double montantVerse = 0.0;

    @Transient
    public Double getMontantRestant() {
        return montantTotal - montantVerse;
    }

    @Column(nullable = false)
    private Boolean soldation = true;

    @Basic
    @Column(length=100, nullable=false)
    private String validation = "En cours";

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany(mappedBy = "dette")
    private List<Detail> details;

    @OneToMany(mappedBy = "dette", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payements;
}
