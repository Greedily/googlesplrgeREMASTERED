package be.greedily.googlesplrge.games.utils

import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.data.Constants
import be.greedily.googlesplrge.games.acerace.AceRace
import be.greedily.googlesplrge.utils.Config
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GameCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(!sender.hasPermission(Constants.PERMISSION.GAMES)) return false
        if(sender !is Player) return false
        val player = sender.player!!
        if(args == null) return false

        if(args[0] == "reset") {
            Config.setActive(Constants.GAME_STATUS.NO_GAME_ACTIVE)
        }

        if(args.size < 2) return false

        // Example: TNTRun.command(args)
        AceRace.command(args)

        return false
    }
}