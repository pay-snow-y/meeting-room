package com.test.meeting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.Room;
import com.test.meeting.service.IMeetingRoomApi;

import feign.FeignException;

@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.test.meeting.service" })
@Controller
public class MeetingRoomWebController {
	@Autowired
	IMeetingRoomApi iMeetingRoomApi;

	@RequestMapping(value = "/calendar/add", method = RequestMethod.GET)
	public String addCalendarForm(String regYmd, Model model) {
		model.addAttribute("regYmd", regYmd);
		return "addCalendar";
	}

	@RequestMapping(value = "/calendar/add/submit", method = RequestMethod.POST)
	public String addCalendar(Calendar calendar, RedirectAttributes redirectAttributes) {
		try {
			if (iMeetingRoomApi.isCalendarExists(calendar.getRoomId(), calendar.getRegYmd(), calendar.getStartTime(),
					calendar.getEndTime()).getStatusCode() == HttpStatus.OK) {
				if (iMeetingRoomApi.addCalendars(calendar).getStatusCode() != HttpStatus.CREATED) {
					redirectAttributes.addFlashAttribute("result", "Fail");
				}
			}
		} catch (FeignException fe) {
			if (fe.status() == HttpStatus.CONFLICT.value()) {
				redirectAttributes.addFlashAttribute("result", "Already Exists");
			}
		}

		return "redirect:/calendar?regYmd=" + calendar.getRegYmd();
	}

	@RequestMapping(value = { "/calendar" }, method = RequestMethod.GET)
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
