package org.springframework.samples.petclinic.web;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.support.SessionStatus;
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
public class OwnerControllerTest {

	@Autowired
	private OwnerController ownerController;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;
	
	private  Map<String, Object> model;

	@Before
	public void setup() {
		model = new HashMap<String,Object>();
	}
	
	 @Test
	 public void testInitCreationForm() throws Exception {
		
		 assertEquals("owners/createOrUpdateOwnerForm",ownerController.initCreationForm(model));
	 }
	 @Test
	 public void testInitFindForm() throws Exception {
		
		 assertEquals("owners/findOwners", ownerController.initFindForm(model));
	
	 }
	 @Test
	 public void testInitShowOwner() throws Exception {
		 ownerController.showOwner(1);
	
	 }
	 @Test
	 public void testProcessCreationForm() throws Exception {
		 Owner owner = new Owner();
	 }
	 
	 @Test
	 public void testshowOwner() throws Exception {
		 Owner owner = new Owner();
		 ownerController.showOwner(5);
	 }

	 @Test
	 public void testinitUpdateOwnerForm() throws Exception {
		 Owner owner = new Owner();
		 
		 /*ownerController.initUpdateOwnerForm(2,);*/
	 }
	 
	 @Test
	 public void testprocessFindForm() throws Exception {
		 Owner owner = new Owner();
		 Object command = null;
		BindingResult bindingResult = new BeanPropertyBindingResult(command, "command");	
		 ownerController.processFindForm(owner,bindingResult,model);
	 }
}
