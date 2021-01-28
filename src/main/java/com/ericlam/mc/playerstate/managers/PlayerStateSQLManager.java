package com.ericlam.mc.playerstate.managers;

import chu77.eldependenci.sql.SQLDataSource;
import com.ericlam.mc.eld.services.ScheduleService;
import com.ericlam.mc.playerstate.PlayerState;
import org.bukkit.OfflinePlayer;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerStateSQLManager {

    @Inject
    private SQLDataSource dataSource;

    @Inject
    private ScheduleService scheduleService;

    @Inject
    private PlayerState playerState;


    public void createTable(){
        scheduleService.runAsync(playerState, () -> {
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS PlayerState (uuid varchar(40) PRIMARY KEY NOT NULL, state tinytext DEFAULT NULL)")) {
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).join();
    }


    ScheduleService.BukkitPromise<String> getPlayerState(OfflinePlayer player){
        return scheduleService.callAsync(playerState, () -> {
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT state FROM PlayerState WHERE uuid = ?")){
                statement.setString(1, player.getUniqueId().toString());
                var set = statement.executeQuery();
                if (set.next()){
                    var state = set.getString(1);
                    return state.isEmpty() ? null : state;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return null;
        });
    }

    ScheduleService.BukkitPromise<Boolean> setPlayerState(OfflinePlayer player, @Nullable String state){
        return scheduleService.callAsync(playerState, () -> {
            try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO PlayerState VALUES (?,?) ON DUPLICATE KEY UPDATE state=?")){
                statement.setString(1, player.getUniqueId().toString());
                statement.setString(2, state == null ? "" : state);
                statement.setString(3, state == null ? "" : state);
                statement.execute();
                return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }
}
