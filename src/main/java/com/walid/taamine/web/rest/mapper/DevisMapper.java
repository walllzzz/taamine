package com.walid.taamine.web.rest.mapper;

import com.walid.taamine.domain.*;
import com.walid.taamine.web.rest.dto.DevisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Devis and its DTO DevisDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DevisMapper {

    @Mapping(source = "typeDevis.id", target = "typeDevisId")
    @Mapping(source = "user.id", target = "userId")
    DevisDTO devisToDevisDTO(Devis devis);

    @Mapping(source = "typeDevisId", target = "typeDevis")
    @Mapping(target = "valeursChamps", ignore = true)
    @Mapping(source = "userId", target = "user")
    Devis devisDTOToDevis(DevisDTO devisDTO);

    default TypeDevis typeDevisFromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeDevis typeDevis = new TypeDevis();
        typeDevis.setId(id);
        return typeDevis;
    }

    default User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
