package com.test.meeting.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.meeting.domain.Calendar;

@FeignClient("meeting-room-api")
public interface IMeetingRoomApi {
	@RequestMapping(value = "/calendars", method = RequestMethod.GET)
	List<Calendar> getCalendars();
	
	@RequestMapping(value = "/calendars", method = RequestMethod.POST)
	String addCalendars(Calendar calendars);
}
