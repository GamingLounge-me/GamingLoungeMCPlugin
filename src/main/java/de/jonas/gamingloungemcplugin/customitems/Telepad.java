package de.jonas.gamingloungemcplugin.customitems;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.jonas.stuff.utility.ItemBuilder;

public class Telepad {


    Material material;
    String name;

    public Telepad() {
        material = Material.BEACON;
        name = "Telepad";
    }

    public ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(material)
        .setName(name)
        .whenPlaced("telepads:buildTelepad")
        .build();
    }

    public ItemStack getItemForGui() {
        return new ItemBuilder()
        .setMaterial(material)
        .setName(name)
        .addLoreLine("Material kosten:")
        .addLoreLine("4x Diamant")
        .addLoreLine("1x Compass")
        .addLoreLine("1x Ender Auge")
        .whenClicked("GamingLoungePlugin:craft_telepad")
        .build();
    }

}
