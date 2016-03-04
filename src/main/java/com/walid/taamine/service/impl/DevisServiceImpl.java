package com.walid.taamine.service.impl;

import com.walid.taamine.service.DevisService;
import com.walid.taamine.domain.Devis;
import com.walid.taamine.repository.DevisRepository;
import com.walid.taamine.web.rest.dto.DevisDTO;
import com.walid.taamine.web.rest.mapper.DevisMapper;
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
 * Service Implementation for managing Devis.
 */
@Service
@Transactional
public class DevisServiceImpl implements DevisService{

    private final Logger log = LoggerFactory.getLogger(DevisServiceImpl.class);
    
    @Inject
    private DevisRepository devisRepository;
    
    @Inject
    private DevisMapper devisMapper;
    
    /**
     * Save a devis.
     * @return the persisted entity
     */
    public DevisDTO save(DevisDTO devisDTO) {
        log.debug("Request to save Devis : {}", devisDTO);
        Devis devis = devisMapper.devisDTOToDevis(devisDTO);
        devis = devisRepository.save(devis);
        DevisDTO result = devisMapper.devisToDevisDTO(devis);
        return result;
    }

    /**
     *  get all the deviss.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<DevisDTO> findAll() {
        log.debug("Request to get all Deviss");
        List<DevisDTO> result = devisRepository.findAll().stream()
            .map(devisMapper::devisToDevisDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one devis by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DevisDTO findOne(Long id) {
        log.debug("Request to get Devis : {}", id);
        Devis devis = devisRepository.findOne(id);
        DevisDTO devisDTO = devisMapper.devisToDevisDTO(devis);
        return devisDTO;
    }

    /**
     *  delete the  devis by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Devis : {}", id);
        devisRepository.delete(id);
    }
}
