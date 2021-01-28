package com.ericlam.mc.playerstate.commands;

import com.ericlam.mc.eld.annotations.Commander;
import com.ericlam.mc.eld.components.CommandNode;
import org.bukkit.command.CommandSender;

@Commander(
        name = "statusadmin",
        description = "管理員指令",
        permission = "status.admin"
)
public class StatusAdminCommand implements CommandNode {

    @Override
    public void execute(CommandSender commandSender) {
    }
}
