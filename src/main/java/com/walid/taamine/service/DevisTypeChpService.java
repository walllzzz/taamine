package com.walid.taamine.service;

import com.walid.taamine.domain.DevisTypeChp;
import com.walid.taamine.web.rest.dto.ChampDTO;
import com.walid.taamine.web.rest.dto.DevisTypeChpDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing DevisTypeChp.
 */
public interface DevisTypeChpService {

    /**
     * Save a devisTypeChp.
     * @return the persisted entity
     */
    public DevisTypeChpDTO save(DevisTypeChpDTO devisTypeChpDTO);

    /**
     *  get all the devisTypeChps.
     *  @return the list of entities
     */
    public List<DevisTypeChpDTO> findAll();

    /**
     *  get the "id" devisTypeChp.
     *  @return the entity
     */
    public DevisTypeChpDTO findOne(Long id);

    /**
     *  delete the "id" devisTypeChp.
     */
    public void delete(Long id);
    
    /**
     * find champs by idTypeDevis
     */
    public List<DevisTypeChpDTO> findByTypeDevis(Long idTypeDevis);
}
