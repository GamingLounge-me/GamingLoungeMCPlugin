package de.jonas.gamingloungemcplugin.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.quests.FishingQuestOne;
import de.jonas.gamingloungemcplugin.quests.FishingQuestTwo;
import net.kyori.adventure.text.Component;

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
        }
        if (container.has(FishingQuestTwo.onQuest)) {
            int count = container.get(FishingQuestTwo.onQuest, PersistentDataType.INTEGER) + 1;
            if (count < 50) {
                container.set(FishingQuestTwo.onQuest, PersistentDataType.INTEGER, count);
                p.sendActionBar(Component.text(count + " von " + 50));
            } else FishingQuestTwo.complete(p);
        }
    }
    
}
