package de.jonas.gamingloungemcplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import de.jonas.stuff.api.GuiPlaceholder;
import de.jonas.stuff.utility.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public class Menu implements InventoryHolder{

    Inventory inv;

    public Menu(Player p) {
        inv = Bukkit.createInventory(this, 9*6, Component.text("Menu"));

        int[] filledSlots = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53};
        inv = new GuiPlaceholder(inv, filledSlots).getInventory();

        inv.setItem(4,
            new ItemBuilder()
                .setSkull(p.getUniqueId())
                .setName(p.teamDisplayName().decoration(TextDecoration.ITALIC, false))
                .whenClicked("GamingLoungeplugin:cancel_click_event")
                .build()
        );

        inv.setItem(49,
            new ItemBuilder()
            .setMaterial(Material.BARRIER)
            .setName("Schließen")
            .whenClicked("GamingLoungePlugin:close_click_event")
            .build()
        );

        inv.setItem(22,
            new ItemBuilder()
                .setMaterial(Material.BEACON)
                .setName("Mobiler Teleporter")
                .whenClicked("GamingLoungePlugin:open_mobile_telepad")
                .build()
        );

    }
    
    @Override
    public Inventory getInventory() {
        return inv;
    }

}
