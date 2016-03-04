package com.walid.taamine.service.impl;

import com.walid.taamine.service.ChampService;
import com.walid.taamine.domain.Champ;
import com.walid.taamine.repository.ChampRepository;
import com.walid.taamine.web.rest.dto.ChampDTO;
import com.walid.taamine.web.rest.mapper.ChampMapper;
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
 * Service Implementation for managing Champ.
 */
@Service
@Transactional
public class ChampServiceImpl implements ChampService{

    private final Logger log = LoggerFactory.getLogger(ChampServiceImpl.class);
    
    @Inject
    private ChampRepository champRepository;
    
    @Inject
    private ChampMapper champMapper;
    
    /**
     * Save a champ.
     * @return the persisted entity
     */
    public ChampDTO save(ChampDTO champDTO) {
        log.debug("Request to save Champ : {}", champDTO);
        Champ champ = champMapper.champDTOToChamp(champDTO);
        champ = champRepository.save(champ);
        ChampDTO result = champMapper.champToChampDTO(champ);
        return result;
    }

    /**
     *  get all the champs.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ChampDTO> findAll() {
        log.debug("Request to get all Champs");
        List<ChampDTO> result = champRepository.findAll().stream()
            .map(champMapper::champToChampDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one champ by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ChampDTO findOne(Long id) {
        log.debug("Request to get Champ : {}", id);
        Champ champ = champRepository.findOne(id);
        ChampDTO champDTO = champMapper.champToChampDTO(champ);
        return champDTO;
    }

    /**
     *  delete the  champ by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Champ : {}", id);
        champRepository.delete(id);
    }
}
