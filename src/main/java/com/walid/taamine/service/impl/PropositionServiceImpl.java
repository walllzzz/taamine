package com.walid.taamine.service.impl;

import com.walid.taamine.service.PropositionService;
import com.walid.taamine.domain.Proposition;
import com.walid.taamine.repository.PropositionRepository;
import com.walid.taamine.web.rest.dto.PropositionDTO;
import com.walid.taamine.web.rest.mapper.PropositionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Proposition.
 */
@Service
@Transactional
public class PropositionServiceImpl implements PropositionService{

    private final Logger log = LoggerFactory.getLogger(PropositionServiceImpl.class);
    
    @Inject
    private PropositionRepository propositionRepository;
    
    @Inject
    private PropositionMapper propositionMapper;
    
    /**
     * Save a proposition.
     * @return the persisted entity
     */
    public PropositionDTO save(PropositionDTO propositionDTO) {
        log.debug("Request to save Proposition : {}", propositionDTO);
        Proposition proposition = propositionMapper.propositionDTOToProposition(propositionDTO);
        proposition = propositionRepository.save(proposition);
        PropositionDTO result = propositionMapper.propositionToPropositionDTO(proposition);
        return result;
    }

    /**
     *  get all the propositions.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PropositionDTO> findAll() {
        log.debug("Request to get all Propositions");
        List<PropositionDTO> result = propositionRepository.findAll().stream()
            .map(propositionMapper::propositionToPropositionDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one proposition by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PropositionDTO findOne(Long id) {
        log.debug("Request to get Proposition : {}", id);
        Proposition proposition = propositionRepository.findOne(id);
        PropositionDTO propositionDTO = propositionMapper.propositionToPropositionDTO(proposition);
        return propositionDTO;
    }

    /**
     *  delete the  proposition by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Proposition : {}", id);
        propositionRepository.delete(id);
    }
}
