package com.ericlam.mc.playerstate.commands;

import com.ericlam.mc.eld.annotations.CommandArg;
import com.ericlam.mc.eld.annotations.Commander;
import com.ericlam.mc.eld.components.CommandNode;
import com.ericlam.mc.eld.services.ScheduleService;
import com.ericlam.mc.playerstate.config.LangConfig;
import com.ericlam.mc.playerstate.managers.PlayerStateManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.text.MessageFormat;

@Commander(
        name = "status",
        description = "更改狀態",
        permission = "status.set",
        alias = {"ss"},
        playerOnly = true
)
public class StatusCommand implements CommandNode {

    @Inject
    private PlayerStateManager stateManager;

    @CommandArg(labels = {"off / <狀態>"}, order = 1, optional = true)
    private String state;

    @Inject
    private LangConfig lang;

    @Override
    public void execute(CommandSender commandSender) {
        var player = (Player) commandSender;
        if (state == null){
            showState(commandSender, player, stateManager, lang);
        }else{
            changeState(commandSender, stateManager, state, player, lang);
        }
    }

    static void showState(CommandSender sender, OfflinePlayer player, PlayerStateManager stateManager, LangConfig lang){
        var state = stateManager.getPlayerState(player);
        var msg = sender == player ? MessageFormat.format(lang.getLang().get("your-state"), state) : MessageFormat.format(lang.getLang().get("player-state"), state);
        sender.sendMessage(msg);
    }

    static void changeState(CommandSender commandSender, PlayerStateManager stateManager, String state, OfflinePlayer player, LangConfig lang){
        try {

            ScheduleService.BukkitPromise<Boolean> promise;

            if (state.equalsIgnoreCase("off")){
                promise = stateManager.setPlayerState(player, null);
            }else{
                promise = stateManager.setPlayerState(player, state);
            }

            promise.thenRunSync(result -> commandSender.sendMessage(lang.getLang().get(result ? "success" : "failed"))).join();
        }catch (CommandException e){
            commandSender.sendMessage(e.getMessage());
        }
    }
}
