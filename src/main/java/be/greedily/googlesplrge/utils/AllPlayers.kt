package be.greedily.googlesplrge.utils

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player

object AllPlayers {

    fun teleport(loc: Location) {
        for (player in Bukkit.getOnlinePlayers()) {
            player.teleport(loc)
        }
    }

    fun broadcastTitle(title: String?, subtitle: String?) {
        for (player in Bukkit.getOnlinePlayers()) {
            player.sendTitle(title, subtitle)
        }
    }

    fun setInvisible(boolean: Boolean) {
        for (player in Bukkit.getOnlinePlayers()) {
            player.isInvisible = boolean
        }
    }

    fun setGameMode(gameMode: GameMode) {
        for (player in Bukkit.getOnlinePlayers()) {
            player.gameMode = gameMode
        }
    }

    fun getPlayersInGMnum(gamemode: GameMode): Int {
        var result = 0
        for (player in Bukkit.getOnlinePlayers()){
            if (gamemode == player.gameMode){
                result += 1

            }
        }
        return result
    }

    fun getPlayerInGM(gameMode: GameMode): Player? {
        for (player in Bukkit.getOnlinePlayers()) {
            if(gameMode == player.gameMode) {
                return player
            }
        }

        return null
    }

}