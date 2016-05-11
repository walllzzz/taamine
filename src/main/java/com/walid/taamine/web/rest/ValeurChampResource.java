package com.walid.taamine.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.walid.taamine.domain.ValeurChamp;
import com.walid.taamine.service.ValeurChampService;
import com.walid.taamine.web.rest.util.HeaderUtil;
import com.walid.taamine.web.rest.dto.ValeurChampDTO;
import com.walid.taamine.web.rest.mapper.ValeurChampMapper;
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
 * REST controller for managing ValeurChamp.
 */
@RestController
@RequestMapping("/api")
public class ValeurChampResource {

    private final Logger log = LoggerFactory.getLogger(ValeurChampResource.class);
        
    @Inject
    private ValeurChampService valeurChampService;
    
    @Inject
    private ValeurChampMapper valeurChampMapper;
    
    /**
     * POST  /valeurChamps -> Create a new valeurChamp.
     */
    @RequestMapping(value = "/valeurChamps",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ValeurChampDTO> createValeurChamp(@RequestBody ValeurChampDTO valeurChampDTO) throws URISyntaxException {
        log.debug("REST request to save ValeurChamp : {}", valeurChampDTO);
        if (valeurChampDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("valeurChamp", "idexists", "A new valeurChamp cannot already have an ID")).body(null);
        }
        ValeurChampDTO result = valeurChampService.save(valeurChampDTO);
        return ResponseEntity.created(new URI("/api/valeurChamps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("valeurChamp", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /valeurChamps -> Updates an existing valeurChamp.
     */
    @RequestMapping(value = "/valeurChamps",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ValeurChampDTO> updateValeurChamp(@RequestBody ValeurChampDTO valeurChampDTO) throws URISyntaxException {
        log.debug("REST request to update ValeurChamp : {}", valeurChampDTO);
        if (valeurChampDTO.getId() == null) {
            return createValeurChamp(valeurChampDTO);
        }
        ValeurChampDTO result = valeurChampService.save(valeurChampDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("valeurChamp", valeurChampDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /valeurChamps -> get all the valeurChamps.
     */
    @RequestMapping(value = "/valeurChamps",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<ValeurChampDTO> getAllValeurChamps() {
        log.debug("REST request to get all ValeurChamps");
        return valeurChampService.findAll();
            }
    
    /**
     * GET  /valeurChamps -> get all the valeurChamps.
     */
    @RequestMapping(value = "/valeurChampsDevis/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<ValeurChampDTO> getAllValeurChamps(@PathVariable Long id) {
        log.debug("REST request to get all ValeurChampsDevis id : {}",id);
        return valeurChampService.findAllByDevisId(id);
        }

    /**
     * GET  /valeurChamps/:id -> get the "id" valeurChamp.
     */
    @RequestMapping(value = "/valeurChamps/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ValeurChampDTO> getValeurChamp(@PathVariable Long id) {
        log.debug("REST request to get ValeurChamp : {}", id);
        ValeurChampDTO valeurChampDTO = valeurChampService.findOne(id);
        return Optional.ofNullable(valeurChampDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /valeurChamps/:id -> delete the "id" valeurChamp.
     */
    @RequestMapping(value = "/valeurChamps/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteValeurChamp(@PathVariable Long id) {
        log.debug("REST request to delete ValeurChamp : {}", id);
        valeurChampService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("valeurChamp", id.toString())).build();
    }
}
