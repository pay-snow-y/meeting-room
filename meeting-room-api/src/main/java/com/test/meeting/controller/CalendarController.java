package com.test.meeting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.repository.CalendarRepository;

@Controller
public class CalendarController {
	@Autowired
	CalendarRepository calendarRepository;

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute(calendarRepository.findAll());
		System.out.println("abc");
		calendarRepository.findAll().forEach(System.out::println);
		return "index";
	}

	@RequestMapping(value = "/calendars", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addCalendars(@RequestBody CalendarDto calendarDto) {
		System.out.println("addCalendar");

		Calendar calendar = new Calendar();
		//calendar.setCalId(calendarDto.getCalId());
		calendar.setMeetingRoomId(calendarDto.getMeetingRoomId());
		calendar.setRegUser(calendarDto.getRegUser());
		calendar.setRegYmd(calendarDto.getRegYmd());
		calendar.setStartTime(calendarDto.getStartTime());
		calendar.setEndTime(calendarDto.getEndTime());
		
		calendarRepository.save(calendar);

		calendarRepository.findAll().forEach(System.out::println);

		return new ResponseEntity<String>("Good", HttpStatus.OK);
	}

	@RequestMapping(value = "/calendars", method = RequestMethod.GET)
	public @ResponseBody List<Calendar> getCalendars() {
		calendarRepository.findAll().forEach(System.out::println);

		return calendarRepository.findAll();
	}
}
