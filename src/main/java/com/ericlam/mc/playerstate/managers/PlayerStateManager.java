package com.ericlam.mc.playerstate.managers;

import com.ericlam.mc.eld.services.ScheduleService;
import com.ericlam.mc.playerstate.commands.CommandException;
import com.ericlam.mc.playerstate.config.LangConfig;
import com.ericlam.mc.playerstate.config.PlayerStateConfig;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class PlayerStateManager {

    @Named("jpa")
    @Inject
    private PlayerStateSQLManager sqlManager;

    @Inject
    private LangConfig lang;

    @Inject
    private PlayerStateConfig config;

    private final Map<OfflinePlayer, String> stateMap = new ConcurrentHashMap<>();

    public void fetchPlayerState(OfflinePlayer player){
        sqlManager.getPlayerState(player).thenRunSync(state -> stateMap.put(player, state == null ? lang.getLang().getPure("no-state") : state)).join();
    }

    @NotNull
    public String getPlayerState(OfflinePlayer player){
        return this.stateMap.getOrDefault(player, lang.getLang().getPure("fetch-failed"));
    }

    public ScheduleService.BukkitPromise<Boolean> setPlayerState(OfflinePlayer player, String state) throws CommandException {
        if (!validateRegex(state)) throw new CommandException(lang.getLang().get("regex-not-match"));
        return sqlManager.setPlayerState(player, state).thenApplySync(result -> {
            if (result) stateMap.put(player, state);
            return result;
        });
    }


    private boolean validateRegex(String txt){
        return Pattern.compile(config.validateRegex).matcher(txt).matches();
    }
}
