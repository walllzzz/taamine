package com.walid.taamine.repository;

import com.walid.taamine.domain.Devis;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Devis entity.
 */
public interface DevisRepository extends JpaRepository<Devis,Long> {

    @Query("select devis from Devis devis where devis.user.login = ?#{principal.username}")
    List<Devis> findByUserIsCurrentUser();
    /**
     * 
     * @param id
     * @return
     */
    List<Devis> findAllByUserId(Long id);
}
