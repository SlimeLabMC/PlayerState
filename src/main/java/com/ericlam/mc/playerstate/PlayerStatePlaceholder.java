package com.ericlam.mc.playerstate;

import com.ericlam.mc.playerstate.managers.PlayerStateManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.Locale;

public class PlayerStatePlaceholder extends PlaceholderExpansion {

    @Inject
    private PlayerState playerState;

    @Inject
    private PlayerStateManager stateManager;

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("self")){
            return stateManager.getPlayerState(player);
        }else{
            var uuid = Bukkit.getPlayerUniqueId(params);
            if (uuid == null) return null;
            return stateManager.getPlayerState(Bukkit.getOfflinePlayer(uuid));
        }
    }

    @Override
    public @NotNull String getIdentifier() {
        return playerState.getName().toLowerCase(Locale.ROOT);
    }

    @Override
    public @NotNull String getAuthor() {
        return playerState.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return playerState.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }
}
