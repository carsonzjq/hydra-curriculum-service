package com.revature.hydra.curriculum.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Curriculum;

/**
 * CurriculumRepository Data Access Object with various methods to communicate
 * with database
 *
 */
@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	/**
	 * Find a certain curriculum with specific curriculumId
	 * 
	 * @param curriculumId
	 * @return curriculum with given curriculumId
	 */
	Curriculum findOneByCurriculumId(Integer curriculumId);

	/**
	 * Find all Curriculums.
	 * 
	 * @return List of Curriculums
	 */
	List<Curriculum> findAll();

}
