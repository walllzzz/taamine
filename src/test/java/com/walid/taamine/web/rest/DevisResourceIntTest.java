package com.walid.taamine.web.rest;

import com.walid.taamine.Application;
import com.walid.taamine.domain.Devis;
import com.walid.taamine.repository.DevisRepository;
import com.walid.taamine.service.DevisService;
import com.walid.taamine.web.rest.dto.DevisDTO;
import com.walid.taamine.web.rest.mapper.DevisMapper;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DevisResource REST controller.
 *
 * @see DevisResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DevisResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_DATE_DEVIS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_DATE_DEVIS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_DATE_DEVIS_STR = dateTimeFormatter.format(DEFAULT_DATE_DEVIS);

    @Inject
    private DevisRepository devisRepository;

    @Inject
    private DevisMapper devisMapper;

    @Inject
    private DevisService devisService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDevisMockMvc;

    private Devis devis;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DevisResource devisResource = new DevisResource();
        ReflectionTestUtils.setField(devisResource, "devisService", devisService);
        ReflectionTestUtils.setField(devisResource, "devisMapper", devisMapper);
        this.restDevisMockMvc = MockMvcBuilders.standaloneSetup(devisResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        devis = new Devis();
        devis.setDateDevis(DEFAULT_DATE_DEVIS);
    }

    @Test
    @Transactional
    public void createDevis() throws Exception {
        int databaseSizeBeforeCreate = devisRepository.findAll().size();

        // Create the Devis
        DevisDTO devisDTO = devisMapper.devisToDevisDTO(devis);

        restDevisMockMvc.perform(post("/api/deviss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(devisDTO)))
                .andExpect(status().isCreated());

        // Validate the Devis in the database
        List<Devis> deviss = devisRepository.findAll();
        assertThat(deviss).hasSize(databaseSizeBeforeCreate + 1);
        Devis testDevis = deviss.get(deviss.size() - 1);
        assertThat(testDevis.getDateDevis()).isEqualTo(DEFAULT_DATE_DEVIS);
    }

    @Test
    @Transactional
    public void getAllDeviss() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

        // Get all the deviss
        restDevisMockMvc.perform(get("/api/deviss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(devis.getId().intValue())))
                .andExpect(jsonPath("$.[*].dateDevis").value(hasItem(DEFAULT_DATE_DEVIS_STR)));
    }

    @Test
    @Transactional
    public void getDevis() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

        // Get the devis
        restDevisMockMvc.perform(get("/api/deviss/{id}", devis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(devis.getId().intValue()))
            .andExpect(jsonPath("$.dateDevis").value(DEFAULT_DATE_DEVIS_STR));
    }

    @Test
    @Transactional
    public void getNonExistingDevis() throws Exception {
        // Get the devis
        restDevisMockMvc.perform(get("/api/deviss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevis() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

		int databaseSizeBeforeUpdate = devisRepository.findAll().size();

        // Update the devis
        devis.setDateDevis(UPDATED_DATE_DEVIS);
        DevisDTO devisDTO = devisMapper.devisToDevisDTO(devis);

        restDevisMockMvc.perform(put("/api/deviss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(devisDTO)))
                .andExpect(status().isOk());

        // Validate the Devis in the database
        List<Devis> deviss = devisRepository.findAll();
        assertThat(deviss).hasSize(databaseSizeBeforeUpdate);
        Devis testDevis = deviss.get(deviss.size() - 1);
        assertThat(testDevis.getDateDevis()).isEqualTo(UPDATED_DATE_DEVIS);
    }

    @Test
    @Transactional
    public void deleteDevis() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

		int databaseSizeBeforeDelete = devisRepository.findAll().size();

        // Get the devis
        restDevisMockMvc.perform(delete("/api/deviss/{id}", devis.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Devis> deviss = devisRepository.findAll();
        assertThat(deviss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
