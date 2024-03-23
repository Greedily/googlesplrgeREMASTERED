package be.greedily.googlesplrge.listeners

import be.greedily.googlesplrge.games.acerace.AceRace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import be.greedily.googlesplrge.utils.countdown.utils.CountdownListeners

class MovementListener: Listener {//e

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        CountdownListeners.movement(event)
        AceRace.checkForCheckPoints(event)
        // Example: TNTRunListeners.movement(event)
    }

}