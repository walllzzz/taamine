package com.walid.taamine.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Devis entity.
 */
public class DevisDTO implements Serializable {

    private Long id;

    private ZonedDateTime dateDevis;


    private Long typeDevisId;
    private Long userId;
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

    public Long getTypeDevisId() {
        return typeDevisId;
    }

    public void setTypeDevisId(Long typeDevisId) {
        this.typeDevisId = typeDevisId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DevisDTO devisDTO = (DevisDTO) o;

        if ( ! Objects.equals(id, devisDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DevisDTO{" +
            "id=" + id +
            ", dateDevis='" + dateDevis + "'" +
            '}';
    }
}
