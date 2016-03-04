package com.walid.taamine.web.rest.mapper;

import com.walid.taamine.domain.*;
import com.walid.taamine.web.rest.dto.ValeurChampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ValeurChamp and its DTO ValeurChampDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ValeurChampMapper {

    @Mapping(source = "devis.id", target = "devisId")
    @Mapping(source = "champ.id", target = "champId")
    ValeurChampDTO valeurChampToValeurChampDTO(ValeurChamp valeurChamp);

    @Mapping(source = "devisId", target = "devis")
    @Mapping(source = "champId", target = "champ")
    ValeurChamp valeurChampDTOToValeurChamp(ValeurChampDTO valeurChampDTO);

    default Devis devisFromId(Long id) {
        if (id == null) {
            return null;
        }
        Devis devis = new Devis();
        devis.setId(id);
        return devis;
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
