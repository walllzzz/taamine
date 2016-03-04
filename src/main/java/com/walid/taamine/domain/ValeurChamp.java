package com.walid.taamine.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ValeurChamp.
 */
@Entity
@Table(name = "valeur_champ")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValeurChamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "val_valeur")
    private String valValeur;
    
    @ManyToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValValeur() {
        return valValeur;
    }
    
    public void setValValeur(String valValeur) {
        this.valValeur = valValeur;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
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
        ValeurChamp valeurChamp = (ValeurChamp) o;
        if(valeurChamp.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, valeurChamp.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ValeurChamp{" +
            "id=" + id +
            ", valValeur='" + valValeur + "'" +
            '}';
    }
}
