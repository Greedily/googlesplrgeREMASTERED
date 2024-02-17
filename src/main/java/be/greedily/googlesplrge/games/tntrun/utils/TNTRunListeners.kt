package be.greedily.googlesplrge.games.tntrun.utils

import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.NumberConversions
import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.utils.AllPlayers
import be.greedily.googlesplrge.utils.Config


object TNTRunListeners {

    fun movement(event: PlayerMoveEvent) {
        if(Config.isActive(TNTRun.GAME)) {
            val player = event.player
            player.saturation = 20.0f
            player.foodLevel = 20
            if(player.location.y <= 11.5) {
                val blockUnder = getBlockUnderPlayer(9, player.location)


                if (blockUnder != null) {
                    if(blockUnder.type == Material.TNT) {
                        val runnable = object : BukkitRunnable() {
                            override fun run() {
                                blockUnder.type = Material.AIR
                            }
                        }.runTaskLater(Main.instance!!, 5)//e
                    }
                }
            }
            if(player.location.y <= -10) {
                player.health = 0.0
            }
        }
    }

    fun death(event: PlayerDeathEvent) {
        if(!Config.isActive(TNTRun.GAME)) return
        event.player.gameMode = GameMode.SPECTATOR
        val remainingPlayers = AllPlayers.getPlayersInGMnum(GameMode.ADVENTURE)
        if(remainingPlayers <= 1) {
            TNTRun.stop()
        }
    }

    fun independentMovement() {
        if(Config.isActive(TNTRun.GAME)) {
            for (player in Bukkit.getOnlinePlayers()) {
                if(player.location.y <= 11.1) {
                    val blockUnder = getBlockUnderPlayer(9, player.location)


                    if (blockUnder != null) {
                        if(blockUnder.type == Material.TNT) {
                            val runnable = object : BukkitRunnable() {
                                override fun run() {
                                    blockUnder.type = Material.AIR
                                }
                            }.runTaskLater(Main.instance!!, 5)
                        }
                    }
                }
            }
        }
    }

    private class PlayerPosition(private val x: Double, private val y: Int, private val z: Double) {
        fun getBlock(world: World, addx: Double, addz: Double): Block {
            return world.getBlockAt(NumberConversions.floor(x + addx), y, NumberConversions.floor(z + addz))
        }
    }

    private const val PLAYER_BOUNDINGBOX_ADD = 0.3

    private fun getBlockUnderPlayer(y: Int, location: Location): Block? {
        val loc = PlayerPosition(location.x, y, location.z)
        val b11: Block = loc.getBlock(location.world, +PLAYER_BOUNDINGBOX_ADD, -PLAYER_BOUNDINGBOX_ADD)
        if (b11.type !== Material.AIR) {
            return b11
        }
        val b12: Block = loc.getBlock(location.world, -PLAYER_BOUNDINGBOX_ADD, +PLAYER_BOUNDINGBOX_ADD)
        if (b12.type !== Material.AIR) {
            return b12
        }
        val b21: Block = loc.getBlock(location.world, +PLAYER_BOUNDINGBOX_ADD, +PLAYER_BOUNDINGBOX_ADD)
        if (b21.type !== Material.AIR) {
            return b21
        }
        val b22: Block = loc.getBlock(location.world, -PLAYER_BOUNDINGBOX_ADD, -PLAYER_BOUNDINGBOX_ADD)
        return if (b22.getType() !== Material.AIR) {
            b22
        } else null
    }

}