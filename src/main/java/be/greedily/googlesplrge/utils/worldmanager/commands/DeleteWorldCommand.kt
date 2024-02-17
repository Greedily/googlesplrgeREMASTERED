package be.greedily.googlesplrge.utils.worldmanager.commands

import be.greedily.googlesplrge.data.Constants
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import java.io.File

class DeleteWorldCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(!sender.hasPermission(Constants.PERMISSION.WORLD.DELETE)) return false
        if(args == null) return false
        if(args.isEmpty()) return false
        Essentials.deleteWorld(File(Bukkit.getWorldContainer(), args[0]))
        Essentials.unloadWorld(Bukkit.getWorld(args[0]))

        return false
    }
}