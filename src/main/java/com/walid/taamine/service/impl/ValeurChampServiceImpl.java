package com.walid.taamine.service.impl;

import com.walid.taamine.service.ValeurChampService;
import com.walid.taamine.domain.ValeurChamp;
import com.walid.taamine.repository.ValeurChampRepository;
import com.walid.taamine.web.rest.dto.ValeurChampDTO;
import com.walid.taamine.web.rest.mapper.ValeurChampMapper;
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
 * Service Implementation for managing ValeurChamp.
 */
@Service
@Transactional
public class ValeurChampServiceImpl implements ValeurChampService{

    private final Logger log = LoggerFactory.getLogger(ValeurChampServiceImpl.class);
    
    @Inject
    private ValeurChampRepository valeurChampRepository;
    
    @Inject
    private ValeurChampMapper valeurChampMapper;
    
    /**
     * Save a valeurChamp.
     * @return the persisted entity
     */
    public ValeurChampDTO save(ValeurChampDTO valeurChampDTO) {
        log.debug("Request to save ValeurChamp : {}", valeurChampDTO);
        ValeurChamp valeurChamp = valeurChampMapper.valeurChampDTOToValeurChamp(valeurChampDTO);
        valeurChamp = valeurChampRepository.save(valeurChamp);
        ValeurChampDTO result = valeurChampMapper.valeurChampToValeurChampDTO(valeurChamp);
        return result;
    }

    /**
     *  get all the valeurChamps.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ValeurChampDTO> findAll() {
        log.debug("Request to get all ValeurChamps");
        List<ValeurChampDTO> result = valeurChampRepository.findAll().stream()
            .map(valeurChampMapper::valeurChampToValeurChampDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one valeurChamp by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ValeurChampDTO findOne(Long id) {
        log.debug("Request to get ValeurChamp : {}", id);
        ValeurChamp valeurChamp = valeurChampRepository.findOne(id);
        ValeurChampDTO valeurChampDTO = valeurChampMapper.valeurChampToValeurChampDTO(valeurChamp);
        return valeurChampDTO;
    }

    /**
     *  delete the  valeurChamp by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete ValeurChamp : {}", id);
        valeurChampRepository.delete(id);
    }

	@Override
	public List<ValeurChampDTO> findAllByDevisId(Long id) {		
		 log.debug("Request to get all ValeurChampsDevis by id {}",id);
		 List<ValeurChampDTO> result = valeurChampRepository.findAllByDevisId(id).stream()
		            .map(valeurChampMapper::valeurChampToValeurChampDTO)
		            .collect(Collectors.toCollection(LinkedList::new));
		        return result;
	}
}
