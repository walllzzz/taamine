package com.walid.taamine.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DevisTypeChp.
 */
@Entity
@Table(name = "devis_type_chp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DevisTypeChp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "actif")
    private Boolean actif;
    
    @Column(name = "obligatoire")
    private Boolean obligatoire;
    
    @ManyToOne
    @JoinColumn(name = "devis_type_id")
    private TypeDevis devisType;

    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActif() {
        return actif;
    }
    
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Boolean getObligatoire() {
        return obligatoire;
    }
    
    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public TypeDevis getDevisType() {
        return devisType;
    }

    public void setDevisType(TypeDevis typeDevis) {
        this.devisType = typeDevis;
    }

    public Champ getChamp() {
        return champ;
    }

    public void setChamp(Champ champ) {
        this.champ = champ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DevisTypeChp devisTypeChp = (DevisTypeChp) o;
        if(devisTypeChp.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, devisTypeChp.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DevisTypeChp{" +
            "id=" + id +
            ", actif='" + actif + "'" +
            ", obligatoire='" + obligatoire + "'" +
            '}';
    }
}
