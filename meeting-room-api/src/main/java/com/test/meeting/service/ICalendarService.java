package com.test.meeting.service;

import java.util.List;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.domain.Room;

public interface ICalendarService {
	Calendar addCalendar(CalendarDto calendarDto);

	List<Calendar> getCalendars(String regYmd);
	
	List<Calendar> getCalendars(int calId);

	List<Room> getRooms();
	
	List<CalendarDto> getOverlappedCalendars(int roomId, String regYmd, String startTime, String endTime);
	
	List<CalendarDto> getAllOverlappedCalendars();
}
