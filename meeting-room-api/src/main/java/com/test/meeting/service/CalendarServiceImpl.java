package com.test.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.repository.CalendarRepository;

@Service
public class CalendarServiceImpl implements ICalendarService{
	@Autowired
	CalendarRepository calendarRepository;

	@Override
	public String addCalendar(CalendarDto calendarDto) {
		Calendar calendar = new Calendar();
		calendar.setMeetingRoomId(calendarDto.getMeetingRoomId());
		calendar.setRegUser(calendarDto.getRegUser());
		calendar.setRegYmd(calendarDto.getRegYmd());
		calendar.setStartTime(calendarDto.getStartTime());
		calendar.setEndTime(calendarDto.getEndTime());
		
		calendarRepository.save(calendar);
		
		return "OK";
	}

	@Override
	public List<Calendar> getCalendars() {
		return calendarRepository.findAll();
	}

}
