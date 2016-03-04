package com.walid.taamine.service;

import com.walid.taamine.domain.Proposition;
import com.walid.taamine.web.rest.dto.PropositionDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Proposition.
 */
public interface PropositionService {

    /**
     * Save a proposition.
     * @return the persisted entity
     */
    public PropositionDTO save(PropositionDTO propositionDTO);

    /**
     *  get all the propositions.
     *  @return the list of entities
     */
    public List<PropositionDTO> findAll();

    /**
     *  get the "id" proposition.
     *  @return the entity
     */
    public PropositionDTO findOne(Long id);

    /**
     *  delete the "id" proposition.
     */
    public void delete(Long id);
}
