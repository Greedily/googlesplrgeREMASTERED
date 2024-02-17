package be.greedily.googlesplrge.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import be.greedily.googlesplrge.utils.MidGameJoinLeave

class LeaveListener: Listener {//e

    @EventHandler
    fun onLeave(event: PlayerQuitEvent) {
        MidGameJoinLeave.leaveListener(event)
    }

}