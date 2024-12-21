package examen.core.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="article")
public class Article extends AbstractEntity{

    private String reference;
    private String libelle;
    private int prix;
    private int qteStock;

    @ManyToMany(mappedBy="article")
    private List<Detail> details;
    
}

