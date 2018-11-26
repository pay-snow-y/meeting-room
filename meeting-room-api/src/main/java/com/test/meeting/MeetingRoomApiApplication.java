package com.test.meeting;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.Room;
import com.test.meeting.repository.CalendarRepository;
import com.test.meeting.repository.RoomRepository;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
public class MeetingRoomApiApplication {
	
	@Bean
	InitializingBean initDataCalendar(CalendarRepository calendarRepository) {
		return () -> {
			calendarRepository.save(new Calendar(1, "Seyol", "2018-11-25", "10:30", "11:00", "N", 0));
			
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "11:30", "12:00", "N", 0));
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "11:30", "12:30", "N", 0));
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "12:00", "12:30", "N", 0));
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "12:30", "15:00", "N", 0));
			calendarRepository.save(new Calendar(1, "Minho", "2018-11-26", "10:30", "11:00", "N", 0));
			
			calendarRepository.save(new Calendar(2, "Kumho", "2018-11-26", "11:30", "12:00", "N", 0));
			calendarRepository.save(new Calendar(2, "Kumho", "2018-11-26", "12:30", "15:00", "N", 0));
			calendarRepository.save(new Calendar(2, "Kumho", "2018-11-26", "13:30", "15:00", "N", 0));
			calendarRepository.save(new Calendar(2, "Kumho", "2018-11-26", "14:30", "15:00", "N", 0));
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
			roomRepository.save(new Room("회의실A(ID:1)"));
			roomRepository.save(new Room("회의실B(ID:2)"));
			roomRepository.save(new Room("회의실C(ID:3)"));
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
	
	@Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomApiApplication.class, args);
	}
}
