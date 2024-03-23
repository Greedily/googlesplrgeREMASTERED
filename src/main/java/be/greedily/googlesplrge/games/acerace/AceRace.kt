package be.greedily.googlesplrge.games.acerace

import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.data.CheckPointsConfig
import be.greedily.googlesplrge.data.Constants
import be.greedily.googlesplrge.utils.AllPlayers
import be.greedily.googlesplrge.utils.Config
import be.greedily.googlesplrge.utils.countdown.utils.Countdown
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.scheduler.BukkitRunnable

object AceRace {
    var playerCheckPoints = mutableMapOf<String, Int>()

    fun start() {
        Config.setActive(Constants.ACERACE.GAME)
    }

    fun stop() {
        AllPlayers.teleport(Bukkit.getWorld(Constants.WORLD.DEFAULT)!!.spawnLocation)
        Bukkit.getOnlinePlayers().forEach { playerCheckPoints.remove(it.name) }
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

        Bukkit.getOnlinePlayers().forEach { playerCheckPoints.plus(Pair(it.name, 0)) }

        Countdown.start({ start() }, false)
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

    fun setCheckpointCommand(plr: Player,number : String){
        val checkpointsConfig = CheckPointsConfig.getConfig()
        val loc = plr.location
        checkpointsConfig.set("${Constants.ACERACE.CHECKPOINTS}.${number}.x", loc.x) // game acerace checkpoint 1
        checkpointsConfig.set("${Constants.ACERACE.CHECKPOINTS}.${number}.y", loc.y)
        checkpointsConfig.set("${Constants.ACERACE.CHECKPOINTS}.${number}.z", loc.z)

        Main.instance!!.saveConfig()
    }

    fun checkForCheckPoints(event : PlayerMoveEvent){
        val checkpointsConfig = CheckPointsConfig.getConfig()

        val plr = event.player
        val prefix = Constants.ACERACE.CHECKPOINTS
        val plrCheckpoint = playerCheckPoints[plr.name]!!
        val nextCheckpoint : Location = Location(Bukkit.getWorld(Constants.ACERACE.WORLDNAME),
            checkpointsConfig.getDouble("${prefix}.${plrCheckpoint + 1}.x"),
            checkpointsConfig.getDouble("${prefix}.${plrCheckpoint + 1}.y"),
            checkpointsConfig.getDouble("${prefix}.${plrCheckpoint + 1}.z")
        )

        if(plr.location.distance(nextCheckpoint) <= Constants.GLOBALCHECKPOINTDIST) {
            playerCheckPoints.replace(plr.name, plrCheckpoint+1)
            return
        }


    }

}