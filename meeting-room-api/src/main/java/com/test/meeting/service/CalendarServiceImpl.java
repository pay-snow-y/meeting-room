package com.test.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.domain.Room;
import com.test.meeting.repository.CalendarRepository;
import com.test.meeting.repository.RoomRepository;

@Service
public class CalendarServiceImpl implements ICalendarService{
	@Autowired
	CalendarRepository calendarRepository;
	@Autowired
	RoomRepository roomRepository;

	@Override
	public String addCalendar(CalendarDto calendarDto) {
		try {
			Calendar calendar = new Calendar();
			calendar.setRoomId(calendarDto.getRoomId());
			calendar.setRegUser(calendarDto.getRegUser());
			calendar.setRegYmd(calendarDto.getRegYmd());
			calendar.setStartTime(calendarDto.getStartTime());
			calendar.setEndTime(calendarDto.getEndTime());
			
			calendarRepository.save(calendar);
			
			return "Success";
		} catch (Exception e) {
			return "Fail";
		}
	}

	@Override
	public List<Calendar> getCalendars(String regYmd) {
		return calendarRepository.findByRegYmdOrderByRoomIdAscStartTimeAsc(regYmd);
	}

	@Override
	public List<Room> getRooms() {
		return roomRepository.findAll();
	}

	@Override
	public List<Calendar> getOverlappedCalendars(int roomId, String regYmd, String startTime, String endTime) {
		return calendarRepository.getOverlappedCalendars(roomId, regYmd, startTime, endTime);
	}

}
