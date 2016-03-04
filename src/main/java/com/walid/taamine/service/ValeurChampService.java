package com.walid.taamine.service;

import com.walid.taamine.domain.ValeurChamp;
import com.walid.taamine.web.rest.dto.ValeurChampDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing ValeurChamp.
 */
public interface ValeurChampService {

    /**
     * Save a valeurChamp.
     * @return the persisted entity
     */
    public ValeurChampDTO save(ValeurChampDTO valeurChampDTO);

    /**
     *  get all the valeurChamps.
     *  @return the list of entities
     */
    public List<ValeurChampDTO> findAll();

    /**
     *  get the "id" valeurChamp.
     *  @return the entity
     */
    public ValeurChampDTO findOne(Long id);

    /**
     *  delete the "id" valeurChamp.
     */
    public void delete(Long id);
}
