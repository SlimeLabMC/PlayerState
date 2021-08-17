package com.ericlam.mc.playerstate.managers;

import com.ericlam.mc.eld.services.ScheduleService;
import com.ericlam.mc.playerstate.PlayerState;
import com.ericlam.mc.playerstate.sql.PlayerStatus;
import com.ericlam.mc.playerstate.sql.PlayerStatusRepository;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

public final class PlayerStateSQLJpa implements PlayerStateSQLManager{

    @Inject
    private PlayerStatusRepository statusRepository;
    @Inject
    private ScheduleService scheduleService;
    @Inject
    private PlayerState plugin;

    @Override
    public ScheduleService.BukkitPromise<String> getPlayerState(OfflinePlayer player) {
        return scheduleService.callAsync(plugin, () -> statusRepository.findById(player.getUniqueId()).map(s -> s.state).orElse(null));
    }

    @Override
    public ScheduleService.BukkitPromise<Boolean> setPlayerState(OfflinePlayer player, @Nullable String state) {
        return scheduleService.callAsync(plugin, () -> {
            PlayerStatus status = new PlayerStatus();
            status.uuid = player.getUniqueId();
            status.state = state == null ? "" : state;
            statusRepository.save(status);
            return true;
        });
    }
}
