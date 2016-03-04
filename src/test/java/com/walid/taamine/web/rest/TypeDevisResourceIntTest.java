package com.walid.taamine.web.rest;

import com.walid.taamine.Application;
import com.walid.taamine.domain.TypeDevis;
import com.walid.taamine.repository.TypeDevisRepository;
import com.walid.taamine.service.TypeDevisService;
import com.walid.taamine.web.rest.dto.TypeDevisDTO;
import com.walid.taamine.web.rest.mapper.TypeDevisMapper;

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
 * Test class for the TypeDevisResource REST controller.
 *
 * @see TypeDevisResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TypeDevisResourceIntTest {

    private static final String DEFAULT_LIBELLE = "AAAAA";
    private static final String UPDATED_LIBELLE = "BBBBB";

    @Inject
    private TypeDevisRepository typeDevisRepository;

    @Inject
    private TypeDevisMapper typeDevisMapper;

    @Inject
    private TypeDevisService typeDevisService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTypeDevisMockMvc;

    private TypeDevis typeDevis;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TypeDevisResource typeDevisResource = new TypeDevisResource();
        ReflectionTestUtils.setField(typeDevisResource, "typeDevisService", typeDevisService);
        ReflectionTestUtils.setField(typeDevisResource, "typeDevisMapper", typeDevisMapper);
        this.restTypeDevisMockMvc = MockMvcBuilders.standaloneSetup(typeDevisResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        typeDevis = new TypeDevis();
        typeDevis.setLibelle(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeDevis() throws Exception {
        int databaseSizeBeforeCreate = typeDevisRepository.findAll().size();

        // Create the TypeDevis
        TypeDevisDTO typeDevisDTO = typeDevisMapper.typeDevisToTypeDevisDTO(typeDevis);

        restTypeDevisMockMvc.perform(post("/api/typeDeviss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(typeDevisDTO)))
                .andExpect(status().isCreated());

        // Validate the TypeDevis in the database
        List<TypeDevis> typeDeviss = typeDevisRepository.findAll();
        assertThat(typeDeviss).hasSize(databaseSizeBeforeCreate + 1);
        TypeDevis testTypeDevis = typeDeviss.get(typeDeviss.size() - 1);
        assertThat(testTypeDevis.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllTypeDeviss() throws Exception {
        // Initialize the database
        typeDevisRepository.saveAndFlush(typeDevis);

        // Get all the typeDeviss
        restTypeDevisMockMvc.perform(get("/api/typeDeviss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(typeDevis.getId().intValue())))
                .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }

    @Test
    @Transactional
    public void getTypeDevis() throws Exception {
        // Initialize the database
        typeDevisRepository.saveAndFlush(typeDevis);

        // Get the typeDevis
        restTypeDevisMockMvc.perform(get("/api/typeDeviss/{id}", typeDevis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(typeDevis.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeDevis() throws Exception {
        // Get the typeDevis
        restTypeDevisMockMvc.perform(get("/api/typeDeviss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeDevis() throws Exception {
        // Initialize the database
        typeDevisRepository.saveAndFlush(typeDevis);

		int databaseSizeBeforeUpdate = typeDevisRepository.findAll().size();

        // Update the typeDevis
        typeDevis.setLibelle(UPDATED_LIBELLE);
        TypeDevisDTO typeDevisDTO = typeDevisMapper.typeDevisToTypeDevisDTO(typeDevis);

        restTypeDevisMockMvc.perform(put("/api/typeDeviss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(typeDevisDTO)))
                .andExpect(status().isOk());

        // Validate the TypeDevis in the database
        List<TypeDevis> typeDeviss = typeDevisRepository.findAll();
        assertThat(typeDeviss).hasSize(databaseSizeBeforeUpdate);
        TypeDevis testTypeDevis = typeDeviss.get(typeDeviss.size() - 1);
        assertThat(testTypeDevis.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void deleteTypeDevis() throws Exception {
        // Initialize the database
        typeDevisRepository.saveAndFlush(typeDevis);

		int databaseSizeBeforeDelete = typeDevisRepository.findAll().size();

        // Get the typeDevis
        restTypeDevisMockMvc.perform(delete("/api/typeDeviss/{id}", typeDevis.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeDevis> typeDeviss = typeDevisRepository.findAll();
        assertThat(typeDeviss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
