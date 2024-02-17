package be.greedily.googlesplrge.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import be.greedily.googlesplrge.utils.MidGameJoinLeave

class JoinListener: Listener {//e

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        MidGameJoinLeave.joinListener(event)
    }

}