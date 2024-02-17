package be.greedily.googlesplrge.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathListener: Listener {//e

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        // Example: TNTRunListeners.death(event)
    }

}