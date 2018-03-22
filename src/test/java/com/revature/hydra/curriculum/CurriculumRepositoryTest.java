package com.revature.hydra.curriculum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Curriculum;
import com.revature.hydra.curriculum.application.CurriculumRepositoryServiceApplication;
import com.revature.hydra.curriculum.data.CurriculumRepository;

/**
 * Unit tests on methods of CurriculumRepository class
 * 
 * @author JIAQI ZHANG
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurriculumRepositoryServiceApplication.class)
public class CurriculumRepositoryTest {

	private static final Logger log = Logger.getLogger(CurriculumRepositoryTest.class);

	@Autowired
	CurriculumRepository curriculumRepository;

	Curriculum testCurriculum;

	@Before
	public void init() {
		log.info("Initializing a test curriculum object for testing.");
		testCurriculum = new Curriculum();
		testCurriculum = curriculumRepository.save(testCurriculum);
	}

	@After
	public void teardown() {
		log.info("Tear down");
		if (curriculumRepository.findOneByCurriculumId(testCurriculum.getCurriculumId()) != null) {
			curriculumRepository.delete(testCurriculum);
		}
	}

	@Test
	public void addCurriculum() {
		log.info("Test adding a curriculum.");
		testCurriculum = new Curriculum();
		Curriculum savedCurriculum = curriculumRepository.save(testCurriculum);

		assertTrue(curriculumRepository.findAll().contains(savedCurriculum));
	}

	@Test
	public void findOneByPlacmentId() {
		log.info("Test getting a curriculum by curriculumId.");
		Curriculum curriculum = curriculumRepository.findOneByCurriculumId(testCurriculum.getCurriculumId());

		assertEquals(testCurriculum, curriculum);
	}

	@Test
	public void findAll() {
		log.info("Test getting all curriculums.");
		List<Curriculum> curriculums = curriculumRepository.findAll();

		assertFalse(curriculums.isEmpty());
	}

	@Test
	public void updateCurriculum() {
		log.info("Test updating a curriculum.");
		testCurriculum.setCurriculumName("SDET");;
		Curriculum updatedCurriculum = curriculumRepository.save(testCurriculum);

		assertEquals(updatedCurriculum.getCurriculumName(), testCurriculum.getCurriculumName());
	}

	@Test
	public void deleteCurriculum() {
		log.info("Test deleting a curriculum.");
		curriculumRepository.delete(testCurriculum);

		assertNull(curriculumRepository.findOneByCurriculumId(testCurriculum.getCurriculumId()));
	}

}
