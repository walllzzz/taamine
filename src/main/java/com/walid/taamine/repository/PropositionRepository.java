package com.walid.taamine.repository;

import com.walid.taamine.domain.Proposition;
import com.walid.taamine.web.rest.dto.PropositionDTO;

import org.springframework.data.jpa.repository.*;

import java.util.Collection;
import java.util.List;

/**
 * Spring Data JPA repository for the Proposition entity.
 */
public interface PropositionRepository extends JpaRepository<Proposition,Long> {

    @Query("select proposition from Proposition proposition where proposition.entreprise.login = ?#{principal.username}")
    List<Proposition> findByEntrepriseIsCurrentUser();

	List<Proposition> findAllByDevisId(Long id);
	
	@Query("select proposition from Proposition proposition where proposition.devis.user.id = ?1")
	List<Proposition> findAllByUserId(Long id);

	@Query("select proposition from Proposition proposition where proposition.entreprise.id = ?1")
	List<Proposition> findAllByCompanyId(Long id);
}
