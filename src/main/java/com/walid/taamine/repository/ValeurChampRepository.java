package com.walid.taamine.repository;

import com.walid.taamine.domain.ValeurChamp;
import com.walid.taamine.web.rest.dto.ValeurChampDTO;

import org.springframework.data.jpa.repository.*;

import java.util.Collection;
import java.util.List;

/**
 * Spring Data JPA repository for the ValeurChamp entity.
 */
public interface ValeurChampRepository extends JpaRepository<ValeurChamp,Long> {
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<ValeurChamp> findAllByDevisId(Long id);

}
