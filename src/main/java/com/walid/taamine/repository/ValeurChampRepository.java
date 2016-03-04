package com.walid.taamine.repository;

import com.walid.taamine.domain.ValeurChamp;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ValeurChamp entity.
 */
public interface ValeurChampRepository extends JpaRepository<ValeurChamp,Long> {

}
