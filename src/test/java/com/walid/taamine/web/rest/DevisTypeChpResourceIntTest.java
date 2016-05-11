package com.walid.taamine.web.rest;

import com.walid.taamine.Application;
import com.walid.taamine.domain.DevisTypeChp;
import com.walid.taamine.domain.TypeDevis;
import com.walid.taamine.repository.DevisTypeChpRepository;
import com.walid.taamine.repository.TypeDevisRepository;
import com.walid.taamine.service.DevisTypeChpService;
import com.walid.taamine.service.TypeDevisService;
import com.walid.taamine.web.rest.dto.DevisTypeChpDTO;
import com.walid.taamine.web.rest.mapper.DevisTypeChpMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DevisTypeChpResource REST controller.
 *
 * @see DevisTypeChpResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DevisTypeChpResourceIntTest {


    private static final Boolean DEFAULT_ACTIF = false;
    private static final Boolean UPDATED_ACTIF = true;

    private static final Boolean DEFAULT_OBLIGATOIRE = false;
    private static final Boolean UPDATED_OBLIGATOIRE = true;

    @Inject
    private DevisTypeChpRepository devisTypeChpRepository;

    @Inject
    private DevisTypeChpMapper devisTypeChpMapper;

    @Inject
    private DevisTypeChpService devisTypeChpService;
    
    @Inject
    private TypeDevisRepository typeDevisRepository;
    
    @Inject
    private TypeDevisService typeDevisService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDevisTypeChpMockMvc;

    private DevisTypeChp devisTypeChp;
    private TypeDevis typeDevis;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DevisTypeChpResource devisTypeChpResource = new DevisTypeChpResource();
        ReflectionTestUtils.setField(devisTypeChpResource, "devisTypeChpService", devisTypeChpService);
        ReflectionTestUtils.setField(devisTypeChpResource, "devisTypeChpMapper", devisTypeChpMapper);
        this.restDevisTypeChpMockMvc = MockMvcBuilders.standaloneSetup(devisTypeChpResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        devisTypeChp = new DevisTypeChp();
        devisTypeChp.setActif(DEFAULT_ACTIF);
        devisTypeChp.setObligatoire(DEFAULT_OBLIGATOIRE);
        /** init type devis*/
        typeDevis= new TypeDevis();
        typeDevis.setLibelle("Assure Auto");
        typeDevis.setId(1L);
        devisTypeChp.setDevisType(typeDevis);
        
    }

    @Test
    @Transactional
    public void createDevisTypeChp() throws Exception {
        int databaseSizeBeforeCreate = devisTypeChpRepository.findAll().size();

        // Create the DevisTypeChp
        DevisTypeChpDTO devisTypeChpDTO = devisTypeChpMapper.devisTypeChpToDevisTypeChpDTO(devisTypeChp);

        restDevisTypeChpMockMvc.perform(post("/api/devisTypeChps")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(devisTypeChpDTO)))
                .andExpect(status().isCreated());

        // Validate the DevisTypeChp in the database
        List<DevisTypeChp> devisTypeChps = devisTypeChpRepository.findAll();
        assertThat(devisTypeChps).hasSize(databaseSizeBeforeCreate + 1);
        DevisTypeChp testDevisTypeChp = devisTypeChps.get(devisTypeChps.size() - 1);
        assertThat(testDevisTypeChp.getActif()).isEqualTo(DEFAULT_ACTIF);
        assertThat(testDevisTypeChp.getObligatoire()).isEqualTo(DEFAULT_OBLIGATOIRE);
    }

    @Test
    @Transactional
    public void getAllDevisTypeChps() throws Exception {
        // Initialize the database
        devisTypeChpRepository.saveAndFlush(devisTypeChp);

        // Get all the devisTypeChps
        restDevisTypeChpMockMvc.perform(get("/api/devisTypeChps?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(devisTypeChp.getId().intValue())))
                .andExpect(jsonPath("$.[*].actif").value(hasItem(DEFAULT_ACTIF.booleanValue())))
                .andExpect(jsonPath("$.[*].obligatoire").value(hasItem(DEFAULT_OBLIGATOIRE.booleanValue())));
    }
    @Test
    @Transactional
    public void getAllDevisTypeChpsByDevisType() throws Exception {
        // Initialize the database
        devisTypeChpRepository.saveAndFlush(devisTypeChp);

        // Get all the devisTypeChps
        restDevisTypeChpMockMvc.perform(get("/api/devisTypeChps/devisType/{id}",devisTypeChp.getDevisType().getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(devisTypeChp.getId().intValue())))
                .andExpect(jsonPath("$.[*].actif").value(hasItem(DEFAULT_ACTIF.booleanValue())))
                .andExpect(jsonPath("$.[*].obligatoire").value(hasItem(DEFAULT_OBLIGATOIRE.booleanValue())));
    }

    @Test
    @Transactional
    public void getDevisTypeChp() throws Exception {
        // Initialize the database
    	
        devisTypeChpRepository.saveAndFlush(devisTypeChp);

        // Get the devisTypeChp
        restDevisTypeChpMockMvc.perform(get("/api/devisTypeChps/{id}", devisTypeChp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(devisTypeChp.getId().intValue()))
            .andExpect(jsonPath("$.actif").value(DEFAULT_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.obligatoire").value(DEFAULT_OBLIGATOIRE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDevisTypeChp() throws Exception {
        // Get the devisTypeChp
        restDevisTypeChpMockMvc.perform(get("/api/devisTypeChps/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevisTypeChp() throws Exception {
        // Initialize the database
        devisTypeChpRepository.saveAndFlush(devisTypeChp);

		int databaseSizeBeforeUpdate = devisTypeChpRepository.findAll().size();

        // Update the devisTypeChp
        devisTypeChp.setActif(UPDATED_ACTIF);
        devisTypeChp.setObligatoire(UPDATED_OBLIGATOIRE);
        DevisTypeChpDTO devisTypeChpDTO = devisTypeChpMapper.devisTypeChpToDevisTypeChpDTO(devisTypeChp);

        restDevisTypeChpMockMvc.perform(put("/api/devisTypeChps")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(devisTypeChpDTO)))
                .andExpect(status().isOk());

        // Validate the DevisTypeChp in the database
        List<DevisTypeChp> devisTypeChps = devisTypeChpRepository.findAll();
        assertThat(devisTypeChps).hasSize(databaseSizeBeforeUpdate);
        DevisTypeChp testDevisTypeChp = devisTypeChps.get(devisTypeChps.size() - 1);
        assertThat(testDevisTypeChp.getActif()).isEqualTo(UPDATED_ACTIF);
        assertThat(testDevisTypeChp.getObligatoire()).isEqualTo(UPDATED_OBLIGATOIRE);
    }

    @Test
    @Transactional
    public void deleteDevisTypeChp() throws Exception {
        // Initialize the database
        devisTypeChpRepository.saveAndFlush(devisTypeChp);

		int databaseSizeBeforeDelete = devisTypeChpRepository.findAll().size();

        // Get the devisTypeChp
        restDevisTypeChpMockMvc.perform(delete("/api/devisTypeChps/{id}", devisTypeChp.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DevisTypeChp> devisTypeChps = devisTypeChpRepository.findAll();
        assertThat(devisTypeChps).hasSize(databaseSizeBeforeDelete - 1);
    }
}
