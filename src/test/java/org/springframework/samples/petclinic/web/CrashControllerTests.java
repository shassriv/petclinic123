package org.springframework.samples.petclinic.web;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/business-config.xml", "classpath:spring/tools-config.xml", "classpath:spring/mvc-core-config.xml"})
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class CrashControllerTests {
	
	@Autowired
	 private CrashController crashController;
	 
	 @Autowired 
	 private WebApplicationContext ctx;
	 
	 private MockMvc mockMvc;
	    
	  @Before
	  public void setup(){
	      this.mockMvc = MockMvcBuilders.standaloneSetup(crashController).build();
	  }
	  
	  
	  @Test(expected=RuntimeException.class)
	    public void testTriggerException() throws Exception {
		 
		  crashController.triggerException();
		 
	}

}
