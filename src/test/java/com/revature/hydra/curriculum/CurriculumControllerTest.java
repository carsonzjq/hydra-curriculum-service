package com.revature.hydra.curriculum;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.revature.beans.Curriculum;
import com.revature.hydra.curriculum.application.CurriculumRepositoryServiceApplication;
import com.revature.hydra.curriculum.data.CurriculumRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= CurriculumRepositoryServiceApplication.class)
public class CurriculumControllerTest {
	
	private static final Logger log = Logger.getLogger(CurriculumControllerTest.class);
	
	private Curriculum testCurriculum;
	
	private MockMvc mockMvc;
	
	private final String mediaTypeJson = MediaType.APPLICATION_JSON_UTF8_VALUE;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
	private CurriculumRepository curriculumRepository;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Before
	public void init() {
		log.info("Initializing a test curriculum object for testing.");
		testCurriculum = new Curriculum("SDET");
		testCurriculum = curriculumRepository.save(testCurriculum);
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void tearDown() {
		log.info("Tear down");
		if (curriculumRepository.findOneByCurriculumId(testCurriculum.getCurriculumId()) != null) {
			curriculumRepository.delete(testCurriculum);
		}
	}
	
    @Test
    public void findOneCurriculumTest() throws Exception {
    	log.info("Testing /one/curriculum/{id} endpoint...");
        mockMvc.perform(get("/one/curriculum/" + testCurriculum.getCurriculumId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.curriculumId", is(testCurriculum.getCurriculumId())))
                .andExpect(jsonPath("$.curriculumName", is(testCurriculum.getCurriculumName())));
    }
    
    @Test
	public void findAllTest() throws Exception {
    	log.info("Testing /all/curriculum endpoint...");
		this.mockMvc.perform(get("/all/curriculum"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(mediaTypeJson));
	}
    
    @Test
    public void createCurriculumTest() throws Exception {
    	log.info("Testing /curriculum/create endpoint...");
        String curriculumJson = json(testCurriculum);

        this.mockMvc.perform(post("/curriculum/create")
                .content(curriculumJson)
                .contentType(this.mediaTypeJson))
                .andExpect(status().isCreated());
    }
    
    @Test
	public void updateCurriculumTest() throws Exception {
    	log.info("Testing /curriculum/update endpoint...");
		this.testCurriculum.setCurriculumName("Updated Name");
		this.mockMvc.perform(put("/curriculum/update")
					.content(this.json(testCurriculum))
					.contentType(this.mediaTypeJson))
					.andExpect(status().isNoContent());
	}
    
    @Test
	public void test7DeleteMs() throws Exception {
    	log.info("Testing /curriculum/delete/{id} endpoint...");
		this.mockMvc.perform(delete("/curriculum/delete/" + testCurriculum.getCurriculumId()))
					.andExpect(status().isNoContent());
	}
    
    protected String json(Object obj) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
