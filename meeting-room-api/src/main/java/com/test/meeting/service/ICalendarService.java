package com.test.meeting.service;

import java.util.List;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.domain.Room;

public interface ICalendarService {
	String addCalendar(CalendarDto calendarDto);

	List<Calendar> getCalendars(String regYmd);

	List<Room> getRooms();
	
	List<Calendar> getOverlappedCalendars(int roomId, String regYmd, String startTime, String endTime);
}
