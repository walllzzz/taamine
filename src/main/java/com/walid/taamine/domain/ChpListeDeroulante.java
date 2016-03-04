package com.walid.taamine.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ChpListeDeroulante.
 */
@Entity
@Table(name = "chp_liste_deroulante")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChpListeDeroulante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "valeur")
    private String valeur;
    
    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }
    
    public void setValeur(String valeur) {
        this.valeur = valeur;
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
        ChpListeDeroulante chpListeDeroulante = (ChpListeDeroulante) o;
        if(chpListeDeroulante.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, chpListeDeroulante.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ChpListeDeroulante{" +
            "id=" + id +
            ", valeur='" + valeur + "'" +
            '}';
    }
}
