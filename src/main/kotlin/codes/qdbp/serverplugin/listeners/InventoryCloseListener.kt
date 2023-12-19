package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.checkConfirmItemsCorrect
import codes.qdbp.serverplugin.misc.giveUpgrade
import codes.qdbp.serverplugin.misc.returnItemsOnFail
import codes.qdbp.serverplugin.misc.upgradeMenuHandlers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryCloseEvent.Reason

class InventoryCloseListener(val plugin: Serverplugin) : Listener {

    private val storage = plugin.backpackStorage.storage

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as Player
        val title = PlainTextComponentSerializer.plainText().serialize(event.view.title())

        if (event.view.title() == Component.text("${player.name}'s Backpack")) {
            storage.set(player.name, event.inventory.contents.asList())
            plugin.backpackStorage.save()
        } else if (event.view.title() == Component.text("Öffentlicher Backpack")) {
            storage.set("P-U-B-L-I-C", event.inventory.contents.asList())
            plugin.backpackStorage.save()
        } else if (title.contains("Choose")) {
            if (event.reason == Reason.PLAYER || event.reason == Reason.DISCONNECT) {
                InventoryClickEvent.getHandlerList().unregister(upgradeMenuHandlers[player.uniqueId]!!)
            }
        } else if (title.contains("Confirm")) {
            if (event.reason == Reason.PLAYER || event.reason == Reason.DISCONNECT) {
                InventoryClickEvent.getHandlerList().unregister(upgradeMenuHandlers[player.uniqueId]!!)
                if (event.reason != Reason.PLAYER) returnItemsOnFail(player)

                if (checkConfirmItemsCorrect(event.inventory)) {
                    player.inventory.addItem(giveUpgrade(event.inventory, plugin))
                } else {
                    returnItemsOnFail(player)
                }
            }
        }
    }
}