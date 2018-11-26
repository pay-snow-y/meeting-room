package com.test.meeting.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.meeting.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
