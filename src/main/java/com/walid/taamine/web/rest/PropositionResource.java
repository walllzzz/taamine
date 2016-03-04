package com.walid.taamine.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.walid.taamine.domain.Proposition;
import com.walid.taamine.service.PropositionService;
import com.walid.taamine.web.rest.util.HeaderUtil;
import com.walid.taamine.web.rest.dto.PropositionDTO;
import com.walid.taamine.web.rest.mapper.PropositionMapper;
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
 * REST controller for managing Proposition.
 */
@RestController
@RequestMapping("/api")
public class PropositionResource {

    private final Logger log = LoggerFactory.getLogger(PropositionResource.class);
        
    @Inject
    private PropositionService propositionService;
    
    @Inject
    private PropositionMapper propositionMapper;
    
    /**
     * POST  /propositions -> Create a new proposition.
     */
    @RequestMapping(value = "/propositions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PropositionDTO> createProposition(@RequestBody PropositionDTO propositionDTO) throws URISyntaxException {
        log.debug("REST request to save Proposition : {}", propositionDTO);
        if (propositionDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("proposition", "idexists", "A new proposition cannot already have an ID")).body(null);
        }
        PropositionDTO result = propositionService.save(propositionDTO);
        return ResponseEntity.created(new URI("/api/propositions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("proposition", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /propositions -> Updates an existing proposition.
     */
    @RequestMapping(value = "/propositions",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PropositionDTO> updateProposition(@RequestBody PropositionDTO propositionDTO) throws URISyntaxException {
        log.debug("REST request to update Proposition : {}", propositionDTO);
        if (propositionDTO.getId() == null) {
            return createProposition(propositionDTO);
        }
        PropositionDTO result = propositionService.save(propositionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("proposition", propositionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /propositions -> get all the propositions.
     */
    @RequestMapping(value = "/propositions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<PropositionDTO> getAllPropositions() {
        log.debug("REST request to get all Propositions");
        return propositionService.findAll();
            }

    /**
     * GET  /propositions/:id -> get the "id" proposition.
     */
    @RequestMapping(value = "/propositions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PropositionDTO> getProposition(@PathVariable Long id) {
        log.debug("REST request to get Proposition : {}", id);
        PropositionDTO propositionDTO = propositionService.findOne(id);
        return Optional.ofNullable(propositionDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /propositions/:id -> delete the "id" proposition.
     */
    @RequestMapping(value = "/propositions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProposition(@PathVariable Long id) {
        log.debug("REST request to delete Proposition : {}", id);
        propositionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("proposition", id.toString())).build();
    }
}
