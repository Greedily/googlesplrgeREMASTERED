package be.greedily.googlesplrge.games.tntrun.utils

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.scheduler.BukkitRunnable
import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.data.Constants
import be.greedily.googlesplrge.utils.AllPlayers
import be.greedily.googlesplrge.utils.Config
import be.greedily.googlesplrge.utils.countdown.utils.Countdown
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import java.io.File

object TNTRun {

    fun start() {
        Config.setActive(Constants.TNTRUN.GAME)//e
        TNTRunLetterHandler.handle()
    }

    fun setup() {
        Essentials.copyWorld(File(Bukkit.getWorldContainer(), Constants.TNTRUN.WORLDNAME), File(Bukkit.getWorldContainer(), Constants.TNTRUN.WORLDNAME_TEMP))
        Essentials.loadWorld(Constants.TNTRUN.WORLDNAME_TEMP)
        AllPlayers.teleport(Bukkit.getWorld(Constants.TNTRUN.WORLDNAME_TEMP)!!.spawnLocation)
        AllPlayers.setGameMode(GameMode.ADVENTURE)

        Countdown.start({ start() }, true)
    }

    fun stop() {
        AllPlayers.teleport(Bukkit.getWorld(Constants.WORLD.DEFAULT)!!.spawnLocation)

        val runnable = object: BukkitRunnable() {
            override fun run() {
                AllPlayers.teleport(Bukkit.getWorld(Constants.WORLD.DEFAULT)!!.spawnLocation)
                AllPlayers.setGameMode(GameMode.ADVENTURE)
                Essentials.deleteWorld(File(Bukkit.getWorldContainer(), Constants.TNTRUN.WORLDNAME_TEMP))
                Essentials.unloadWorld(Bukkit.getWorld(Constants.TNTRUN.WORLDNAME_TEMP))
                TNTRunLetterHandler.stophandling()
            }
        }.runTaskLater(Main.instance!!, 20)

        Config.setActive(Constants.GAME_STATUS.NO_GAME_ACTIVE)
    }

    fun command(args: Array<out String>?) {
        if(args!![0] == Constants.TNTRUN.GAME) {
            if(args[1] == Constants.COMMAND.START) {
                setup()
            }
            if(args[1] == Constants.COMMAND.STOP) {
                stop()
            }
        }
    }
}