package de.jonas.gamingloungemcplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import de.jonas.gamingloungemcplugin.customitems.ChunkLoader;
import de.jonas.gamingloungemcplugin.customitems.Telepad;

public final class CustomItemsIndex {

    List<ItemStack> list;
    
    public CustomItemsIndex() {
        list = new ArrayList<>();
        list.add(Telepad.getItemForGui());
        list.add(ChunkLoader.getItemForGui());
    }
    
    public List<ItemStack> getAll() {
        return list;
    }

}
