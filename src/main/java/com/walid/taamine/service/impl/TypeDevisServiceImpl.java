package com.walid.taamine.service.impl;

import com.walid.taamine.service.TypeDevisService;
import com.walid.taamine.domain.TypeDevis;
import com.walid.taamine.repository.TypeDevisRepository;
import com.walid.taamine.web.rest.dto.TypeDevisDTO;
import com.walid.taamine.web.rest.mapper.TypeDevisMapper;
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
 * Service Implementation for managing TypeDevis.
 */
@Service
@Transactional
public class TypeDevisServiceImpl implements TypeDevisService{

    private final Logger log = LoggerFactory.getLogger(TypeDevisServiceImpl.class);
    
    @Inject
    private TypeDevisRepository typeDevisRepository;
    
    @Inject
    private TypeDevisMapper typeDevisMapper;
    
    /**
     * Save a typeDevis.
     * @return the persisted entity
     */
    public TypeDevisDTO save(TypeDevisDTO typeDevisDTO) {
        log.debug("Request to save TypeDevis : {}", typeDevisDTO);
        TypeDevis typeDevis = typeDevisMapper.typeDevisDTOToTypeDevis(typeDevisDTO);
        typeDevis = typeDevisRepository.save(typeDevis);
        TypeDevisDTO result = typeDevisMapper.typeDevisToTypeDevisDTO(typeDevis);
        return result;
    }

    /**
     *  get all the typeDeviss.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TypeDevisDTO> findAll() {
        log.debug("Request to get all TypeDeviss");
        List<TypeDevisDTO> result = typeDevisRepository.findAll().stream()
            .map(typeDevisMapper::typeDevisToTypeDevisDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one typeDevis by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public TypeDevisDTO findOne(Long id) {
        log.debug("Request to get TypeDevis : {}", id);
        TypeDevis typeDevis = typeDevisRepository.findOne(id);
        TypeDevisDTO typeDevisDTO = typeDevisMapper.typeDevisToTypeDevisDTO(typeDevis);
        return typeDevisDTO;
    }

    /**
     *  delete the  typeDevis by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeDevis : {}", id);
        typeDevisRepository.delete(id);
    }
}
