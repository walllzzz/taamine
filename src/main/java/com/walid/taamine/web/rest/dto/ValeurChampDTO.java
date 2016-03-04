package com.walid.taamine.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the ValeurChamp entity.
 */
public class ValeurChampDTO implements Serializable {

    private Long id;

    private String valValeur;


    private Long devisId;
   
    private Long champId;
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

    public Long getDevisId() {
        return devisId;
    }

    public void setDevisId(Long devisId) {
        this.devisId = devisId;
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

        ValeurChampDTO valeurChampDTO = (ValeurChampDTO) o;

        if ( ! Objects.equals(id, valeurChampDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ValeurChampDTO{" +
            "id=" + id +
            ", valValeur='" + valValeur + "'" +
            '}';
    }
}
