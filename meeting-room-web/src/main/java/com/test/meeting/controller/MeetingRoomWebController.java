package com.test.meeting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.Room;
import com.test.meeting.exception.CalendarNotFoundException;
import com.test.meeting.exception.DuplicatedRegisterException;
import com.test.meeting.exception.MeetingRoomNotFoundException;
import com.test.meeting.service.IMeetingRoomApi;

import feign.FeignException;

@Controller
public class MeetingRoomWebController {
	IMeetingRoomApi iMeetingRoomApi;

	@Autowired
	public MeetingRoomWebController(IMeetingRoomApi iMeetingRoomApi) {
		this.iMeetingRoomApi = iMeetingRoomApi;
	}

	@GetMapping("/calendar/add")
	public String addCalendarForm(String regYmd, Model model) {
		model.addAttribute("regYmd", regYmd);
		return "addCalendar";
	}

	/**
	 * Submit new calendar
	 * 1. Check new calendar overlap exist calendars
	 * 2. Add new calendar
	 * 3. Sleep 2 seconds
	 * 4. Check new calendar is exists or not
	 * 		4.1 every second scheduled job check overlapping calendars and remove rest calendars except first one.
	 * 
	 * @param calendar
	 * @param redirectAttributes
	 * @return
	 * @throws InterruptedException
	 */
	@PostMapping("/calendar/add/submit")
	public String addCalendar(Calendar calendar, RedirectAttributes redirectAttributes) throws InterruptedException {
		try {
			if (iMeetingRoomApi.isCalendarExists(calendar.getRoomId(), calendar.getRegYmd(), calendar.getStartTime(),
					calendar.getEndTime()).getStatusCode() == HttpStatus.OK) {
				
				ResponseEntity<Calendar> resultCalendar = iMeetingRoomApi.addCalendar(calendar);
				if (resultCalendar.getStatusCode() == HttpStatus.CREATED) {
					Thread.sleep(2000L); // wait for 2 seconds
					if (iMeetingRoomApi.getByCalId(resultCalendar.getBody().getCalId()).getStatusCode() == HttpStatus.OK) {
						redirectAttributes.addFlashAttribute("result", "Calendar was successfully registered");
					}
				} else {
					redirectAttributes.addFlashAttribute("result", "Failed to create calendar");
				}
				
			}
		} catch (FeignException fe) {
			if (fe.status() == HttpStatus.CONFLICT.value()) {
				redirectAttributes.addFlashAttribute("result", "Calendar overlap exists items");
			}
			
			if (fe.status() == HttpStatus.NOT_FOUND.value()) {
				redirectAttributes.addFlashAttribute("result", "Calendar was canceled by overlapping");
			}
		}

		return "redirect:/calendar?regYmd=" + calendar.getRegYmd();
	}

	@GetMapping("/calendar")
	public String main(String regYmd, Model model) {
		if (StringUtils.isEmpty(regYmd)) {
			regYmd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}

		List<Calendar> calendars = iMeetingRoomApi.getCalendars(regYmd);
		List<Room> rooms = iMeetingRoomApi.getRooms();
		model.addAttribute("regYmd", regYmd);
		model.addAttribute("calendars", calendars);
		model.addAttribute("rooms", rooms);

		return "calendar";
	}
}
