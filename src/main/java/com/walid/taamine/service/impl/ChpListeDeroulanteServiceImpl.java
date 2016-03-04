package com.walid.taamine.service.impl;

import com.walid.taamine.service.ChpListeDeroulanteService;
import com.walid.taamine.domain.ChpListeDeroulante;
import com.walid.taamine.repository.ChpListeDeroulanteRepository;
import com.walid.taamine.web.rest.dto.ChpListeDeroulanteDTO;
import com.walid.taamine.web.rest.mapper.ChpListeDeroulanteMapper;
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
 * Service Implementation for managing ChpListeDeroulante.
 */
@Service
@Transactional
public class ChpListeDeroulanteServiceImpl implements ChpListeDeroulanteService{

    private final Logger log = LoggerFactory.getLogger(ChpListeDeroulanteServiceImpl.class);
    
    @Inject
    private ChpListeDeroulanteRepository chpListeDeroulanteRepository;
    
    @Inject
    private ChpListeDeroulanteMapper chpListeDeroulanteMapper;
    
    /**
     * Save a chpListeDeroulante.
     * @return the persisted entity
     */
    public ChpListeDeroulanteDTO save(ChpListeDeroulanteDTO chpListeDeroulanteDTO) {
        log.debug("Request to save ChpListeDeroulante : {}", chpListeDeroulanteDTO);
        ChpListeDeroulante chpListeDeroulante = chpListeDeroulanteMapper.chpListeDeroulanteDTOToChpListeDeroulante(chpListeDeroulanteDTO);
        chpListeDeroulante = chpListeDeroulanteRepository.save(chpListeDeroulante);
        ChpListeDeroulanteDTO result = chpListeDeroulanteMapper.chpListeDeroulanteToChpListeDeroulanteDTO(chpListeDeroulante);
        return result;
    }

    /**
     *  get all the chpListeDeroulantes.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ChpListeDeroulanteDTO> findAll() {
        log.debug("Request to get all ChpListeDeroulantes");
        List<ChpListeDeroulanteDTO> result = chpListeDeroulanteRepository.findAll().stream()
            .map(chpListeDeroulanteMapper::chpListeDeroulanteToChpListeDeroulanteDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one chpListeDeroulante by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ChpListeDeroulanteDTO findOne(Long id) {
        log.debug("Request to get ChpListeDeroulante : {}", id);
        ChpListeDeroulante chpListeDeroulante = chpListeDeroulanteRepository.findOne(id);
        ChpListeDeroulanteDTO chpListeDeroulanteDTO = chpListeDeroulanteMapper.chpListeDeroulanteToChpListeDeroulanteDTO(chpListeDeroulante);
        return chpListeDeroulanteDTO;
    }

    /**
     *  delete the  chpListeDeroulante by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete ChpListeDeroulante : {}", id);
        chpListeDeroulanteRepository.delete(id);
    }
}
