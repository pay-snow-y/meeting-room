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
	CalendarRepository calendarRepository;
	RoomRepository roomRepository;

	@Autowired
	public CalendarServiceImpl(CalendarRepository calendarRepository, RoomRepository roomRepository) {
		this.calendarRepository = calendarRepository;
		this.roomRepository = roomRepository;
	}

	@Override
	public Calendar addCalendar(CalendarDto calendarDto) {
		try {
			Calendar calendar = new Calendar();
			calendar.setRoomId(calendarDto.getRoomId());
			calendar.setRegUser(calendarDto.getRegUser());
			calendar.setRegYmd(calendarDto.getRegYmd());
			calendar.setStartTime(calendarDto.getStartTime());
			calendar.setEndTime(calendarDto.getEndTime());
			
			Calendar resultCalendar = calendarRepository.save(calendar);
			
			return resultCalendar;
		} catch (Exception e) {
			return new Calendar();
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
	public List<CalendarDto> getOverlappedCalendars(int roomId, String regYmd, String startTime, String endTime) {
		return calendarRepository.getOverlappedCalendars(roomId, regYmd, startTime, endTime);
	}
	
	@Override
	public 	List<CalendarDto> getAllOverlappedCalendars() {
		return calendarRepository.getAllOverlappedCalendars();
	}

	@Override
	public List<Calendar> getCalendars(int calId) {
		return calendarRepository.findByCalId(calId);
	}
}
