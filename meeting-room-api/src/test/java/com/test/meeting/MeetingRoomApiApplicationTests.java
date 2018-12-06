package com.test.meeting;

import java.nio.charset.Charset;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.test.meeting.domain.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup; 

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class MeetingRoomApiApplicationTests {

	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2httpMessageConverter;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"));
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2httpMessageConverter = (HttpMessageConverter) Arrays.asList(converters).stream()
				.filter(converter -> converter instanceof MappingJackson2HttpMessageConverter).findAny().get();
	}
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void addCalendars() throws Exception {
		Gson gson = new Gson();
	    String json = gson.toJson(new Calendar(1, "Seyol", "2018-11-27", "10:30", "11:00", "N", 0));
		mockMvc.perform(post("/calendar").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	public void findCalendarByRegYmd() throws Exception {
		mockMvc.perform(get("/calendars/2018-11-26")).andExpect(status().isOk());
	}
	
	@Test
	public void findCalendarByCalId() throws Exception {
		mockMvc.perform(get("/calId/1")).andExpect(status().isOk());
	}
	
	@Test
	public void findAllRooms() throws Exception {
		mockMvc.perform(get("/rooms")).andExpect(status().isOk());
	}
	
	@Test
	public void findCalendarByConditions() throws Exception {
		mockMvc.perform(get("/rooms/1/regYmd/2018-11-26/startTime/10:30/endtime/12:00")).andExpect(status().isConflict());
	}

	@Test
	public void contextLoads() {
	}

}
