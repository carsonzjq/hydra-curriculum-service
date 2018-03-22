package com.revature.hydra.curriculum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Curriculum;
import com.revature.hydra.curriculum.data.CurriculumRepository;

/**
 * CurriculumService
 * 
 * Curriculum services implementation
 * 
 * @author JIAQI ZHANG
 *
 */
@Service
public class CurriculumService {

	@Autowired
	CurriculumRepository curriculumRepository;

	/**
	 * Save a curriculum
	 * 
	 * @param curriculum
	 */
	public Curriculum save(Curriculum curriculum) {
		return curriculumRepository.save(curriculum);
	}

	/**
	 * Update a curriculum
	 * 
	 * @param curriculum
	 */
	public void update(Curriculum curriculum) {
		save(curriculum);
	}

	/**
	 * delete a curriculum
	 * 
	 * @param curriculum
	 */
	public void delete(Curriculum curriculum) {
		curriculumRepository.delete(curriculum);
	}

	/**
	 * Obtain list of all curriculums from curriculumRepository
	 * 
	 * @return List<Curriculum> - List of All Curriculums
	 */
	public List<Curriculum> findAll() {
		return curriculumRepository.findAll();
	}

	/**
	 * Obtain a curriculum from curriculumRepository with given curriculumId
	 * 
	 * @param curriculumId
	 * @return Curriculum - The curriculum with given curriculumId
	 */
	public Curriculum findOneById(Integer curriculumId) {
		return curriculumRepository.findOneByCurriculumId(curriculumId);
	}

}
