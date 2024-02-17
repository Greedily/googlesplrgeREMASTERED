package be.greedily.googlesplrge.utils

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object MidGameJoinLeave {

    fun joinListener(event: PlayerJoinEvent) {
        if(Config.isActive("")) {
            event.player.gameMode = GameMode.ADVENTURE
            event.player.teleport(Bukkit.getWorld("world")!!.spawnLocation)
        }
        else {

            event.player.gameMode = GameMode.SPECTATOR
            if (AllPlayers.getPlayerInGM(GameMode.ADVENTURE) != null) {
                event.player.teleport(AllPlayers.getPlayerInGM(GameMode.ADVENTURE)!!.location)
            } else {
                if (AllPlayers.getPlayerInGM(GameMode.SURVIVAL) != null) {
                    event.player.teleport(AllPlayers.getPlayerInGM(GameMode.SURVIVAL)!!.location)
                }
            }
        }
    }

    fun leaveListener(event: PlayerQuitEvent) {
        if(Config.isActive("")) return

        event.player.health = 0.0
    }

}