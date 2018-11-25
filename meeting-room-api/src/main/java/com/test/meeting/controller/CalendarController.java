package com.test.meeting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.domain.Room;
import com.test.meeting.repository.CalendarRepository;
import com.test.meeting.service.ICalendarService;

@Controller
public class CalendarController {
	@Autowired
	CalendarRepository calendarRepository;
	@Autowired
	ICalendarService calendarService;

	@RequestMapping(value = "/rooms/{roomId}/regYmd/{regYmd}/startTime/{startTime}/endtime/{endTime}", method = RequestMethod.HEAD)
	public @ResponseBody ResponseEntity<String> isCalendarExists(@PathVariable("roomId") int roomId,
			@PathVariable("regYmd") String regYmd, @PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		List<Calendar> overlappedCalendarList = calendarService.getOverlappedCalendars(roomId, regYmd, startTime, endTime);

		if (CollectionUtils.isEmpty(overlappedCalendarList)) {
			return new ResponseEntity<String>("Searching calendar does not exists.", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Searching calendar already exists." , HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/calendars", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addCalendars(@RequestBody CalendarDto calendarDto) {
		String result = calendarService.addCalendar(calendarDto);

		if (isFail(result)) {
			return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}

	private boolean isFail(String result) {
		return StringUtils.equalsIgnoreCase(result, "Fail");
	}

	@RequestMapping(value = { "/calendars/{regYmd}", "/calendars" }, method = RequestMethod.GET)
	public @ResponseBody List<Calendar> getCalendars(@PathVariable(name = "regYmd", required = false) String regYmd) {
		if (StringUtils.isEmpty(regYmd)) {
			regYmd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}

		return calendarService.getCalendars(regYmd);
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public @ResponseBody List<Room> getRooms() {
		return calendarService.getRooms();
	}
}
