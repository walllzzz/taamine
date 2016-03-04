package com.walid.taamine.web.rest;

import com.walid.taamine.Application;
import com.walid.taamine.domain.Champ;
import com.walid.taamine.repository.ChampRepository;
import com.walid.taamine.service.ChampService;
import com.walid.taamine.web.rest.dto.ChampDTO;
import com.walid.taamine.web.rest.mapper.ChampMapper;

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
 * Test class for the ChampResource REST controller.
 *
 * @see ChampResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ChampResourceIntTest {

    private static final String DEFAULT_CHP_LIBELLE = "AAAAA";
    private static final String UPDATED_CHP_LIBELLE = "BBBBB";

    private static final Boolean DEFAULT_CHP_DANS_LISTE = false;
    private static final Boolean UPDATED_CHP_DANS_LISTE = true;

    private static final Long DEFAULT_CHP_TYPE = 1L;
    private static final Long UPDATED_CHP_TYPE = 2L;

    @Inject
    private ChampRepository champRepository;

    @Inject
    private ChampMapper champMapper;

    @Inject
    private ChampService champService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restChampMockMvc;

    private Champ champ;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ChampResource champResource = new ChampResource();
        ReflectionTestUtils.setField(champResource, "champService", champService);
        ReflectionTestUtils.setField(champResource, "champMapper", champMapper);
        this.restChampMockMvc = MockMvcBuilders.standaloneSetup(champResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        champ = new Champ();
        champ.setChpLibelle(DEFAULT_CHP_LIBELLE);
        champ.setChpDansListe(DEFAULT_CHP_DANS_LISTE);
        champ.setChpType(DEFAULT_CHP_TYPE);
    }

    @Test
    @Transactional
    public void createChamp() throws Exception {
        int databaseSizeBeforeCreate = champRepository.findAll().size();

        // Create the Champ
        ChampDTO champDTO = champMapper.champToChampDTO(champ);

        restChampMockMvc.perform(post("/api/champs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(champDTO)))
                .andExpect(status().isCreated());

        // Validate the Champ in the database
        List<Champ> champs = champRepository.findAll();
        assertThat(champs).hasSize(databaseSizeBeforeCreate + 1);
        Champ testChamp = champs.get(champs.size() - 1);
        assertThat(testChamp.getChpLibelle()).isEqualTo(DEFAULT_CHP_LIBELLE);
        assertThat(testChamp.getChpDansListe()).isEqualTo(DEFAULT_CHP_DANS_LISTE);
        assertThat(testChamp.getChpType()).isEqualTo(DEFAULT_CHP_TYPE);
    }

    @Test
    @Transactional
    public void getAllChamps() throws Exception {
        // Initialize the database
        champRepository.saveAndFlush(champ);

        // Get all the champs
        restChampMockMvc.perform(get("/api/champs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(champ.getId().intValue())))
                .andExpect(jsonPath("$.[*].chpLibelle").value(hasItem(DEFAULT_CHP_LIBELLE.toString())))
                .andExpect(jsonPath("$.[*].chpDansListe").value(hasItem(DEFAULT_CHP_DANS_LISTE.booleanValue())))
                .andExpect(jsonPath("$.[*].chpType").value(hasItem(DEFAULT_CHP_TYPE.intValue())));
    }

    @Test
    @Transactional
    public void getChamp() throws Exception {
        // Initialize the database
        champRepository.saveAndFlush(champ);

        // Get the champ
        restChampMockMvc.perform(get("/api/champs/{id}", champ.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(champ.getId().intValue()))
            .andExpect(jsonPath("$.chpLibelle").value(DEFAULT_CHP_LIBELLE.toString()))
            .andExpect(jsonPath("$.chpDansListe").value(DEFAULT_CHP_DANS_LISTE.booleanValue()))
            .andExpect(jsonPath("$.chpType").value(DEFAULT_CHP_TYPE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingChamp() throws Exception {
        // Get the champ
        restChampMockMvc.perform(get("/api/champs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChamp() throws Exception {
        // Initialize the database
        champRepository.saveAndFlush(champ);

		int databaseSizeBeforeUpdate = champRepository.findAll().size();

        // Update the champ
        champ.setChpLibelle(UPDATED_CHP_LIBELLE);
        champ.setChpDansListe(UPDATED_CHP_DANS_LISTE);
        champ.setChpType(UPDATED_CHP_TYPE);
        ChampDTO champDTO = champMapper.champToChampDTO(champ);

        restChampMockMvc.perform(put("/api/champs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(champDTO)))
                .andExpect(status().isOk());

        // Validate the Champ in the database
        List<Champ> champs = champRepository.findAll();
        assertThat(champs).hasSize(databaseSizeBeforeUpdate);
        Champ testChamp = champs.get(champs.size() - 1);
        assertThat(testChamp.getChpLibelle()).isEqualTo(UPDATED_CHP_LIBELLE);
        assertThat(testChamp.getChpDansListe()).isEqualTo(UPDATED_CHP_DANS_LISTE);
        assertThat(testChamp.getChpType()).isEqualTo(UPDATED_CHP_TYPE);
    }

    @Test
    @Transactional
    public void deleteChamp() throws Exception {
        // Initialize the database
        champRepository.saveAndFlush(champ);

		int databaseSizeBeforeDelete = champRepository.findAll().size();

        // Get the champ
        restChampMockMvc.perform(delete("/api/champs/{id}", champ.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Champ> champs = champRepository.findAll();
        assertThat(champs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
