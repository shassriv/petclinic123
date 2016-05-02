package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/business-config.xml",
		"classpath:spring/tools-config.xml",
		"classpath:spring/mvc-core-config.xml" })
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class VisitControllerTests {


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
	public void testInitNewVisitForm() throws Exception {
		ResultActions actions = mockMvc.perform(get("/owners/*/pets/{petId}/visits/new",2L).accept(MediaType.ALL))
                .andExpect(status().isOk());
		actions.andExpect(forwardedUrl(("pets/createOrUpdateVisitForm"))); 
	}

	@Test
	public void testShowVisits() throws Exception {
		ResultActions actions = mockMvc.perform(get("/owners/*/pets/{petId}/visits",2L).accept(MediaType.ALL))
                .andExpect(status().isOk());
		actions.andExpect(forwardedUrl(("visitList"))).andExpect(model().attributeExists("visits")); 
	}

	@Test
	public void testSetAllowedFields() throws Exception {
		WebDataBinder bindingResult = new WebDataBinder(mockMvc);
		visitController.setAllowedFields(bindingResult);
	}

	@Test
	public void testProcessNewVisitFormError() throws Exception {
	//	Visit visit = visitController.loadPetWithVisit(2);
		ResultActions actions = mockMvc.perform(post("/owners/1/pets/2/visits/new").accept(MediaType.ALL));
	}

}
