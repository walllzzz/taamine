package com.walid.taamine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Devis.
 */
@Entity
@Table(name = "devis")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Devis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_devis")
    private ZonedDateTime dateDevis;
    
    @ManyToOne
    @JoinColumn(name = "type_devis_id")
    private TypeDevis typeDevis;

    @OneToMany(mappedBy = "devis")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ValeurChamp> valeursChamps = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateDevis() {
        return dateDevis;
    }
    
    public void setDateDevis(ZonedDateTime dateDevis) {
        this.dateDevis = dateDevis;
    }

    public TypeDevis getTypeDevis() {
        return typeDevis;
    }

    public void setTypeDevis(TypeDevis typeDevis) {
        this.typeDevis = typeDevis;
    }

    public Set<ValeurChamp> getValeursChamps() {
        return valeursChamps;
    }

    public void setValeursChamps(Set<ValeurChamp> valeurChamps) {
        this.valeursChamps = valeurChamps;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Devis devis = (Devis) o;
        if(devis.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, devis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Devis{" +
            "id=" + id +
            ", dateDevis='" + dateDevis + "'" +
            '}';
    }
}
