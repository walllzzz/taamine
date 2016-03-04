package com.walid.taamine.service.impl;

import com.walid.taamine.service.DevisTypeChpService;
import com.walid.taamine.domain.DevisTypeChp;
import com.walid.taamine.repository.DevisTypeChpRepository;
import com.walid.taamine.web.rest.dto.DevisTypeChpDTO;
import com.walid.taamine.web.rest.mapper.DevisTypeChpMapper;
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
 * Service Implementation for managing DevisTypeChp.
 */
@Service
@Transactional
public class DevisTypeChpServiceImpl implements DevisTypeChpService{

    private final Logger log = LoggerFactory.getLogger(DevisTypeChpServiceImpl.class);
    
    @Inject
    private DevisTypeChpRepository devisTypeChpRepository;
    
    @Inject
    private DevisTypeChpMapper devisTypeChpMapper;
    
    /**
     * Save a devisTypeChp.
     * @return the persisted entity
     */
    public DevisTypeChpDTO save(DevisTypeChpDTO devisTypeChpDTO) {
        log.debug("Request to save DevisTypeChp : {}", devisTypeChpDTO);
        DevisTypeChp devisTypeChp = devisTypeChpMapper.devisTypeChpDTOToDevisTypeChp(devisTypeChpDTO);
        devisTypeChp = devisTypeChpRepository.save(devisTypeChp);
        DevisTypeChpDTO result = devisTypeChpMapper.devisTypeChpToDevisTypeChpDTO(devisTypeChp);
        return result;
    }

    /**
     *  get all the devisTypeChps.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<DevisTypeChpDTO> findAll() {
        log.debug("Request to get all DevisTypeChps");
        List<DevisTypeChpDTO> result = devisTypeChpRepository.findAll().stream()
            .map(devisTypeChpMapper::devisTypeChpToDevisTypeChpDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one devisTypeChp by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DevisTypeChpDTO findOne(Long id) {
        log.debug("Request to get DevisTypeChp : {}", id);
        DevisTypeChp devisTypeChp = devisTypeChpRepository.findOne(id);
        DevisTypeChpDTO devisTypeChpDTO = devisTypeChpMapper.devisTypeChpToDevisTypeChpDTO(devisTypeChp);
        return devisTypeChpDTO;
    }

    /**
     *  delete the  devisTypeChp by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete DevisTypeChp : {}", id);
        devisTypeChpRepository.delete(id);
    }
}
