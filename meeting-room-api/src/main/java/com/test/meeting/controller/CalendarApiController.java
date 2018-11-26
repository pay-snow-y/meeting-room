package com.test.meeting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.domain.Room;
import com.test.meeting.repository.CalendarRepository;
import com.test.meeting.service.ICalendarService;

@RestController
public class CalendarApiController {
	CalendarRepository calendarRepository;
	ICalendarService calendarService;
	
	@Autowired
	public CalendarApiController(CalendarRepository calendarRepository, ICalendarService calendarService) {
		this.calendarRepository = calendarRepository;
		this.calendarService = calendarService;
	}

	@GetMapping("/rooms/{roomId}/regYmd/{regYmd}/startTime/{startTime}/endtime/{endTime}")
	public ResponseEntity<String> isCalendarExists(@PathVariable("roomId") int roomId,
			@PathVariable("regYmd") String regYmd, @PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime) {
		List<CalendarDto> overlappedCalendarList = calendarService.getOverlappedCalendars(roomId, regYmd, startTime,
				endTime);

		if (CollectionUtils.isEmpty(overlappedCalendarList)) {
			return new ResponseEntity<String>("Searching calendar does not exists.", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Searching calendar already exists.", HttpStatus.CONFLICT);
	}
	
	@GetMapping("/calId/{calId}")
	public ResponseEntity<String> getByCalId(@PathVariable("calId") int calId) {
		List<Calendar> overlappedCalendarList = calendarService.getCalendars(calId);

		if (CollectionUtils.isEmpty(overlappedCalendarList)) {
			return new ResponseEntity<String>("Searching calendar does not exists.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("Searching calendar exists.", HttpStatus.OK);
	}
	
	@GetMapping("/findAllOverlapCalendars")
	public ResponseEntity<List<CalendarDto>> findAllOverlapCalendars() {
		List<CalendarDto> overlappedCalendarList = calendarService.getAllOverlappedCalendars();

		if (CollectionUtils.isEmpty(overlappedCalendarList)) {
			return new ResponseEntity<List<CalendarDto>>(overlappedCalendarList, HttpStatus.OK);
		}

		return new ResponseEntity<List<CalendarDto>>(overlappedCalendarList, HttpStatus.CONFLICT);
	}

	@PostMapping("/calendars")
	public ResponseEntity<Calendar> addCalendars(@RequestBody CalendarDto calendarDto) {
		try {
			Calendar resultCalendar = calendarService.addCalendar(calendarDto);
	
			return new ResponseEntity<Calendar>(resultCalendar, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Calendar>(new Calendar(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping({ "/calendars/{regYmd}", "/calendars" })
	public List<Calendar> getCalendars(@PathVariable(name = "regYmd", required = false) String regYmd) {
		if (StringUtils.isEmpty(regYmd)) {
			regYmd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}

		return calendarService.getCalendars(regYmd);
	}

	@GetMapping("/rooms")
	public List<Room> getRooms() {
		return calendarService.getRooms();
	}
}
