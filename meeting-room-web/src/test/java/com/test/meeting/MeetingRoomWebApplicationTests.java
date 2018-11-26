package com.test.meeting;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class MeetingRoomWebApplicationTests {

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
		mockMvc.perform(post("/calendar/add/submit").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON).param("roomId", "1").param("regUser", "Seyol")
				.param("regYmd", "2018-11-27").param("startTime", "10:30").param("endTime", "11:00"))
				.andExpect(status().isFound());
	}

	@Test
	public void getAddCalendarPage() throws Exception {
		mockMvc.perform(get("/calendar/add")).andExpect(status().isOk());
	}

	@Test
	public void getCalendarMainpage() throws Exception {
		mockMvc.perform(get("/calendar")).andExpect(status().isOk());
	}

	@Test
	public void contextLoads() {
	}

}
