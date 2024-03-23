package be.greedily.googlesplrge

import be.greedily.googlesplrge.data.CheckPointsConfig
import org.bukkit.Bukkit
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import be.greedily.googlesplrge.games.utils.GameCommand
import be.greedily.googlesplrge.listeners.JoinListener
import be.greedily.googlesplrge.listeners.LeaveListener
import be.greedily.googlesplrge.listeners.MovementListener
import be.greedily.googlesplrge.listeners.PlayerDeathListener
import be.greedily.googlesplrge.utils.worldmanager.commands.CopyWorldCommand
import be.greedily.googlesplrge.utils.worldmanager.commands.DeleteWorldCommand
import be.greedily.googlesplrge.utils.worldmanager.commands.LeaveCommand
import be.greedily.googlesplrge.utils.worldmanager.commands.MapCommand
import be.greedily.googlesplrge.utils.worldmanager.utils.Essentials
import java.io.File
import java.io.IOException


class Main : JavaPlugin() { // tortel was here

    private var customConfigFile: File? = null
    private var customConfig: FileConfiguration? = null

    companion object {
        var instance: Main? = null
            private set;
    }

    override fun onEnable() {//e
        // Plugin startup logic
        saveConfig()
        CheckPointsConfig.load()

        instance = this

        getCommand("copyworld")?.setExecutor(CopyWorldCommand())
        getCommand("deleteworld")?.setExecutor(DeleteWorldCommand())

        getCommand("map")?.setExecutor(MapCommand())
        getCommand("leave")?.setExecutor(LeaveCommand())

        getCommand("game")?.setExecutor(GameCommand())

        listenerRegistration()

        Essentials.loadWorld("void")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        saveConfig()
        saveDefaultConfig()

        CheckPointsConfig.save()
    }

    private fun listenerRegistration() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(MovementListener(), this)
        pluginManager.registerEvents(PlayerDeathListener(), this)
        pluginManager.registerEvents(JoinListener(), this)
        pluginManager.registerEvents(LeaveListener(), this)
    }
}