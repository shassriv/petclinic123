package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.cglib.core.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/business-config.xml",
		"classpath:spring/tools-config.xml",
		"classpath:spring/mvc-core-config.xml" })
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class PetControllerTests {

	 @Autowired
	 protected ClinicService clinicService;

	@Autowired
	private PetController petController;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
	}

	@Test
	public void testInitUpdateForm() throws Exception {
		ResultActions actions = mockMvc.perform(
				get("/owners/*/pets/{petId}/edit", 2L).accept(MediaType.ALL))
				.andExpect(status().isOk());
		actions.andExpect(forwardedUrl(("pets/createOrUpdatePetForm")));
	}

	@Test
	public void testCreationForm() throws Exception {
		ResultActions actions = mockMvc.perform(
				get("/owners/{ownerId}/pets/new", 2L).accept(MediaType.ALL))
				.andExpect(status().isOk());
		actions.andExpect(forwardedUrl(("pets/createOrUpdatePetForm")));
	}

	@Test
	public void testSetAllowedFields() throws Exception {
		Person p = new Person();
		WebDataBinder data = new WebDataBinder(p);
		petController.setAllowedFields(data);
	}

	@Test
	public void testProcessCreationForm() throws Exception {
		Pet pet = clinicService.findPetById(7);
		mockMvc.perform(post("/owners/6/pets/new").sessionAttr("pet",pet).accept(MediaType.ALL));
	}
	@Test
	public void testProcessCreationPetBirthDateIsNull() throws Exception {
		Pet pet = clinicService.findPetById(7);
		pet.setBirthDate(null); 
		System.out.println(pet.getOwner());
		mockMvc.perform(post("/owners/6/pets/new").sessionAttr("pet",pet).accept(MediaType.ALL));
	}
	@Test
	public void testProcessUpdateForm() throws Exception {
		Pet pet = clinicService.findPetById(7);
		mockMvc.perform(post("/owners/6/pets/7/edit").sessionAttr("pet",pet).accept(MediaType.ALL));
	}
	@Test
	public void testProcessUpdatePetNameisNull() throws Exception {
		Pet pet = clinicService.findPetById(7);
		pet.setName(null);
		mockMvc.perform(post("/owners/6/pets/7/edit").sessionAttr("pet",pet).accept(MediaType.ALL));
	}
	@Test
	public void tesetpopulatePetTypes() throws Exception {
		petController.populatePetTypes();
	}
	@Test
	public void testPetTypeFormatter() throws Exception
	{
		PetTypeFormatter petTypeFormatter = 	new PetTypeFormatter(clinicService); 
		Pet pet = clinicService.findPetById(7);
		petTypeFormatter.print(pet.getType(),null);
		petTypeFormatter.parse("dog", null);
	}

}
