package com.walid.taamine.domain;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = ""
    + "The Employee entity.")
@Entity
@Table(name = "proposition")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Proposition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "prix")
    private String prix;
    
    @Column(name = "date_proposition")
    private ZonedDateTime dateProposition;
    
    @ManyToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private User entreprise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrix() {
        return prix;
    }
    
    public void setPrix(String prix) {
        this.prix = prix;
    }

    public ZonedDateTime getDateProposition() {
        return dateProposition;
    }
    
    public void setDateProposition(ZonedDateTime dateProposition) {
        this.dateProposition = dateProposition;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public User getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(User user) {
        this.entreprise = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proposition proposition = (Proposition) o;
        if(proposition.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, proposition.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Proposition{" +
            "id=" + id +
            ", prix='" + prix + "'" +
            ", dateProposition='" + dateProposition + "'" +
            '}';
    }
}
