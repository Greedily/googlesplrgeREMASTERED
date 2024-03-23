package be.greedily.googlesplrge.data

import be.greedily.googlesplrge.Main
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object CheckPointsConfig {
    private var file: File = File("")
    private var config: YamlConfiguration = YamlConfiguration()

    fun load() {
        file = File(Main.instance!!.dataFolder, "checkpoints.yml")

        if (!file.exists()) {
            Main.instance?.saveResource("checkpoints.yml", false)
        }

        config = YamlConfiguration()
        //config.options().parseComments(true)

        try {
            config.save(file)
            config.load(file)
            config.set("works", true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun save() {
        try {
            config.save(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getConfig(): YamlConfiguration {
        return config
    }

}