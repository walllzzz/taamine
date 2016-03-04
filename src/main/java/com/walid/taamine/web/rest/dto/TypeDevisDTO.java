package com.walid.taamine.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the TypeDevis entity.
 */
public class TypeDevisDTO implements Serializable {

    private Long id;

    private String libelle;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeDevisDTO typeDevisDTO = (TypeDevisDTO) o;

        if ( ! Objects.equals(id, typeDevisDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TypeDevisDTO{" +
            "id=" + id +
            ", libelle='" + libelle + "'" +
            '}';
    }
}
