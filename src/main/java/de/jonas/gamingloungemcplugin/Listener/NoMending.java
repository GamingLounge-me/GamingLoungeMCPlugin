package de.jonas.gamingloungemcplugin.Listener;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class NoMending implements Listener{

    public void onMending(VillagerAcquireTradeEvent e) {
        if (e.getRecipe().getResult().getItemMeta().hasEnchant(Enchantment.MENDING)) {
            ItemMeta i = e.getRecipe().getResult().getItemMeta();
            i.removeEnchant(Enchantment.MENDING);
            // i.addEnchant(Enchantment.MENDING, 1, false);
            e.getRecipe().getResult().setItemMeta(i);
        }
    }

}
