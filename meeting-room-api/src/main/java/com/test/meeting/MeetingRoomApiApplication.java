package com.test.meeting;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.meeting.domain.Calendar;
import com.test.meeting.repository.CalendarRepository;

@SpringBootApplication
public class MeetingRoomApiApplication {
	
	@Bean
	InitializingBean initData(CalendarRepository calendarRepository) {
		return () -> {
			calendarRepository.save(new Calendar(1, "Seyol"));
		};
	}
	
	@Bean
    CommandLineRunner init(CalendarRepository calendarRepository) {
        return args -> {
            System.out.println("---------------------------------");
            calendarRepository.findAll().forEach(System.out::println);
            System.out.println("---------------------------------");
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomApiApplication.class, args);
	}
}
