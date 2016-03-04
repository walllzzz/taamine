package com.walid.taamine.web.rest.mapper;

import com.walid.taamine.domain.*;
import com.walid.taamine.web.rest.dto.DevisTypeChpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DevisTypeChp and its DTO DevisTypeChpDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DevisTypeChpMapper {

    @Mapping(source = "devisType.id", target = "devisTypeId")
    @Mapping(source = "champ.id", target = "champId")
    DevisTypeChpDTO devisTypeChpToDevisTypeChpDTO(DevisTypeChp devisTypeChp);

    @Mapping(source = "devisTypeId", target = "devisType")
    @Mapping(source = "champId", target = "champ")
    DevisTypeChp devisTypeChpDTOToDevisTypeChp(DevisTypeChpDTO devisTypeChpDTO);

    default TypeDevis typeDevisFromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeDevis typeDevis = new TypeDevis();
        typeDevis.setId(id);
        return typeDevis;
    }

    default Champ champFromId(Long id) {
        if (id == null) {
            return null;
        }
        Champ champ = new Champ();
        champ.setId(id);
        return champ;
    }
}
