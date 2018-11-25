package com.test.meeting;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.Room;
import com.test.meeting.repository.CalendarRepository;
import com.test.meeting.repository.RoomRepository;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class MeetingRoomApiApplication {
	
	@Bean
	InitializingBean initDataCalendar(CalendarRepository calendarRepository) {
		return () -> {
			calendarRepository.save(new Calendar(1, "Seyol", "2018-11-25", "10:30", "11:00", "N", 0));
			
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "11:30", "12:00", "N", 0));
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "12:30", "15:00", "N", 0));
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "10:30", "11:00", "N", 0));
			
			calendarRepository.save(new Calendar(2, "Kumho", "2018-11-26", "11:30", "12:00", "N", 0));
			calendarRepository.save(new Calendar(2, "Kumho", "2018-11-26", "12:30", "15:00", "N", 0));
			calendarRepository.save(new Calendar(2, "Kumho", "2018-11-26", "10:30", "11:00", "N", 0));
			
			calendarRepository.save(new Calendar(3, "Hankook", "2018-11-26", "11:30", "12:00", "N", 0));
			calendarRepository.save(new Calendar(3, "Hankook", "2018-11-26", "12:30", "15:00", "N", 0));
			calendarRepository.save(new Calendar(3, "Hankook", "2018-11-26", "10:30", "11:00", "N", 0));
			
			calendarRepository.save(new Calendar(1, "Kimchi", "2018-11-27", "10:30", "11:00", "Y", 3));
			
			calendarRepository.save(new Calendar(1, "Goguma", "2018-11-28", "10:30", "11:00", "Y", 2));
			
			calendarRepository.save(new Calendar(1, "Baechu", "2018-11-29", "10:30", "11:00", "Y", 1));
		};
	}
	
	@Bean
	InitializingBean initDataRoom(RoomRepository roomRepository) {
		return () -> {
			roomRepository.save(new Room("회의실 A"));
			roomRepository.save(new Room("회의실 B"));
			roomRepository.save(new Room("회의실 C"));
		};
	}
	
	@Bean
    CommandLineRunner initCalendar(CalendarRepository calendarRepository) {
        return args -> {
            System.out.println("---------------------------------");
            calendarRepository.findAll().forEach(System.out::println);
            System.out.println("---------------------------------");
        };
    }
	
	@Bean
    CommandLineRunner initRoom(RoomRepository roomRepository) {
        return args -> {
            System.out.println("---------------------------------");
            roomRepository.findAll().forEach(System.out::println);
            System.out.println("---------------------------------");
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomApiApplication.class, args);
	}
}
