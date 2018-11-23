package com.test.meeting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.test.meeting.domain.Calendar;
import com.test.meeting.service.IMeetingRoomApi;

@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.test.meeting.service"})
@Controller
public class MeetingRoomWebController {
	@Autowired
	IMeetingRoomApi IMeetingRoomApi;
	
	@RequestMapping(value = "/calendar/add", method = RequestMethod.GET)
    public String addCalendarForm() {
        return "addCalendar";
    }
	
	@RequestMapping(value = "/calendar", method = RequestMethod.POST)
    public String addCalendar(Calendar calendar) {
		
		String result = IMeetingRoomApi.addCalendars(calendar);
		
        return "redirect:/calendar/list";
    }
	
	@RequestMapping(value = "/calendar/list", method = RequestMethod.GET)
    public String main(Model model) {
		List<Calendar> calendars = IMeetingRoomApi.getCalendars();
		
		model.addAttribute("calendars", calendars);
		
        ModelAndView view = new ModelAndView("index");
        view.addObject("text", "world");
        view.addObject("calendars", calendars);
        System.out.println();
        return "index";
    }
}
