package newstart.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "payement")
public class Payment extends AbstractEntity {

    @Column(name = "montant", nullable = false)
    private int montant;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dette_id", nullable = false)
    private Dette dette;
}
