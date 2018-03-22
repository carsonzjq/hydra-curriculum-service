package com.revature.hydra.curriculum.controller;

import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Curriculum;
import com.revature.hydra.curriculum.service.CurriculumService;

/**
 * CurriculumController where curriculum service is exposed.
 * 
 * @author JIAQI ZHANG
 *
 */
@RestController
@CrossOrigin
@EnableAutoConfiguration
public class CurriculumController {

	private static final Logger log = Logger.getLogger(CurriculumController.class);

	private CurriculumService curriculumService;

	@Autowired
	public void setCurriculumService(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}
	
	

	/**
	 * Get curriculum By placmentId
	 * 
	 * @param id
	 * @return Response Entity with corresponding curriculum
	 */
	@RequestMapping(value = "/one/curriculum/{id}", method = RequestMethod.GET)
	public ResponseEntity<Curriculum> findOneCurriculum(@PathVariable Integer id) {
		log.info("Finding curriculum with curriculumId: " + id);
		Curriculum curriculum = curriculumService.findOneById(id);
		return new ResponseEntity<>(curriculum, HttpStatus.OK);
	}

	/**
	 * Get all curriculums
	 * 
	 * @return ResponseEntity with all curriculums
	 */
	@RequestMapping(value = "/all/curriculum", method = RequestMethod.GET)
	public ResponseEntity<List<Curriculum>> findAllCurriculum() {
		log.info("Finding all curriculums...");
		List<Curriculum> curriculums = curriculumService.findAll();
		return new ResponseEntity<>(curriculums, HttpStatus.OK);
	}

	/**
	 * Create curriculum
	 *
	 * @param curriculum to save
	 * @return the response entity with saved curriculum
	 */
	@RequestMapping(value = "/curriculum/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> createCurriculum(@Valid @RequestBody Curriculum curriculum) {
		log.info("Saving curriculum: " + curriculum);
		return new ResponseEntity<>(curriculumService.save(curriculum), HttpStatus.CREATED);
	}

	/**
	 * Update curriculum
	 *
	 * @param curriculum to update
	 * @return the response entity
	 */
	@RequestMapping(value = "/curriculum/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateCurriculum(@Valid @RequestBody Curriculum curriculum) {
		log.info("Updating curriculum: " + curriculum);
		curriculumService.update(curriculum);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete curriculum
	 *
	 * @param id 
	 * @return the response entity
	 */
	@RequestMapping(value = "/curriculum/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCurriculum(@PathVariable int id) {
		Curriculum curriculum = new Curriculum();
		curriculum.setCurriculumId(id);
		log.info("Deleting curriculum: " + id);
		curriculumService.delete(curriculum);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
