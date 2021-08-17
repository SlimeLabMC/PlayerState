package com.ericlam.mc.playerstate;

import com.ericlam.mc.eld.ELDLifeCycle;
import com.ericlam.mc.playerstate.managers.PlayerStateSQLManager;
import com.ericlam.mc.playerstate.managers.PlayerStateSQLTraditional;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Named;

public class PlayerStateLifeCycle implements ELDLifeCycle {


    @Named("jpa")
    @Inject
    private PlayerStateSQLManager sqlManager;

    @Inject
    private PlayerStatePlaceholder statePlaceholder;

    @Override
    public void onEnable(JavaPlugin javaPlugin) {
        if (sqlManager instanceof PlayerStateSQLTraditional){
            ((PlayerStateSQLTraditional) sqlManager).createTable();
        }
        statePlaceholder.register();
    }

    @Override
    public void onDisable(JavaPlugin javaPlugin) {
    }
}
