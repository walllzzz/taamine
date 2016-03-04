package com.walid.taamine.web.rest.mapper;

import com.walid.taamine.domain.*;
import com.walid.taamine.web.rest.dto.TypeDevisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeDevis and its DTO TypeDevisDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeDevisMapper {

    TypeDevisDTO typeDevisToTypeDevisDTO(TypeDevis typeDevis);

    TypeDevis typeDevisDTOToTypeDevis(TypeDevisDTO typeDevisDTO);
}
