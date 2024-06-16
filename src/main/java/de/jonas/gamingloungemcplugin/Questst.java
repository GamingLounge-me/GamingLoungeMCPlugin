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
import de.jonas.gamingloungemcplugin.quests.FishingQuestTree;
import de.jonas.gamingloungemcplugin.quests.FishingQuestTwo;
import de.jonas.gamingloungemcplugin.quests.MiningQuestOne;
import de.jonas.gamingloungemcplugin.quests.MiningQuestThree;
import de.jonas.gamingloungemcplugin.quests.MiningQuestTwo;

public class Questst {

    public static final NamespacedKey fish = new NamespacedKey("gamingloungequestsindex", "fish");
    public static final NamespacedKey mining = new NamespacedKey("gamingloungequestsindex", "mining");

    List<ItemStack> list;

    public Questst(Player p) {
        PersistentDataContainer container = p.getPersistentDataContainer();
        list = new ArrayList<>();
        // Craft Telepad Quests
        if (
            !container.has(CraftTelepadQuest.onQuest)
        ) list.add(new CraftTelepadQuest().getItem());

        // Fishing Quests
        if (
            cooldown(container, FishingQuestOne.lastDone, 60) &&
            !container.has(fish) &&
            !container.has(FishingQuestOne.onQuest)
        ) list.add(new FishingQuestOne().getItem());
        if (
            cooldown(container, FishingQuestTwo.lastDone, 60) &&
            container.has(FishingQuestOne.doneOnce) &&
            !container.has(fish) &&
            !container.has(FishingQuestTwo.onQuest)
            ) list.add(new FishingQuestTwo().getItem());
        if (
            cooldown(container, FishingQuestTwo.lastDone, 60) &&
            container.has(FishingQuestTree.doneOnce) &&
            !container.has(fish) &&
            !container.has(FishingQuestTree.onQuest)
        ) list.add(new FishingQuestTree().getItem());

        // Mining Quests
        if (
            cooldown(container, MiningQuestOne.lastDone, 60) &&
            !container.has(mining) &&
            !container.has(MiningQuestOne.onQuest)
        ) list.add(new MiningQuestOne().getItem());
        if (
            cooldown(container, MiningQuestTwo.lastDone, 60) &&
            container.has(MiningQuestOne.doneOnce) &&
            !container.has(mining) &&
            !container.has(MiningQuestTwo.onQuest)
        ) list.add(new MiningQuestTwo().getItem());
        if (
            cooldown(container, MiningQuestThree.lastDone, 60) &&
            container.has(MiningQuestTwo.doneOnce) &&
            !container.has(mining) &&
            !container.has(MiningQuestThree.onQuest)
        ) list.add(new MiningQuestThree().getItem());
    }

    public List<ItemStack> getQuests() {
        return list;
    }

    private boolean cooldown(PersistentDataContainer container, NamespacedKey key, long cooldown) {
        cooldown *= 1000;
        if (!container.has(key)) return true;
        long amount = container.get(key, PersistentDataType.LONG);
        return (System.currentTimeMillis() - amount) >= cooldown;
    }
    
}