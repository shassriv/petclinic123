package org.springframework.samples.petclinic.web;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.WebApplicationContext;



/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/business-config.xml", "classpath:spring/tools-config.xml", "classpath:spring/mvc-core-config.xml"})
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class VisitControllerTest {
	
	private static final String Dog = null;

	@Autowired
    private VisitController visitController;
	
	 @Autowired 
	    private WebApplicationContext ctx;

	    private MockMvc mockMvc;

	    @Before
	    public void setup() {
	        this.mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
	    }
	    
	    @Test
	    public void testinitNewVisitForm() throws Exception {
	    	Visit p = new Visit();
		  Map<String, Object> model1 =  new HashMap<String, Object>();
		  model1.put(Dog,p);
		  visitController.initNewVisitForm(2, model1);
		}
	    
	    @Test
	    public void testshowVisits() throws Exception {
		  Visit p = new Visit();
		  Map<String, Object> model1 =  new HashMap<String, Object>();
		  model1.put(Dog,p);
		  visitController.showVisits(2, model1);
		}
	    
	    @Test
	    public void testsetAllowedFields() throws Exception {
		  Visit p = new Visit();
		  WebDataBinder bindingResult = new WebDataBinder(mockMvc);
		  visitController.setAllowedFields(bindingResult);
		}
	    
	    
	    @Test
	    public void testloadPetWithVisit() throws Exception {
		  Visit p = new Visit();
		  
		  visitController.loadPetWithVisit(2);
		}
	

}
