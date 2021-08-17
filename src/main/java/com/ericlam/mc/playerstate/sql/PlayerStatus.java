package com.ericlam.mc.playerstate.sql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "PlayerState")
public final class PlayerStatus {

    @Id
    public UUID uuid;

    public String state = null;


}
