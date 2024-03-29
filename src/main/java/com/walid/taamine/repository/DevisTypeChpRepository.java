package com.walid.taamine.repository;

import com.walid.taamine.domain.DevisTypeChp;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DevisTypeChp entity.
 */
public interface DevisTypeChpRepository extends JpaRepository<DevisTypeChp,Long> {
	/**
	 * get DevisTypeChamsp by Devis Type
	 * @param idDevisType
	 * @return
	 */
	public List<DevisTypeChp> findAllByDevisTypeId(Long idDevisType);
}
