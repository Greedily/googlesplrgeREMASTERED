package be.greedily.googlesplrge.games.tntrun.utils

import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import be.greedily.googlesplrge.Main

object TNTRunLetterHandler {
    var GameTimeSeconds = 0
    var enabled = true

    fun handle() {//follow
        enabled = true
        val run = object : BukkitRunnable() {
            override fun run() {
                if (GameTimeSeconds / 60 == 1/2){// start after 2 minutes
                    val chosenchar = getRandomString(1, "lo")

                    Bukkit.broadcastMessage("Type : ${chosenchar}")
                }

                GameTimeSeconds += 1
                if (!enabled) {
                    this.cancel()
                }
            }
        }.runTaskTimer(Main.instance!!, 1, 40)// maybe it depends on difficulity

    }

    fun stophandling() {
        enabled = false
    }

    fun getRandomString(length: Int, hardness : String) : String {
        val hardChars = ('A'..'Z')
        val easychars = ('a'..'z')
       // val allowedChars = ('A'..'Z') + ('a'..'z')

        return (1..length)
                .map { easychars.random() }
                .joinToString("")
    }

}