package com.walid.taamine.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the ChpListeDeroulante entity.
 */
public class ChpListeDeroulanteDTO implements Serializable {

    private Long id;

    private String valeur;


    private Long champId;
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

        ChpListeDeroulanteDTO chpListeDeroulanteDTO = (ChpListeDeroulanteDTO) o;

        if ( ! Objects.equals(id, chpListeDeroulanteDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ChpListeDeroulanteDTO{" +
            "id=" + id +
            ", valeur='" + valeur + "'" +
            '}';
    }
}
