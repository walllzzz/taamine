package com.walid.taamine.service;

import com.walid.taamine.domain.TypeDevis;
import com.walid.taamine.web.rest.dto.TypeDevisDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing TypeDevis.
 */
public interface TypeDevisService {

    /**
     * Save a typeDevis.
     * @return the persisted entity
     */
    public TypeDevisDTO save(TypeDevisDTO typeDevisDTO);

    /**
     *  get all the typeDeviss.
     *  @return the list of entities
     */
    public List<TypeDevisDTO> findAll();

    /**
     *  get the "id" typeDevis.
     *  @return the entity
     */
    public TypeDevisDTO findOne(Long id);

    /**
     *  delete the "id" typeDevis.
     */
    public void delete(Long id);
}
