package com.ericlam.mc.playerstate;

import com.ericlam.mc.eld.ELDBukkitPlugin;
import com.ericlam.mc.eld.ManagerProvider;
import com.ericlam.mc.eld.ServiceCollection;
import com.ericlam.mc.eld.annotations.ELDPlugin;
import com.ericlam.mc.playerstate.config.LangConfig;
import com.ericlam.mc.playerstate.config.PlayerStateConfig;
import com.ericlam.mc.playerstate.managers.PlayerStateManager;
import com.ericlam.mc.playerstate.managers.PlayerStateSQLJpa;
import com.ericlam.mc.playerstate.managers.PlayerStateSQLManager;
import com.ericlam.mc.playerstate.managers.PlayerStateSQLTraditional;

import java.util.Map;

@ELDPlugin(
        registry = PlayerStateRegistry.class,
        lifeCycle = PlayerStateLifeCycle.class
)
public class PlayerState extends ELDBukkitPlugin {

    @Override
    protected void bindServices(ServiceCollection serviceCollection) {
        serviceCollection.addSingleton(PlayerStateManager.class);
        serviceCollection.addServices(PlayerStateSQLManager.class, Map.of(
                "trad", PlayerStateSQLTraditional.class,
                "jpa", PlayerStateSQLJpa.class
        ));
        serviceCollection.addSingleton(PlayerStatePlaceholder.class);

        serviceCollection.addConfiguration(LangConfig.class);
        serviceCollection.addConfiguration(PlayerStateConfig.class);
    }

    @Override
    protected void manageProvider(ManagerProvider managerProvider) {

    }
}
