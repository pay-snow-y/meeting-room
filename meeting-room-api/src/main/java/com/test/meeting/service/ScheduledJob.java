package com.test.meeting.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;
import com.test.meeting.repository.CalendarRepository;

@Component
public class ScheduledJob {
	CalendarRepository calendarRepository;

	@Autowired
	public ScheduledJob(CalendarRepository calendarRepository) {
		this.calendarRepository = calendarRepository;
	}

	// check duplicate calendar and cancel the rest except first every second
	@Scheduled(fixedDelay = 1000)
	public void calendarCanceler() {
		List<CalendarDto> calendarList;
		while (CollectionUtils.isNotEmpty(calendarList = calendarRepository.getAllOverlappedCalendars())) {
			CalendarDto confirmedCalendar = calendarList.get(0);
			List<CalendarDto> removeCalendarList = calendarRepository.getOverlappedCalendars(confirmedCalendar.getRoomId(),
					confirmedCalendar.getRegYmd(), confirmedCalendar.getStartTime(), confirmedCalendar.getEndTime());
			
			for (CalendarDto calendarDto : removeCalendarList) {
				if(confirmedCalendar.getCalId() == calendarDto.getCalId()) {
					continue;
				}
				Calendar calendar = new Calendar();
				calendar.setCalId(calendarDto.getCalId());
				calendarRepository.delete(calendar);
			}
		}
		// System.out.println(calendarRepository.getAllOverlappedCalendars().size());
	}
}
