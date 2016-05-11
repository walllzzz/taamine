package com.walid.taamine.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.walid.taamine.domain.Devis;
import com.walid.taamine.service.DevisService;
import com.walid.taamine.web.rest.util.HeaderUtil;
import com.walid.taamine.web.rest.dto.DevisDTO;
import com.walid.taamine.web.rest.mapper.DevisMapper;
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
 * REST controller for managing Devis.
 */
@RestController
@RequestMapping("/api")
public class DevisResource {

    private final Logger log = LoggerFactory.getLogger(DevisResource.class);
        
    @Inject
    private DevisService devisService;
    
    @Inject
    private DevisMapper devisMapper;
    
    /**
     * POST  /deviss -> Create a new devis.
     */
    @RequestMapping(value = "/deviss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DevisDTO> createDevis(@RequestBody DevisDTO devisDTO) throws URISyntaxException {
        log.debug("REST request to save Devis : {}", devisDTO);
        if (devisDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("devis", "idexists", "A new devis cannot already have an ID")).body(null);
        }
        DevisDTO result = devisService.save(devisDTO);
        return ResponseEntity.created(new URI("/api/deviss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("devis", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /deviss -> Updates an existing devis.
     */
    @RequestMapping(value = "/deviss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DevisDTO> updateDevis(@RequestBody DevisDTO devisDTO) throws URISyntaxException {
        log.debug("REST request to update Devis : {}", devisDTO);
        if (devisDTO.getId() == null) {
            return createDevis(devisDTO);
        }
        DevisDTO result = devisService.save(devisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("devis", devisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /deviss -> get all the deviss.
     */
    @RequestMapping(value = "/deviss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<DevisDTO> getAllDeviss() {
        log.debug("REST request to get all Deviss");
        return devisService.findAll();
            }
    

    /**
     * GET  /deviss -> get all the deviss of user.
     */
    @RequestMapping(value = "/mesdevis/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<DevisDTO> getMesDevis(@PathVariable Long id) {
        log.debug("REST request to get all Deviss by user id:  {}",id);
        return devisService.findAllByUserId(id);
            }

    /**
     * GET  /deviss/:id -> get the "id" devis.
     */
    @RequestMapping(value = "/deviss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DevisDTO> getDevis(@PathVariable Long id) {
        log.debug("REST request to get Devis : {}", id);
        DevisDTO devisDTO = devisService.findOne(id);
        return Optional.ofNullable(devisDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /deviss/:id -> delete the "id" devis.
     */
    @RequestMapping(value = "/deviss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        log.debug("REST request to delete Devis : {}", id);
        devisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("devis", id.toString())).build();
    }
}
