package com.test.meeting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.meeting.domain.Calendar;
import com.test.meeting.domain.CalendarDto;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
	List<Calendar> findByRegYmdOrderByRoomIdAscStartTimeAsc(String regYmd);

	List<Calendar> findByCalId(int calId);

	@Query("SELECT  new com.test.meeting.domain.CalendarDto(c.calId, c.roomId, c.regUser, c.regYmd, c.startTime, c.endTime) FROM Calendar c WHERE c.roomId = :roomId AND c.regYmd = :regYmd AND c.startTime < :endTime AND c.endTime > :startTime")
	public List<CalendarDto> getOverlappedCalendars(@Param("roomId") int roomId, @Param("regYmd") String regYmd,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	@Query("SELECT  new com.test.meeting.domain.CalendarDto(c.calId, c.roomId, c.regUser, c.regYmd, c.startTime, c.endTime) FROM Calendar c, Calendar c2"
			+ " WHERE c.roomId= c2.roomId AND c.regYmd= c2.regYmd "
			+ "AND (c.startTime != c2.startTime OR c.endTime != c2.endTime ) "
			+ "AND (c.startTime < c2.endTime AND c.endTime > c2.startTime) "
			+ "GROUP BY c.roomId, c.regYmd, c.regUser, c.startTime, c.endTime ORDER BY c.calId ASC")
	public List<CalendarDto> getAllOverlappedCalendars();
}
