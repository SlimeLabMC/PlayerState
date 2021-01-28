package com.ericlam.mc.playerstate.commands;

import com.ericlam.mc.eld.annotations.CommandArg;
import com.ericlam.mc.eld.annotations.Commander;
import com.ericlam.mc.eld.components.CommandNode;
import com.ericlam.mc.playerstate.config.LangConfig;
import com.ericlam.mc.playerstate.managers.PlayerStateManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import javax.inject.Inject;

@Commander(
        name = "set",
        description = "變更玩家狀態"
)
public class StatusAdminSetCommand implements CommandNode {

    @Inject
    private PlayerStateManager stateManager;

    @CommandArg(order = 1)
    private OfflinePlayer player;

    @CommandArg(labels = {"off / <狀態>"}, order = 2, optional = true)
    private String state;

    @Inject
    private LangConfig lang;

    @Override
    public void execute(CommandSender commandSender) {
        if (state == null){
            StatusCommand.showState(commandSender, player, stateManager, lang);
        }else{
            StatusCommand.changeState(commandSender, stateManager, state, player, lang);
        }
    }
}
