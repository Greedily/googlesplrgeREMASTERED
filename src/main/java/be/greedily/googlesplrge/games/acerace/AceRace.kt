package be.greedily.googlesplrge.games.acerace

import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.data.Constants
import be.greedily.googlesplrge.utils.AllPlayers
import be.greedily.googlesplrge.utils.Config
import be.greedily.googlesplrge.utils.countdown.utils.Countdown
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.scheduler.BukkitRunnable
import java.io.File

object AceRace {

    fun start() {
        Config.setActive(Constants.ACERACE.GAME)
    }

    fun stop() {
        AllPlayers.teleport(Bukkit.getWorld(Constants.WORLD.DEFAULT)!!.spawnLocation)

        val runnable = object: BukkitRunnable() {
            override fun run() {
                AllPlayers.teleport(Bukkit.getWorld(Constants.WORLD.DEFAULT)!!.spawnLocation)
                AllPlayers.setGameMode(GameMode.ADVENTURE)
                Essentials.unloadWorld(Bukkit.getWorld(Constants.ACERACE.WORLDNAME))
            }
        }.runTaskLater(Main.instance!!, 20)

        Config.setActive(Constants.GAME_STATUS.NO_GAME_ACTIVE)
    }

    fun setup() {
        Essentials.loadWorld(Constants.ACERACE.WORLDNAME)
        AllPlayers.teleport(Bukkit.getWorld(Constants.ACERACE.WORLDNAME)!!.spawnLocation)
        AllPlayers.setGameMode(GameMode.ADVENTURE)

        Countdown.start({ start() }, true)
    }

    fun command(args: Array<out String>?) {
        if(args!![0] == Constants.ACERACE.GAME) {
            if(args[1] == Constants.COMMAND.START) {
                setup()
            }
            if(args[1] == Constants.COMMAND.STOP) {
                stop()
            }
        }
    }

}