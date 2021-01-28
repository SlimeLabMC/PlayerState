package com.ericlam.mc.playerstate;

import com.ericlam.mc.eld.registrations.CommandRegistry;
import com.ericlam.mc.eld.registrations.ComponentsRegistry;
import com.ericlam.mc.eld.registrations.ListenerRegistry;
import com.ericlam.mc.playerstate.commands.StatusAdminCommand;
import com.ericlam.mc.playerstate.commands.StatusAdminSetCommand;
import com.ericlam.mc.playerstate.commands.StatusCommand;

import java.util.List;

public class PlayerStateRegistry implements ComponentsRegistry {

    @Override
    public void registerCommand(CommandRegistry commandRegistry) {
        commandRegistry.command(StatusCommand.class);
        commandRegistry.command(StatusAdminCommand.class, cmd -> {
            cmd.command(StatusAdminSetCommand.class);
        });
    }

    @Override
    public void registerListeners(ListenerRegistry listenerRegistry) {
        listenerRegistry.listeners(List.of(PlayerStateListener.class));
    }
}
