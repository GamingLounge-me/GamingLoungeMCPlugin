package de.jonas.gamingloungemcplugin.customitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.jonas.stuff.utility.ItemBuilder;

// The Chunkloader is from "Wild Loaders"

public class ChunkLoader {

        public ChunkLoader() {
    }

    public static ItemStack getItemForGui() {
        return new ItemBuilder()
        .setMaterial(Material.BEACON)
        .setName("Chubkloader")
        .addLoreLine("Material kosten:")
        .addLoreLine("1x Netherstar")
        .addLoreLine("16x Redstoneblöcke")
        .whenClicked("GamingLoungePlugin:craft_chunkloader")
        .build();
    }
    
}
