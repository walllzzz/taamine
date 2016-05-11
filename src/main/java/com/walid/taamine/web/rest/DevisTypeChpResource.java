package com.walid.taamine.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.walid.taamine.domain.DevisTypeChp;
import com.walid.taamine.service.DevisTypeChpService;
import com.walid.taamine.web.rest.util.HeaderUtil;
import com.walid.taamine.web.rest.dto.DevisTypeChpDTO;
import com.walid.taamine.web.rest.mapper.DevisTypeChpMapper;
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
 * REST controller for managing DevisTypeChp.
 */
@RestController
@RequestMapping("/api")
public class DevisTypeChpResource {

    private final Logger log = LoggerFactory.getLogger(DevisTypeChpResource.class);
        
    @Inject
    private DevisTypeChpService devisTypeChpService;
    
    @Inject
    private DevisTypeChpMapper devisTypeChpMapper;
    
    /**
     * POST  /devisTypeChps -> Create a new devisTypeChp.
     */
    @RequestMapping(value = "/devisTypeChps",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DevisTypeChpDTO> createDevisTypeChp(@RequestBody DevisTypeChpDTO devisTypeChpDTO) throws URISyntaxException {
        log.debug("REST request to save DevisTypeChp : {}", devisTypeChpDTO);
        if (devisTypeChpDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("devisTypeChp", "idexists", "A new devisTypeChp cannot already have an ID")).body(null);
        }
        DevisTypeChpDTO result = devisTypeChpService.save(devisTypeChpDTO);
        return ResponseEntity.created(new URI("/api/devisTypeChps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("devisTypeChp", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /devisTypeChps -> Updates an existing devisTypeChp.
     */
    @RequestMapping(value = "/devisTypeChps",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DevisTypeChpDTO> updateDevisTypeChp(@RequestBody DevisTypeChpDTO devisTypeChpDTO) throws URISyntaxException {
        log.debug("REST request to update DevisTypeChp : {}", devisTypeChpDTO);
        if (devisTypeChpDTO.getId() == null) {
            return createDevisTypeChp(devisTypeChpDTO);
        }
        DevisTypeChpDTO result = devisTypeChpService.save(devisTypeChpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("devisTypeChp", devisTypeChpDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /devisTypeChps -> get all the devisTypeChps.
     */
    @RequestMapping(value = "/devisTypeChps",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<DevisTypeChpDTO> getAllDevisTypeChps() {
        log.debug("REST request to get all DevisTypeChps");
        return devisTypeChpService.findAll();
            }
    /**
     * GET  /devisTypeChps -> get all the devisTypeChps.
     */
    @RequestMapping(value = "/devisTypeChps/devisType/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<DevisTypeChpDTO> getAllDevisTypeChps(@PathVariable Long id) {
        log.debug("REST request to get all DevisTypeChps by DevisType : {}", id);
        return devisTypeChpService.findByTypeDevis(id);
            }

    /**
     * GET  /devisTypeChps/:id -> get the "id" devisTypeChp.
     */
    @RequestMapping(value = "/devisTypeChps/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DevisTypeChpDTO> getDevisTypeChp(@PathVariable Long id) {
        log.debug("REST request to get DevisTypeChp : {}", id);
        DevisTypeChpDTO devisTypeChpDTO = devisTypeChpService.findOne(id);
        return Optional.ofNullable(devisTypeChpDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /devisTypeChps/:id -> delete the "id" devisTypeChp.
     */
    @RequestMapping(value = "/devisTypeChps/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDevisTypeChp(@PathVariable Long id) {
        log.debug("REST request to delete DevisTypeChp : {}", id);
        devisTypeChpService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("devisTypeChp", id.toString())).build();
    }
}
