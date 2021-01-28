package com.ericlam.mc.playerstate;

import com.ericlam.mc.playerstate.managers.PlayerStateManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.inject.Inject;

public class PlayerStateListener implements Listener {

    @Inject
    private PlayerStateManager stateManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        var player = e.getPlayer();
        stateManager.fetchPlayerState(player);
    }
}
