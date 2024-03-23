package be.greedily.googlesplrge.data

object Constants {

    const val GLOBALCHECKPOINTDIST = 3

    object ACERACE {
        const val GAME = "acerace"
        const val WORLDNAME = "acerace"
        const val CHECKPOINTS = "acerace"

    }

    object WORLD {
        const val DEFAULT = "world"
        const val DEFAULT_END = "world_the_end"
        const val DEFAULT_NETHER = "world_nether"
        const val VOID = "void"
    }

    object GAME_STATUS {
        const val ACTIVE_PATH = "game.active"
        const val NO_GAME_ACTIVE = ""
    }

    object PERMISSION {
        const val GAMES = "googlesplrge.games"
        object WORLD {
            const val COPY = "googlesplrge.world.copy"
            const val DELETE = "googlesplrge.world.delete"
            const val SWITCH = "googlesplrge.world.switch"
        }
    }

    object COMMAND {
        const val START = "start"
        const val STOP = "stop"
    }

}