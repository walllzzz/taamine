package com.walid.taamine.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the DevisTypeChp entity.
 */
public class DevisTypeChpDTO implements Serializable {

    private Long id;

    private Boolean actif;


    private Boolean obligatoire;

    private Long devisTypeId;
    
    private Long champId;
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

    public Long getDevisTypeId() {
        return devisTypeId;
    }

    public void setDevisTypeId(Long typeDevisId) {
        this.devisTypeId = typeDevisId;
    }
    public Long getChampId() {
        return champId;
    }

    public void setChampId(Long champId) {
        this.champId = champId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DevisTypeChpDTO devisTypeChpDTO = (DevisTypeChpDTO) o;

        if ( ! Objects.equals(id, devisTypeChpDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DevisTypeChpDTO{" +
            "id=" + id +
            ", actif='" + actif + "'" +
            ", obligatoire='" + obligatoire + "'" +
            '}';
    }
}
