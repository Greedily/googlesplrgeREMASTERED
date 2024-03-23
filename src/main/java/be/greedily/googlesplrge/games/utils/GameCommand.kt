package be.greedily.googlesplrge.games.utils

import be.greedily.googlesplrge.data.Constants
import be.greedily.googlesplrge.games.acerace.AceRace
import be.greedily.googlesplrge.utils.Config
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GameCommand: CommandExecutor { //TabCompleter
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(args == null) return false
        if(sender !is Player) return false
        if(!sender.hasPermission(Constants.PERMISSION.GAMES)) return false
        val player = sender.player!!

        if(args[0] == "reset") {
            Config.setActive(Constants.GAME_STATUS.NO_GAME_ACTIVE)

            return false
        }
        if (args[0] == "checkpoint"){ // game checkpoint acerace 123
            if (args[2].lowercase() == Constants.ACERACE.GAME){
                AceRace.setCheckpointCommand(sender, args[3])
            }

            return false
        }

        if(args.size < 2) return false

        // Example: TNTRun.command(args)
        AceRace.command(args)


        return false
    }
}