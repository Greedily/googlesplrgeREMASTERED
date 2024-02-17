package be.greedily.googlesplrge.games.tntrun.utils

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.scheduler.BukkitRunnable
import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.utils.AllPlayers
import be.greedily.googlesplrge.utils.Config
import be.greedily.googlesplrge.utils.countdown.utils.Countdown
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import java.io.File

object TNTRun {

    const val GAME = "tnt"
    const val WORLDNAME = "tntrun"
    const val WORLDNAME_TEMP = "tntrun_temp"

    fun start() {
        Config.setActive(GAME)//e
        TNTRunLetterHandler.handle()
    }

    fun setup() {
        Essentials.copyWorld(File(Bukkit.getWorldContainer(), WORLDNAME), File(Bukkit.getWorldContainer(), WORLDNAME_TEMP))
        Essentials.loadWorld(WORLDNAME_TEMP)
        AllPlayers.teleport(Bukkit.getWorld(WORLDNAME_TEMP)!!.spawnLocation)
        AllPlayers.setGameMode(GameMode.ADVENTURE)

        Countdown.start({ start() }, true)
    }

    fun stop() {
        AllPlayers.teleport(Bukkit.getWorld("world")!!.spawnLocation)

        val runnable = object: BukkitRunnable() {
            override fun run() {
                AllPlayers.teleport(Bukkit.getWorld("world")!!.spawnLocation)
                AllPlayers.setGameMode(GameMode.ADVENTURE)
                Essentials.deleteWorld(File(Bukkit.getWorldContainer(), WORLDNAME_TEMP))
                Essentials.unloadWorld(Bukkit.getWorld(WORLDNAME_TEMP))
                TNTRunLetterHandler.stophandling()
            }
        }.runTaskLater(Main.instance!!, 20)

        Config.setActive(Main.instance!!.NO_GAME_ACTIVE)
    }

    fun command(args: Array<out String>?) {
        if(args!![0] == "tnt") {
            if(args[1] == "start") {
                setup()
            }
            if(args[1] == "stop") {
                stop()
            }
        }
    }
}