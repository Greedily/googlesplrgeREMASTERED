package be.greedily.googlesplrge.utils.worldmanager.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import java.io.File

class MapCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(!sender.hasPermission("googlesplrge.world.switch")) return false
        if(sender !is Player) return false

        val player = sender.player!!

        if(args == null) return false
        if(args.isEmpty()) return false
        if(!File(Bukkit.getWorldContainer(), args[0]).exists()) return false
        if(Bukkit.getWorld(args[0]) == null) {
            Essentials.loadWorld(args[0])
        }

        val runnable = object: BukkitRunnable() {
            override fun run() {
                player.teleport(Bukkit.getWorld(args[0])!!.spawnLocation)
            }
        }.runTaskLater(Main.instance!!, 10)

        return false
    }


}