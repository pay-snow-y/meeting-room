package com.test.meeting.controller;

import java.util.List;

import javax.inject.Inject;

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
import com.test.meeting.service.ICalendarService;

@Controller
public class CalendarController {
	@Autowired
	CalendarRepository calendarRepository;
	@Autowired
	ICalendarService calendarService;

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute(calendarRepository.findAll());
		calendarRepository.findAll().forEach(System.out::println);
		return "index";
	}

	@RequestMapping(value = "/calendars", method = RequestMethod.POST)
	public @ResponseBody String addCalendars(@RequestBody CalendarDto calendarDto) {
		calendarService.addCalendar(calendarDto);
		calendarRepository.findAll().forEach(System.out::println);

		return "Good";
	}

	@RequestMapping(value = "/calendars", method = RequestMethod.GET)
	public @ResponseBody List<Calendar> getCalendars() {
		calendarRepository.findAll().forEach(System.out::println);

		return calendarService.getCalendars();
	}
}
