package de.jonas.gamingloungemcplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.quests.CraftTelepadQuest;
import de.jonas.gamingloungemcplugin.quests.FishingQuestOne;
import de.jonas.gamingloungemcplugin.quests.FishingQuestTwo;

public class Questst {

    List<ItemStack> list;

    public Questst(Player p) {
        list = new ArrayList<>();
        list.add(new CraftTelepadQuest().getItem());
        if (cooldown(p.getPersistentDataContainer(), FishingQuestOne.lastDone, 60)) list.add(new FishingQuestOne().getItem());
        if (p.getPersistentDataContainer().has(FishingQuestOne.doneOnce)) list.add(new FishingQuestTwo().getItem());
    }

    public List<ItemStack> getQuests() {
        return list;
    }

    private boolean cooldown(PersistentDataContainer container, NamespacedKey key, long cooldown) {
        cooldown *= 1000;
        if (!container.has(key)) return true;
        long amount = container.get(key, PersistentDataType.LONG);
        if ((System.currentTimeMillis() - amount) >= cooldown) return true;
        else return false;
    }
    
}