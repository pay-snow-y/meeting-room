package com.test.meeting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.meeting.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
	List<Calendar> findByRegYmdOrderByRoomIdAscStartTimeAsc(String regYmd);

	@Query("SELECT c.roomId, c.regYmd, c.regUser, c.startTime, c.endTime FROM Calendar c WHERE c.roomId = :roomId AND c.regYmd = :regYmd AND c.startTime < :endTime AND c.endTime > :startTime")
	public List<Calendar> getOverlappedCalendars(@Param("roomId") int roomId, @Param("regYmd") String regYmd,
			@Param("startTime") String startTime, @Param("endTime") String endTime);
}
