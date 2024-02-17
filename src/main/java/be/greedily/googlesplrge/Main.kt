package be.greedily.googlesplrge

import org.bukkit.Bukkit
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import be.greedily.googlesplrge.games.tntrun.utils.TNTRunListeners
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


class Main : JavaPlugin() {

    val ACTIVE_PATH = "game.active"
    val NO_GAME_ACTIVE = ""

    private var customConfigFile: File? = null
    private var customConfig: FileConfiguration? = null

    companion object {
        var instance: Main? = null
            private set;
    }

    override fun onEnable() {//e
        // Plugin startup logic
        createCustomConfig();

        instance = this

        getCommand("copyworld")?.setExecutor(CopyWorldCommand())
        getCommand("deleteworld")?.setExecutor(DeleteWorldCommand())

        getCommand("map")?.setExecutor(MapCommand())
        getCommand("leave")?.setExecutor(LeaveCommand())

        getCommand("game")?.setExecutor(GameCommand())

        listenerRegistration()

        Essentials.loadWorld("void")

        val runnable = object: BukkitRunnable() {
            override fun run() {
                TNTRunListeners.independentMovement()
            }
        }.runTaskTimer(this, 1, 1)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        saveConfig()
        saveDefaultConfig()
        saveResource("custom.yml", false)
    }

    private fun createCustomConfig() {
        customConfigFile = File(dataFolder, "custom.yml")
        if (!customConfigFile?.exists()!!) {
            customConfigFile!!.parentFile.mkdirs()
            saveResource("custom.yml", false)
        }
        customConfig = YamlConfiguration()
        try {
            (customConfig as YamlConfiguration).load(customConfigFile!!)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
        /* User Edit:
            Instead of the above Try/Catch, you can also use
            YamlConfiguration.loadConfiguration(customConfigFile)
        */
    }

    private fun listenerRegistration() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(MovementListener(), this)
        pluginManager.registerEvents(PlayerDeathListener(), this)
        pluginManager.registerEvents(JoinListener(), this)
        pluginManager.registerEvents(LeaveListener(), this)
    }
}