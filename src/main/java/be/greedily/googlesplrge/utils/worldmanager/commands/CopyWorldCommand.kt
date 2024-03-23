package be.greedily.googlesplrge.utils.worldmanager.commands

import be.greedily.googlesplrge.data.Constants
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import java.io.File




class CopyWorldCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(!sender.hasPermission(Constants.PERMISSION.WORLD.COPY)) return false
        if(args == null) return false
        if(args.size < 2) return false
        if(Bukkit.getWorld(args[0]) == null) return false
        val world = Bukkit.getWorld(args[0])
        val copiedworldname = args[1]

        Essentials.copyWorld(world!!.worldFolder, File(Bukkit.getWorldContainer(), copiedworldname))
        Essentials.loadWorld(copiedworldname)

        sender.sendMessage("Copied ${copiedworldname} succesfully!!! yey")
        return false // save world command, saves to schematics folder
    }


}