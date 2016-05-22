package com.walid.taamine.repository;

import com.walid.taamine.domain.ChpListeDeroulante;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ChpListeDeroulante entity.
 */
public interface ChpListeDeroulanteRepository extends JpaRepository<ChpListeDeroulante,Long> {


	List<ChpListeDeroulante> findAllByChampId(Long id);

}
