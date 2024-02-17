package be.greedily.googlesplrge.utils

import be.greedily.googlesplrge.Main

object Config {

    fun isActive(target: String): Boolean {
        if(target == Main.instance!!.config.getString(Main.instance!!.ACTIVE_PATH)) {
            return true
        }

        return false
    }

    fun setActive(target: String) {

        Main.instance!!.config.set(Main.instance!!.ACTIVE_PATH, target)

        return
    }

}