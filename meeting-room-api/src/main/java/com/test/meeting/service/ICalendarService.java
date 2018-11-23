package com.test.meeting.service;

import java.util.List;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;


public interface ICalendarService {
	String addCalendar(CalendarDto calendarDto);
	
	List<Calendar> getCalendars();
}
