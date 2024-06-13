package de.jonas.gamingloungemcplugin.customitems;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.jonas.stuff.utility.ItemBuilder;

public class Telepad {

    public Telepad() {
    }

    public static ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(Material.BEACON)
        .setName("Telepad")
        .whenPlaced("telepads:buildTelepad")
        .build();
    }

    public static ItemStack getItemForGui() {
        return new ItemBuilder()
        .setMaterial(Material.BEACON)
        .setName("Telepad")
        .addLoreLine("Material kosten:")
        .addLoreLine("4x Diamant")
        .addLoreLine("1x Compass")
        .addLoreLine("1x Ender Auge")
        .whenClicked("GamingLoungePlugin:craft_telepad")
        .build();
    }

}
