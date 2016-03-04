package com.walid.taamine.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Proposition entity.
 */
public class PropositionDTO implements Serializable {

    private Long id;

    private String prix;


    private ZonedDateTime dateProposition;


    private Long devisId;
    private Long entrepriseId;
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

    public Long getDevisId() {
        return devisId;
    }

    public void setDevisId(Long devisId) {
        this.devisId = devisId;
    }
    public Long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Long userId) {
        this.entrepriseId = userId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PropositionDTO propositionDTO = (PropositionDTO) o;

        if ( ! Objects.equals(id, propositionDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PropositionDTO{" +
            "id=" + id +
            ", prix='" + prix + "'" +
            ", dateProposition='" + dateProposition + "'" +
            '}';
    }
}
