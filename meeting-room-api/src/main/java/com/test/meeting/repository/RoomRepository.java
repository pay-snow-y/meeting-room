package com.test.meeting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.meeting.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
