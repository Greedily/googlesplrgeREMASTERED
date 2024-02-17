package be.greedily.googlesplrge.utils.countdown.utils

import org.bukkit.event.player.PlayerMoveEvent
import be.greedily.googlesplrge.utils.Config

object CountdownListeners {

    fun movement(event: PlayerMoveEvent) {
        if(Config.isActive(Countdown.GAME) && !Countdown.move) {
            if (event.from.x != event.to.x || event.from.z != event.to.z) {
                event.isCancelled = true
            }
        }
    }

}