package com.ericlam.mc.playerstate.managers;

import com.ericlam.mc.eld.services.ScheduleService;
import org.bukkit.OfflinePlayer;

import javax.annotation.Nullable;

public interface PlayerStateSQLManager {

    ScheduleService.BukkitPromise<String> getPlayerState(OfflinePlayer player);

    ScheduleService.BukkitPromise<Boolean> setPlayerState(OfflinePlayer player, @Nullable String state);


}
