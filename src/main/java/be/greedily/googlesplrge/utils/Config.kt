package be.greedily.googlesplrge.utils

import be.greedily.googlesplrge.Main
import be.greedily.googlesplrge.data.Constants

object Config {

    fun isActive(target: String): Boolean {
        if(target == Main.instance!!.config.getString(Constants.GAME_STATUS.ACTIVE_PATH)) {
            return true
        }

        return false
    }

    fun setActive(target: String) {

        Main.instance!!.config.set(Constants.GAME_STATUS.ACTIVE_PATH, target)

        return
    }

}