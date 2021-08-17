package com.ericlam.mc.playerstate.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerStatusRepository extends JpaRepository<PlayerStatus, UUID> {
}