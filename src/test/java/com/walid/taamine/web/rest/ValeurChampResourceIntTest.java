package com.walid.taamine.web.rest;

import com.walid.taamine.Application;
import com.walid.taamine.domain.ValeurChamp;
import com.walid.taamine.repository.ValeurChampRepository;
import com.walid.taamine.service.ValeurChampService;
import com.walid.taamine.web.rest.dto.ValeurChampDTO;
import com.walid.taamine.web.rest.mapper.ValeurChampMapper;

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
 * Test class for the ValeurChampResource REST controller.
 *
 * @see ValeurChampResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ValeurChampResourceIntTest {

    private static final String DEFAULT_VAL_VALEUR = "AAAAA";
    private static final String UPDATED_VAL_VALEUR = "BBBBB";

    @Inject
    private ValeurChampRepository valeurChampRepository;

    @Inject
    private ValeurChampMapper valeurChampMapper;

    @Inject
    private ValeurChampService valeurChampService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restValeurChampMockMvc;

    private ValeurChamp valeurChamp;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ValeurChampResource valeurChampResource = new ValeurChampResource();
        ReflectionTestUtils.setField(valeurChampResource, "valeurChampService", valeurChampService);
        ReflectionTestUtils.setField(valeurChampResource, "valeurChampMapper", valeurChampMapper);
        this.restValeurChampMockMvc = MockMvcBuilders.standaloneSetup(valeurChampResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        valeurChamp = new ValeurChamp();
        valeurChamp.setValValeur(DEFAULT_VAL_VALEUR);
    }

    @Test
    @Transactional
    public void createValeurChamp() throws Exception {
        int databaseSizeBeforeCreate = valeurChampRepository.findAll().size();

        // Create the ValeurChamp
        ValeurChampDTO valeurChampDTO = valeurChampMapper.valeurChampToValeurChampDTO(valeurChamp);

        restValeurChampMockMvc.perform(post("/api/valeurChamps")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(valeurChampDTO)))
                .andExpect(status().isCreated());

        // Validate the ValeurChamp in the database
        List<ValeurChamp> valeurChamps = valeurChampRepository.findAll();
        assertThat(valeurChamps).hasSize(databaseSizeBeforeCreate + 1);
        ValeurChamp testValeurChamp = valeurChamps.get(valeurChamps.size() - 1);
        assertThat(testValeurChamp.getValValeur()).isEqualTo(DEFAULT_VAL_VALEUR);
    }

    @Test
    @Transactional
    public void getAllValeurChamps() throws Exception {
        // Initialize the database
        valeurChampRepository.saveAndFlush(valeurChamp);

        // Get all the valeurChamps
        restValeurChampMockMvc.perform(get("/api/valeurChamps?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(valeurChamp.getId().intValue())))
                .andExpect(jsonPath("$.[*].valValeur").value(hasItem(DEFAULT_VAL_VALEUR.toString())));
    }

    @Test
    @Transactional
    public void getValeurChamp() throws Exception {
        // Initialize the database
        valeurChampRepository.saveAndFlush(valeurChamp);

        // Get the valeurChamp
        restValeurChampMockMvc.perform(get("/api/valeurChamps/{id}", valeurChamp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(valeurChamp.getId().intValue()))
            .andExpect(jsonPath("$.valValeur").value(DEFAULT_VAL_VALEUR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingValeurChamp() throws Exception {
        // Get the valeurChamp
        restValeurChampMockMvc.perform(get("/api/valeurChamps/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValeurChamp() throws Exception {
        // Initialize the database
        valeurChampRepository.saveAndFlush(valeurChamp);

		int databaseSizeBeforeUpdate = valeurChampRepository.findAll().size();

        // Update the valeurChamp
        valeurChamp.setValValeur(UPDATED_VAL_VALEUR);
        ValeurChampDTO valeurChampDTO = valeurChampMapper.valeurChampToValeurChampDTO(valeurChamp);

        restValeurChampMockMvc.perform(put("/api/valeurChamps")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(valeurChampDTO)))
                .andExpect(status().isOk());

        // Validate the ValeurChamp in the database
        List<ValeurChamp> valeurChamps = valeurChampRepository.findAll();
        assertThat(valeurChamps).hasSize(databaseSizeBeforeUpdate);
        ValeurChamp testValeurChamp = valeurChamps.get(valeurChamps.size() - 1);
        assertThat(testValeurChamp.getValValeur()).isEqualTo(UPDATED_VAL_VALEUR);
    }

    @Test
    @Transactional
    public void deleteValeurChamp() throws Exception {
        // Initialize the database
        valeurChampRepository.saveAndFlush(valeurChamp);

		int databaseSizeBeforeDelete = valeurChampRepository.findAll().size();

        // Get the valeurChamp
        restValeurChampMockMvc.perform(delete("/api/valeurChamps/{id}", valeurChamp.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ValeurChamp> valeurChamps = valeurChampRepository.findAll();
        assertThat(valeurChamps).hasSize(databaseSizeBeforeDelete - 1);
    }
}
