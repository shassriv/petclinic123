package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
public class OwnerControllerTests {

	@Autowired
	private OwnerController ownerController;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	private Map<String, Object> model;

	@Autowired
	protected ClinicService clinicService;

	@Before
	public void setup() {
		model = new HashMap<String, Object>();
		this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}

	@Test
	public void testInitCreationForm() throws Exception {
		ResultActions actions = mockMvc.perform(
				get("/owners/new").accept(MediaType.ALL)).andExpect(
				status().isOk());
		actions.andExpect(forwardedUrl(("owners/createOrUpdateOwnerForm")));
	}

	@Test
	public void testInitFindForm() throws Exception {
		ResultActions actions = mockMvc.perform(
				get("/owners/find").accept(MediaType.ALL)).andExpect(
				status().isOk());
		actions.andExpect(forwardedUrl(("owners/findOwners")));
	}

	@Test
	public void testShowOwner() throws Exception {
		mockMvc.perform(get("/owners/{ownerId}", 1).accept(MediaType.ALL))
				.andExpect(status().isOk());
	}

	@Test
	public void testProcessUpdateForm() throws Exception {
		Owner owner = clinicService.findOwnerById(1);
		mockMvc.perform(
				get("/owners/{ownerId}/edit", 1).header("owner", owner)
						.accept(MediaType.ALL)).andExpect(status().isOk());
	}

	@Test
	public void testProcessFindForm() throws Exception {
		Owner owner = clinicService.findOwnerById(1);
		mockMvc.perform(
				get("/owners").requestAttr("owner", owner)
						.accept(MediaType.ALL)).andExpect(status().isOk());
	}
}
