package com.walid.taamine.repository;

import com.walid.taamine.domain.TypeDevis;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TypeDevis entity.
 */
public interface TypeDevisRepository extends JpaRepository<TypeDevis,Long> {

}
