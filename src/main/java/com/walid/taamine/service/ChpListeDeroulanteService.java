package com.walid.taamine.service;

import com.walid.taamine.domain.ChpListeDeroulante;
import com.walid.taamine.web.rest.dto.ChpListeDeroulanteDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing ChpListeDeroulante.
 */
public interface ChpListeDeroulanteService {

    /**
     * Save a chpListeDeroulante.
     * @return the persisted entity
     */
    public ChpListeDeroulanteDTO save(ChpListeDeroulanteDTO chpListeDeroulanteDTO);

    /**
     *  get all the chpListeDeroulantes.
     *  @return the list of entities
     */
    public List<ChpListeDeroulanteDTO> findAll();

    /**
     *  get the "id" chpListeDeroulante.
     *  @return the entity
     */
    public ChpListeDeroulanteDTO findOne(Long id);

    /**
     *  delete the "id" chpListeDeroulante.
     */
    public void delete(Long id);
    
    
    /**
     * find by champ Id
     * @param id
     * @return
     */
	public List<ChpListeDeroulanteDTO> findByChampId(Long id);
}
