package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.Serverplugin
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class UseCmd(val plugin: Serverplugin) : TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?) =
        if (args?.size == 1) mutableListOf(
            "afk",
            "backpack",
            "craft",
            "enderchest",
            "freecam",
            "upgrade",
            "skipnight",
            "switchworld",
            "deaths",
            "enhancedEating",
            "enhancedSleep",
            "light",
            "doubleDoorOpening",
            "mapImage",
            "invisibleItemFrames",
            "enhancedTotems",
            "fastLeafDecay",
        ) else null

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (args.isNullOrEmpty()) return false
        if (sender !is Player) return false
        if (!sender.isOp) return false


        when (args[0]) {
            "afk" -> plugin.config.set("Features.useAFK", !plugin.config.getBoolean("Features.useAFK"))
            "backpack" -> plugin.config.set("Features.useBackpack", !plugin.config.getBoolean("Features.useBackpack"))
            "craft" -> plugin.config.set("Features.useCraft", !plugin.config.getBoolean("Features.useCraft"))
            "enderchest" -> plugin.config.set("Features.useEnderchest", !plugin.config.getBoolean("Features.useEnderchest"))
            "freecam" -> plugin.config.set("Features.useFreecam", !plugin.config.getBoolean("Features.useFreecam"))
            "skipNight" -> plugin.config.set("Features.useSkipNight", !plugin.config.getBoolean("Features.useSkipNight"))
            "switchWorld" -> plugin.config.set("Features.useSwitchWorld", !plugin.config.getBoolean("Features.useSwitchWorld"))
            "deaths" -> plugin.config.set("Features.useDeaths", !plugin.config.getBoolean("Features.useDeaths"))
            "eating" -> plugin.config.set("Features.useEating", !plugin.config.getBoolean("Features.useEating"))
            "sleep" -> plugin.config.set("Features.useSleep", !plugin.config.getBoolean("Features.useSleep"))
            "lightRecipe" -> plugin.config.set("Features.useLightRecipe", !plugin.config.getBoolean("Features.useLightRecipe"))
            "doubleDoors" -> plugin.config.set("Features.useDoubleOpenDoors", !plugin.config.getBoolean("Features.useDoubleOpenDoors"))
            "customMapImages" -> plugin.config.set("Features.useCustomMapImages", !plugin.config.getBoolean("Features.useCustomMapImages"))
            "upgrade" -> plugin.config.set("Features.useUpgrade", !plugin.config.getBoolean("Features.useUpgrade"))
            "invisibleItemFrames" -> plugin.config.set("Features.useInvisibleItemFrames", !plugin.config.getBoolean("Features.useInvisibleItemFrames"))
            "changeUpgrade" -> plugin.config.set("Features.useChangeUpgrade", !plugin.config.getBoolean("Features.useChangeUpgrade"))
            "enhancedTotems" -> plugin.config.set("Features.useTotems", !plugin.config.getBoolean("Features.useTotems"))
            "fastLeafDecay" -> plugin.config.set("Features.useFastLeafDecay", !plugin.config.getBoolean("Features.useFastLeafDecay"))
            else -> return false
        }
        plugin.saveConfig()

        sender.sendPlainMessage("Reloading Server")
        Bukkit.getServer().reload()
        return true
    }
}