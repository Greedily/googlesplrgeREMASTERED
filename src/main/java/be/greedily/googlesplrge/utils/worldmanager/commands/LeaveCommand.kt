package be.greedily.googlesplrge.utils.worldmanager.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials

class LeaveCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(!sender.hasPermission("googlesplrge.world.switch")) return false
        if(sender !is Player) return false

        val player = sender.player!!
        val world = player.world
        val worldName = world.name

        player.teleport(Bukkit.getWorld("world")!!.spawnLocation)

        if(worldName == "world" || worldName == "world_the_end" || worldName == "world_nether" || worldName == "void") return false
        Essentials.unloadWorld(world)

        return false
    }
}