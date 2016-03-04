package com.walid.taamine.web.rest.mapper;

import com.walid.taamine.domain.*;
import com.walid.taamine.web.rest.dto.PropositionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Proposition and its DTO PropositionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PropositionMapper {

    @Mapping(source = "devis.id", target = "devisId")
    @Mapping(source = "entreprise.id", target = "entrepriseId")
    PropositionDTO propositionToPropositionDTO(Proposition proposition);

    @Mapping(source = "devisId", target = "devis")
    @Mapping(source = "entrepriseId", target = "entreprise")
    Proposition propositionDTOToProposition(PropositionDTO propositionDTO);

    default Devis devisFromId(Long id) {
        if (id == null) {
            return null;
        }
        Devis devis = new Devis();
        devis.setId(id);
        return devis;
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
