package com.test.meeting.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.Room;

@FeignClient("meeting-room-api")
public interface IMeetingRoomApi {
	@GetMapping("/calendars/{regYmd}")
	List<Calendar> getCalendars(@PathVariable("regYmd") String regYmd);
	
	@GetMapping("/rooms")
	List<Room> getRooms();
	
	@PostMapping("/calendars")
	ResponseEntity<Calendar> addCalendars(Calendar calendars);
	
	@GetMapping("/rooms/{roomId}/regYmd/{regYmd}/startTime/{startTime}/endtime/{endTime}")
	public @ResponseBody ResponseEntity<String> isCalendarExists(@PathVariable("roomId") int roomId,
			@PathVariable("regYmd") String regYmd, @PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime);

	@GetMapping("/calId/{calId}")
	public @ResponseBody ResponseEntity<String> getByCalId(@PathVariable("calId") int calId);
}
