package be.greedily.googlesplrge.utils.worldmanager.commands

import be.greedily.googlesplrge.data.Constants
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials

class LeaveCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(!sender.hasPermission(Constants.PERMISSION.WORLD.SWITCH)) return false
        if(sender !is Player) return false

        val player = sender.player!!
        val world = player.world
        val worldName = world.name

        player.teleport(Bukkit.getWorld(Constants.WORLD.DEFAULT)!!.spawnLocation)

        if(worldName == Constants.WORLD.DEFAULT || worldName == Constants.WORLD.DEFAULT_END || worldName == Constants.WORLD.DEFAULT_NETHER || worldName == Constants.WORLD.VOID) return false
        Essentials.unloadWorld(world)

        return false
    }
}