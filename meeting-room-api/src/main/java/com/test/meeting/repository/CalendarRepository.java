package com.test.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.meeting.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
