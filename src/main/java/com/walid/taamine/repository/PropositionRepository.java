package com.walid.taamine.repository;

import com.walid.taamine.domain.Proposition;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Proposition entity.
 */
public interface PropositionRepository extends JpaRepository<Proposition,Long> {

    @Query("select proposition from Proposition proposition where proposition.entreprise.login = ?#{principal.username}")
    List<Proposition> findByEntrepriseIsCurrentUser();

}
