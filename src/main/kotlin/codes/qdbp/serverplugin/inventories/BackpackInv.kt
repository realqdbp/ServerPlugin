package codes.qdbp.serverplugin.inventories

import codes.qdbp.serverplugin.Serverplugin
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun Player.openBackpack(plugin: Serverplugin) {
    val storage = plugin.backpackStorage.storage
    val inventory = Bukkit.createInventory(this, 54, Component.text("${this.name}'s Backpack"))
    val items = storage.getList(this.name)
    items?.forEachIndexed{index, any -> inventory.setItem(index, (any ?: ItemStack(Material.AIR)) as ItemStack) }
    openInventory(inventory)
}