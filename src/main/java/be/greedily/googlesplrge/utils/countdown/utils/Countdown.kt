package be.greedily.googlesplrge.utils.countdown.utils

import org.bukkit.scheduler.BukkitRunnable
import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.utils.AllPlayers
import be.greedily.googlesplrge.utils.Config

object Countdown {

    private const val START_TIME = 10
    private const val STOP_TIME = 0
    const val GAME = "countdown"
    var move = true

    fun start(func: ()-> Unit, moveArg: Boolean) {
        move = moveArg//e
        var currentTime = START_TIME

        Config.setActive(GAME)
        AllPlayers.setInvisible(true)

        val runnable = object: BukkitRunnable() {
            override fun run() {
                currentTime -= 1

                if(STOP_TIME >= currentTime) {
                    AllPlayers.setInvisible(false)
                    AllPlayers.broadcastTitle("§aStart!!!", "")
                    move = true
                    func()

                    this.cancel()
                }
                else {
                    AllPlayers.broadcastTitle("", "§b$currentTime")
                }
            }
        }.runTaskTimer(Main.instance!!, 0, 20)
    }

}