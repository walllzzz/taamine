package com.walid.taamine.web.rest;

import com.walid.taamine.Application;
import com.walid.taamine.domain.ChpListeDeroulante;
import com.walid.taamine.repository.ChpListeDeroulanteRepository;
import com.walid.taamine.service.ChpListeDeroulanteService;
import com.walid.taamine.web.rest.dto.ChpListeDeroulanteDTO;
import com.walid.taamine.web.rest.mapper.ChpListeDeroulanteMapper;

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
 * Test class for the ChpListeDeroulanteResource REST controller.
 *
 * @see ChpListeDeroulanteResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ChpListeDeroulanteResourceIntTest {

    private static final String DEFAULT_VALEUR = "AAAAA";
    private static final String UPDATED_VALEUR = "BBBBB";

    @Inject
    private ChpListeDeroulanteRepository chpListeDeroulanteRepository;

    @Inject
    private ChpListeDeroulanteMapper chpListeDeroulanteMapper;

    @Inject
    private ChpListeDeroulanteService chpListeDeroulanteService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restChpListeDeroulanteMockMvc;

    private ChpListeDeroulante chpListeDeroulante;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ChpListeDeroulanteResource chpListeDeroulanteResource = new ChpListeDeroulanteResource();
        ReflectionTestUtils.setField(chpListeDeroulanteResource, "chpListeDeroulanteService", chpListeDeroulanteService);
        ReflectionTestUtils.setField(chpListeDeroulanteResource, "chpListeDeroulanteMapper", chpListeDeroulanteMapper);
        this.restChpListeDeroulanteMockMvc = MockMvcBuilders.standaloneSetup(chpListeDeroulanteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        chpListeDeroulante = new ChpListeDeroulante();
        chpListeDeroulante.setValeur(DEFAULT_VALEUR);
    }

    @Test
    @Transactional
    public void createChpListeDeroulante() throws Exception {
        int databaseSizeBeforeCreate = chpListeDeroulanteRepository.findAll().size();

        // Create the ChpListeDeroulante
        ChpListeDeroulanteDTO chpListeDeroulanteDTO = chpListeDeroulanteMapper.chpListeDeroulanteToChpListeDeroulanteDTO(chpListeDeroulante);

        restChpListeDeroulanteMockMvc.perform(post("/api/chpListeDeroulantes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(chpListeDeroulanteDTO)))
                .andExpect(status().isCreated());

        // Validate the ChpListeDeroulante in the database
        List<ChpListeDeroulante> chpListeDeroulantes = chpListeDeroulanteRepository.findAll();
        assertThat(chpListeDeroulantes).hasSize(databaseSizeBeforeCreate + 1);
        ChpListeDeroulante testChpListeDeroulante = chpListeDeroulantes.get(chpListeDeroulantes.size() - 1);
        assertThat(testChpListeDeroulante.getValeur()).isEqualTo(DEFAULT_VALEUR);
    }

    @Test
    @Transactional
    public void getAllChpListeDeroulantes() throws Exception {
        // Initialize the database
        chpListeDeroulanteRepository.saveAndFlush(chpListeDeroulante);

        // Get all the chpListeDeroulantes
        restChpListeDeroulanteMockMvc.perform(get("/api/chpListeDeroulantes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(chpListeDeroulante.getId().intValue())))
                .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR.toString())));
    }

    @Test
    @Transactional
    public void getChpListeDeroulante() throws Exception {
        // Initialize the database
        chpListeDeroulanteRepository.saveAndFlush(chpListeDeroulante);

        // Get the chpListeDeroulante
        restChpListeDeroulanteMockMvc.perform(get("/api/chpListeDeroulantes/{id}", chpListeDeroulante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(chpListeDeroulante.getId().intValue()))
            .andExpect(jsonPath("$.valeur").value(DEFAULT_VALEUR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChpListeDeroulante() throws Exception {
        // Get the chpListeDeroulante
        restChpListeDeroulanteMockMvc.perform(get("/api/chpListeDeroulantes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChpListeDeroulante() throws Exception {
        // Initialize the database
        chpListeDeroulanteRepository.saveAndFlush(chpListeDeroulante);

		int databaseSizeBeforeUpdate = chpListeDeroulanteRepository.findAll().size();

        // Update the chpListeDeroulante
        chpListeDeroulante.setValeur(UPDATED_VALEUR);
        ChpListeDeroulanteDTO chpListeDeroulanteDTO = chpListeDeroulanteMapper.chpListeDeroulanteToChpListeDeroulanteDTO(chpListeDeroulante);

        restChpListeDeroulanteMockMvc.perform(put("/api/chpListeDeroulantes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(chpListeDeroulanteDTO)))
                .andExpect(status().isOk());

        // Validate the ChpListeDeroulante in the database
        List<ChpListeDeroulante> chpListeDeroulantes = chpListeDeroulanteRepository.findAll();
        assertThat(chpListeDeroulantes).hasSize(databaseSizeBeforeUpdate);
        ChpListeDeroulante testChpListeDeroulante = chpListeDeroulantes.get(chpListeDeroulantes.size() - 1);
        assertThat(testChpListeDeroulante.getValeur()).isEqualTo(UPDATED_VALEUR);
    }

    @Test
    @Transactional
    public void deleteChpListeDeroulante() throws Exception {
        // Initialize the database
        chpListeDeroulanteRepository.saveAndFlush(chpListeDeroulante);

		int databaseSizeBeforeDelete = chpListeDeroulanteRepository.findAll().size();

        // Get the chpListeDeroulante
        restChpListeDeroulanteMockMvc.perform(delete("/api/chpListeDeroulantes/{id}", chpListeDeroulante.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ChpListeDeroulante> chpListeDeroulantes = chpListeDeroulanteRepository.findAll();
        assertThat(chpListeDeroulantes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
