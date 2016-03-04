package com.walid.taamine.web.rest.mapper;

import com.walid.taamine.domain.*;
import com.walid.taamine.web.rest.dto.ChampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Champ and its DTO ChampDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChampMapper {

    ChampDTO champToChampDTO(Champ champ);

    Champ champDTOToChamp(ChampDTO champDTO);
}
