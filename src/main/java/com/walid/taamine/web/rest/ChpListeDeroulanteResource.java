package com.walid.taamine.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.walid.taamine.domain.ChpListeDeroulante;
import com.walid.taamine.service.ChpListeDeroulanteService;
import com.walid.taamine.web.rest.util.HeaderUtil;
import com.walid.taamine.web.rest.dto.ChpListeDeroulanteDTO;
import com.walid.taamine.web.rest.mapper.ChpListeDeroulanteMapper;
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
 * REST controller for managing ChpListeDeroulante.
 */
@RestController
@RequestMapping("/api")
public class ChpListeDeroulanteResource {

    private final Logger log = LoggerFactory.getLogger(ChpListeDeroulanteResource.class);
        
    @Inject
    private ChpListeDeroulanteService chpListeDeroulanteService;
    
    @Inject
    private ChpListeDeroulanteMapper chpListeDeroulanteMapper;
    
    /**
     * POST  /chpListeDeroulantes -> Create a new chpListeDeroulante.
     */
    @RequestMapping(value = "/chpListeDeroulantes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChpListeDeroulanteDTO> createChpListeDeroulante(@RequestBody ChpListeDeroulanteDTO chpListeDeroulanteDTO) throws URISyntaxException {
        log.debug("REST request to save ChpListeDeroulante : {}", chpListeDeroulanteDTO);
        if (chpListeDeroulanteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("chpListeDeroulante", "idexists", "A new chpListeDeroulante cannot already have an ID")).body(null);
        }
        ChpListeDeroulanteDTO result = chpListeDeroulanteService.save(chpListeDeroulanteDTO);
        return ResponseEntity.created(new URI("/api/chpListeDeroulantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("chpListeDeroulante", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chpListeDeroulantes -> Updates an existing chpListeDeroulante.
     */
    @RequestMapping(value = "/chpListeDeroulantes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChpListeDeroulanteDTO> updateChpListeDeroulante(@RequestBody ChpListeDeroulanteDTO chpListeDeroulanteDTO) throws URISyntaxException {
        log.debug("REST request to update ChpListeDeroulante : {}", chpListeDeroulanteDTO);
        if (chpListeDeroulanteDTO.getId() == null) {
            return createChpListeDeroulante(chpListeDeroulanteDTO);
        }
        ChpListeDeroulanteDTO result = chpListeDeroulanteService.save(chpListeDeroulanteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("chpListeDeroulante", chpListeDeroulanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chpListeDeroulantes -> get all the chpListeDeroulantes.
     */
    @RequestMapping(value = "/chpListeDeroulantes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<ChpListeDeroulanteDTO> getAllChpListeDeroulantes() {
        log.debug("REST request to get all ChpListeDeroulantes");
        return chpListeDeroulanteService.findAll();
            }

    /**
     * GET  /chpListeDeroulantes/:id -> get the "id" chpListeDeroulante.
     */
    @RequestMapping(value = "/chpListeDeroulantes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChpListeDeroulanteDTO> getChpListeDeroulante(@PathVariable Long id) {
        log.debug("REST request to get ChpListeDeroulante : {}", id);
        ChpListeDeroulanteDTO chpListeDeroulanteDTO = chpListeDeroulanteService.findOne(id);
        return Optional.ofNullable(chpListeDeroulanteDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /chpListeDeroulantes/:id -> delete the "id" chpListeDeroulante.
     */
    @RequestMapping(value = "/chpListeDeroulantes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteChpListeDeroulante(@PathVariable Long id) {
        log.debug("REST request to delete ChpListeDeroulante : {}", id);
        chpListeDeroulanteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("chpListeDeroulante", id.toString())).build();
    }
}
