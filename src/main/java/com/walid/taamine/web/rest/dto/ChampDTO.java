package com.walid.taamine.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Champ entity.
 */
public class ChampDTO implements Serializable {

    private Long id;

    private String chpLibelle;


    private Boolean chpDansListe;


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

        ChampDTO champDTO = (ChampDTO) o;

        if ( ! Objects.equals(id, champDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ChampDTO{" +
            "id=" + id +
            ", chpLibelle='" + chpLibelle + "'" +
            ", chpDansListe='" + chpDansListe + "'" +
            ", chpType='" + chpType + "'" +
            '}';
    }
}
