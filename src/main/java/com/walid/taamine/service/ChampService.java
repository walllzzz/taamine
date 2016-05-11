package com.walid.taamine.service;

import com.walid.taamine.domain.Champ;
import com.walid.taamine.web.rest.dto.ChampDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Champ.
 */
public interface ChampService {

    /**
     * Save a champ.
     * @return the persisted entity
     */
    public ChampDTO save(ChampDTO champDTO);

    /**
     *  get all the champs.
     *  @return the list of entities
     */
    public List<ChampDTO> findAll();

    /**
     *  get the "id" champ.
     *  @return the entity
     */
    public ChampDTO findOne(Long id);

    /**
     *  delete the "id" champ.
     */
    public void delete(Long id);
    

}
