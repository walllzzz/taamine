package com.walid.taamine.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Champ.
 */
@Entity
@Table(name = "champ")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Champ implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chp_libelle")
    private String chpLibelle;
    
    @Column(name = "chp_dans_liste")
    private Boolean chpDansListe;
    
    @Column(name = "chp_type")
    private Long chpType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChpLibelle() {
        return chpLibelle;
    }
    
    public void setChpLibelle(String chpLibelle) {
        this.chpLibelle = chpLibelle;
    }

    public Boolean getChpDansListe() {
        return chpDansListe;
    }
    
    public void setChpDansListe(Boolean chpDansListe) {
        this.chpDansListe = chpDansListe;
    }

    public Long getChpType() {
        return chpType;
    }
    
    public void setChpType(Long chpType) {
        this.chpType = chpType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Champ champ = (Champ) o;
        if(champ.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, champ.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Champ{" +
            "id=" + id +
            ", chpLibelle='" + chpLibelle + "'" +
            ", chpDansListe='" + chpDansListe + "'" +
            ", chpType='" + chpType + "'" +
            '}';
    }
}
