package com.walid.taamine.service;

import com.walid.taamine.domain.Devis;
import com.walid.taamine.web.rest.dto.DevisDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Devis.
 */
public interface DevisService {

    /**
     * Save a devis.
     * @return the persisted entity
     */
    public DevisDTO save(DevisDTO devisDTO);

    /**
     *  get all the deviss.
     *  @return the list of entities
     */
    public List<DevisDTO> findAll();
    
    /**
     *  get all the deviss of user
     *  @return the list of entities
     */
    public List<DevisDTO> findAllByUserId(Long id);

    /**
     *  get the "id" devis.
     *  @return the entity
     */
    public DevisDTO findOne(Long id);

    /**
     *  delete the "id" devis.
     */
    public void delete(Long id);
}
