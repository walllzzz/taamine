package com.walid.taamine.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.walid.taamine.domain.TypeDevis;
import com.walid.taamine.service.TypeDevisService;
import com.walid.taamine.web.rest.util.HeaderUtil;
import com.walid.taamine.web.rest.dto.TypeDevisDTO;
import com.walid.taamine.web.rest.mapper.TypeDevisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing TypeDevis.
 */
@RestController
@RequestMapping("/api")
public class TypeDevisResource {

    private final Logger log = LoggerFactory.getLogger(TypeDevisResource.class);
        
    @Inject
    private TypeDevisService typeDevisService;
    
    @Inject
    private TypeDevisMapper typeDevisMapper;
    
    /**
     * POST  /typeDeviss -> Create a new typeDevis.
     */
    @RequestMapping(value = "/typeDeviss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TypeDevisDTO> createTypeDevis(@RequestBody TypeDevisDTO typeDevisDTO) throws URISyntaxException {
        log.debug("REST request to save TypeDevis : {}", typeDevisDTO);
        if (typeDevisDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("typeDevis", "idexists", "A new typeDevis cannot already have an ID")).body(null);
        }
        TypeDevisDTO result = typeDevisService.save(typeDevisDTO);
        return ResponseEntity.created(new URI("/api/typeDeviss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("typeDevis", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /typeDeviss -> Updates an existing typeDevis.
     */
    @RequestMapping(value = "/typeDeviss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TypeDevisDTO> updateTypeDevis(@RequestBody TypeDevisDTO typeDevisDTO) throws URISyntaxException {
        log.debug("REST request to update TypeDevis : {}", typeDevisDTO);
        if (typeDevisDTO.getId() == null) {
            return createTypeDevis(typeDevisDTO);
        }
        TypeDevisDTO result = typeDevisService.save(typeDevisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("typeDevis", typeDevisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /typeDeviss -> get all the typeDeviss.
     */
    @RequestMapping(value = "/typeDeviss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<TypeDevisDTO> getAllTypeDeviss() {
        log.debug("REST request to get all TypeDeviss");
        return typeDevisService.findAll();
            }

    /**
     * GET  /typeDeviss/:id -> get the "id" typeDevis.
     */
    @RequestMapping(value = "/typeDeviss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TypeDevisDTO> getTypeDevis(@PathVariable Long id) {
        log.debug("REST request to get TypeDevis : {}", id);
        TypeDevisDTO typeDevisDTO = typeDevisService.findOne(id);
        return Optional.ofNullable(typeDevisDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /typeDeviss/:id -> delete the "id" typeDevis.
     */
    @RequestMapping(value = "/typeDeviss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTypeDevis(@PathVariable Long id) {
        log.debug("REST request to delete TypeDevis : {}", id);
        typeDevisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("typeDevis", id.toString())).build();
    }
}
