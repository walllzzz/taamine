package com.walid.taamine.web.rest;

import com.walid.taamine.Application;
import com.walid.taamine.domain.Proposition;
import com.walid.taamine.repository.PropositionRepository;
import com.walid.taamine.service.PropositionService;
import com.walid.taamine.web.rest.dto.PropositionDTO;
import com.walid.taamine.web.rest.mapper.PropositionMapper;

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
 * Test class for the PropositionResource REST controller.
 *
 * @see PropositionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PropositionResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_PRIX = "AAAAA";
    private static final String UPDATED_PRIX = "BBBBB";

    private static final ZonedDateTime DEFAULT_DATE_PROPOSITION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_DATE_PROPOSITION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_DATE_PROPOSITION_STR = dateTimeFormatter.format(DEFAULT_DATE_PROPOSITION);

    @Inject
    private PropositionRepository propositionRepository;

    @Inject
    private PropositionMapper propositionMapper;

    @Inject
    private PropositionService propositionService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPropositionMockMvc;

    private Proposition proposition;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PropositionResource propositionResource = new PropositionResource();
        ReflectionTestUtils.setField(propositionResource, "propositionService", propositionService);
        ReflectionTestUtils.setField(propositionResource, "propositionMapper", propositionMapper);
        this.restPropositionMockMvc = MockMvcBuilders.standaloneSetup(propositionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        proposition = new Proposition();
        proposition.setPrix(DEFAULT_PRIX);
        proposition.setDateProposition(DEFAULT_DATE_PROPOSITION);
    }

    @Test
    @Transactional
    public void createProposition() throws Exception {
        int databaseSizeBeforeCreate = propositionRepository.findAll().size();

        // Create the Proposition
        PropositionDTO propositionDTO = propositionMapper.propositionToPropositionDTO(proposition);

        restPropositionMockMvc.perform(post("/api/propositions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(propositionDTO)))
                .andExpect(status().isCreated());

        // Validate the Proposition in the database
        List<Proposition> propositions = propositionRepository.findAll();
        assertThat(propositions).hasSize(databaseSizeBeforeCreate + 1);
        Proposition testProposition = propositions.get(propositions.size() - 1);
        assertThat(testProposition.getPrix()).isEqualTo(DEFAULT_PRIX);
        assertThat(testProposition.getDateProposition()).isEqualTo(DEFAULT_DATE_PROPOSITION);
    }

    @Test
    @Transactional
    public void getAllPropositions() throws Exception {
        // Initialize the database
        propositionRepository.saveAndFlush(proposition);

        // Get all the propositions
        restPropositionMockMvc.perform(get("/api/propositions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(proposition.getId().intValue())))
                .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.toString())))
                .andExpect(jsonPath("$.[*].dateProposition").value(hasItem(DEFAULT_DATE_PROPOSITION_STR)));
    }

    @Test
    @Transactional
    public void getProposition() throws Exception {
        // Initialize the database
        propositionRepository.saveAndFlush(proposition);

        // Get the proposition
        restPropositionMockMvc.perform(get("/api/propositions/{id}", proposition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(proposition.getId().intValue()))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.toString()))
            .andExpect(jsonPath("$.dateProposition").value(DEFAULT_DATE_PROPOSITION_STR));
    }

    @Test
    @Transactional
    public void getNonExistingProposition() throws Exception {
        // Get the proposition
        restPropositionMockMvc.perform(get("/api/propositions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProposition() throws Exception {
        // Initialize the database
        propositionRepository.saveAndFlush(proposition);

		int databaseSizeBeforeUpdate = propositionRepository.findAll().size();

        // Update the proposition
        proposition.setPrix(UPDATED_PRIX);
        proposition.setDateProposition(UPDATED_DATE_PROPOSITION);
        PropositionDTO propositionDTO = propositionMapper.propositionToPropositionDTO(proposition);

        restPropositionMockMvc.perform(put("/api/propositions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(propositionDTO)))
                .andExpect(status().isOk());

        // Validate the Proposition in the database
        List<Proposition> propositions = propositionRepository.findAll();
        assertThat(propositions).hasSize(databaseSizeBeforeUpdate);
        Proposition testProposition = propositions.get(propositions.size() - 1);
        assertThat(testProposition.getPrix()).isEqualTo(UPDATED_PRIX);
        assertThat(testProposition.getDateProposition()).isEqualTo(UPDATED_DATE_PROPOSITION);
    }

    @Test
    @Transactional
    public void deleteProposition() throws Exception {
        // Initialize the database
        propositionRepository.saveAndFlush(proposition);

		int databaseSizeBeforeDelete = propositionRepository.findAll().size();

        // Get the proposition
        restPropositionMockMvc.perform(delete("/api/propositions/{id}", proposition.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Proposition> propositions = propositionRepository.findAll();
        assertThat(propositions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
