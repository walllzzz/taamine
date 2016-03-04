package com.walid.taamine.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.walid.taamine.domain.Champ;
import com.walid.taamine.service.ChampService;
import com.walid.taamine.web.rest.util.HeaderUtil;
import com.walid.taamine.web.rest.dto.ChampDTO;
import com.walid.taamine.web.rest.mapper.ChampMapper;
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
 * REST controller for managing Champ.
 */
@RestController
@RequestMapping("/api")
public class ChampResource {

    private final Logger log = LoggerFactory.getLogger(ChampResource.class);
        
    @Inject
    private ChampService champService;
    
    @Inject
    private ChampMapper champMapper;
    
    /**
     * POST  /champs -> Create a new champ.
     */
    @RequestMapping(value = "/champs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChampDTO> createChamp(@RequestBody ChampDTO champDTO) throws URISyntaxException {
        log.debug("REST request to save Champ : {}", champDTO);
        if (champDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("champ", "idexists", "A new champ cannot already have an ID")).body(null);
        }
        ChampDTO result = champService.save(champDTO);
        return ResponseEntity.created(new URI("/api/champs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("champ", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /champs -> Updates an existing champ.
     */
    @RequestMapping(value = "/champs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChampDTO> updateChamp(@RequestBody ChampDTO champDTO) throws URISyntaxException {
        log.debug("REST request to update Champ : {}", champDTO);
        if (champDTO.getId() == null) {
            return createChamp(champDTO);
        }
        ChampDTO result = champService.save(champDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("champ", champDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /champs -> get all the champs.
     */
    @RequestMapping(value = "/champs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<ChampDTO> getAllChamps() {
        log.debug("REST request to get all Champs");
        return champService.findAll();
            }

    /**
     * GET  /champs/:id -> get the "id" champ.
     */
    @RequestMapping(value = "/champs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChampDTO> getChamp(@PathVariable Long id) {
        log.debug("REST request to get Champ : {}", id);
        ChampDTO champDTO = champService.findOne(id);
        return Optional.ofNullable(champDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /champs/:id -> delete the "id" champ.
     */
    @RequestMapping(value = "/champs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteChamp(@PathVariable Long id) {
        log.debug("REST request to delete Champ : {}", id);
        champService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("champ", id.toString())).build();
    }
}
