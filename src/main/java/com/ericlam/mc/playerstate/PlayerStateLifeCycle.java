package com.ericlam.mc.playerstate;

import com.ericlam.mc.eld.ELDLifeCycle;
import com.ericlam.mc.playerstate.managers.PlayerStateSQLManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public class PlayerStateLifeCycle implements ELDLifeCycle {


    @Inject
    private PlayerStateSQLManager sqlManager;

    @Inject
    private PlayerStatePlaceholder statePlaceholder;

    @Override
    public void onEnable(JavaPlugin javaPlugin) {
        sqlManager.createTable();
        statePlaceholder.register();
    }

    @Override
    public void onDisable(JavaPlugin javaPlugin) {
    }
}
