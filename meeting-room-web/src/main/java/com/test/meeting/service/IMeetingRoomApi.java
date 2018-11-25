package com.test.meeting.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.Room;

@FeignClient("meeting-room-api")
public interface IMeetingRoomApi {
	@RequestMapping(value = "/calendars/{regYmd}", method = RequestMethod.GET)
	List<Calendar> getCalendars(@PathVariable("regYmd") String regYmd);
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	List<Room> getRooms();
	
	@RequestMapping(value = "/calendars", method = RequestMethod.POST)
	ResponseEntity<String> addCalendars(Calendar calendars);
	
	@RequestMapping(value = "/rooms/{roomId}/regYmd/{regYmd}/startTime/{startTime}/endtime/{endTime}", method = RequestMethod.HEAD)
	public @ResponseBody ResponseEntity<String> isCalendarExists(@PathVariable("roomId") int roomId,
			@PathVariable("regYmd") String regYmd, @PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime);
}
