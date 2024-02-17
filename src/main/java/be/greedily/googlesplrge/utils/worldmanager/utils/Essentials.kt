package be.greedily.googlesplrge.utils.worldmanager.utils

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import java.io.*
import java.util.*


object Essentials {

    fun unloadWorld(world: World?) {
        if (world != null) {
            world.save()
            for (p in world.players) {
                p.teleport(Bukkit.getWorlds()[0].spawnLocation)
            }
            Bukkit.getServer().unloadWorld(world, false)
            println(world.name + " unloaded!")
        }
    }

    fun deleteWorld(path: File): Boolean {
        if (path.exists()) {
            val files = path.listFiles()
            for (i in files.indices) {
                if (files[i].isDirectory) {
                    deleteWorld(files[i])
                } else {
                    files[i].delete()
                }
            }
        }
        return path.delete()
    }

    fun copyWorld(source: File, target: File) {
        try {
            val ignore: ArrayList<String> = ArrayList<String>(Arrays.asList("uid.dat", "session.dat"))
            if (!ignore.contains(source.name)) {
                if (source.isDirectory) {
                    if (!target.exists()) target.mkdirs()
                    val files = source.list()
                    for (file in files) {
                        val srcFile = File(source, file)
                        val destFile = File(target, file)
                        copyWorld(srcFile, destFile)
                    }
                } else {
                    val `in`: InputStream = FileInputStream(source)
                    val out: OutputStream = FileOutputStream(target)
                    val buffer = ByteArray(1024)
                    var length: Int
                    while (`in`.read(buffer).also { length = it } > 0) out.write(buffer, 0, length)
                    `in`.close()
                    out.close()
                }
            }
        } catch (e: IOException) {
        }
    }

    fun loadWorld(target: String) {
        val w = Bukkit.createWorld(WorldCreator(target))
    }



}