package de.jonas.gamingloungemcplugin.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.quests.FishingQuestOne;
import de.jonas.gamingloungemcplugin.quests.FishingQuestTree;
import de.jonas.gamingloungemcplugin.quests.FishingQuestTwo;
import de.jonas.gamingloungemcplugin.quests.MiningQuestThree;
import de.jonas.gamingloungemcplugin.quests.MiningQuestTwo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class QuestsListener implements Listener{

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        if (e.getCaught() == null) return;
        Player p = e.getPlayer();
        PersistentDataContainer container = p.getPersistentDataContainer();
        if (container.has(FishingQuestOne.onQuest)) {
            int count = container.get(FishingQuestOne.onQuest, PersistentDataType.INTEGER) + 1;
            if (count < 20) {
                container.set(FishingQuestOne.onQuest, PersistentDataType.INTEGER, count);
                p.sendActionBar(Component.text(count + " von " + 20));
            } else FishingQuestOne.complete(p);
        } else if (container.has(FishingQuestTwo.onQuest)) {
            int count = container.get(FishingQuestTwo.onQuest, PersistentDataType.INTEGER) + 1;
            if (count < 50) {
                container.set(FishingQuestTwo.onQuest, PersistentDataType.INTEGER, count);
                p.sendActionBar(Component.text(count + " von " + 50));
            } else FishingQuestTwo.complete(p);
        } else if (container.has(FishingQuestTree.onQuest)) {
            int count = container.get(FishingQuestTree.onQuest, PersistentDataType.INTEGER) + 1;
            if (count < 200) {
                container.set(FishingQuestTree.onQuest, PersistentDataType.INTEGER, count);
                p.sendActionBar(Component.text(count + " von " + 200));
            } else FishingQuestTree.complete(p);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        MiniMessage mm = MiniMessage.miniMessage();
        Block b = e.getBlock();
        Player p = e.getPlayer();
        PersistentDataContainer c = p.getPersistentDataContainer();

        if (c.has(MiningQuestTwo.onQuest)) {
            if (
                b.getType().equals(Material.DEEPSLATE_DIAMOND_ORE) ||
                b.getType().equals(Material.DIAMOND_ORE)
            ) {
                int count = c.get(MiningQuestTwo.onQuest, PersistentDataType.INTEGER) + 1;
                c.set(MiningQuestTwo.onQuest, PersistentDataType.INTEGER, count);
                if (count >= 16) MiningQuestTwo.complete(p);
                p.sendActionBar(mm.deserialize(count + " von " + 16));
            }
        } else if (c.has(MiningQuestThree.onQuest)) {
            if (maxTimeOffset(c.get(MiningQuestThree.started, PersistentDataType.LONG), 3600)) {
                p.sendMessage(mm.deserialize("<red>Deine zeit ist abgelaufen!</red>"));
                MiningQuestThree.fail(p);
                return;
            }
            if (
                b.getType().equals(Material.DEEPSLATE_DIAMOND_ORE) ||
                b.getType().equals(Material.DIAMOND_ORE)
            ) {
                int count = c.get(MiningQuestThree.onQuest, PersistentDataType.INTEGER) + 1;
                c.set(MiningQuestTwo.onQuest, PersistentDataType.INTEGER, count);
                if (count >= 64) MiningQuestThree.complete(p);
                p.sendActionBar(mm.deserialize(count + " von " + 64));
            }
        }

    }

    // methods

    private boolean maxTimeOffset(long startLONG, int maxOffsetSEC) {
        long diff = System.currentTimeMillis() - startLONG;
        diff /= 1000;
        return diff >= maxOffsetSEC;
    }

}
