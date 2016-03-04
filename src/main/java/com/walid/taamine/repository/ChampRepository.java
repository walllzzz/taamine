package com.walid.taamine.repository;

import com.walid.taamine.domain.Champ;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Champ entity.
 */
public interface ChampRepository extends JpaRepository<Champ,Long> {

}
