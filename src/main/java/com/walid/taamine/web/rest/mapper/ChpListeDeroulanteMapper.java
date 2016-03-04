package com.walid.taamine.web.rest.mapper;

import com.walid.taamine.domain.*;
import com.walid.taamine.web.rest.dto.ChpListeDeroulanteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ChpListeDeroulante and its DTO ChpListeDeroulanteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChpListeDeroulanteMapper {

    @Mapping(source = "champ.id", target = "champId")
    ChpListeDeroulanteDTO chpListeDeroulanteToChpListeDeroulanteDTO(ChpListeDeroulante chpListeDeroulante);

    @Mapping(source = "champId", target = "champ")
    ChpListeDeroulante chpListeDeroulanteDTOToChpListeDeroulante(ChpListeDeroulanteDTO chpListeDeroulanteDTO);

    default Champ champFromId(Long id) {
        if (id == null) {
            return null;
        }
        Champ champ = new Champ();
        champ.setId(id);
        return champ;
    }
}
