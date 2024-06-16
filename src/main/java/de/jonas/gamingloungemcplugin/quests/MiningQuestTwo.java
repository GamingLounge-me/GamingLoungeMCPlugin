package de.jonas.gamingloungemcplugin.quests;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.Questst;
import de.jonas.stuff.Stuff;
import de.jonas.stuff.interfaced.ClickEvent;
import de.jonas.stuff.utility.ItemBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiningQuestTwo {
    
    private static final ClickEvent CLICK = MiningQuestTwo::click;

    public static final NamespacedKey onQuest = new NamespacedKey("gamingloungequest", "mining_two");
    public static final NamespacedKey lastDone = new NamespacedKey("gamingloungequest", "mining_two_last_done");
    public static final NamespacedKey doneOnce = new NamespacedKey("gamingloungequest", "mining_two_done_once");

    public MiningQuestTwo() {
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CLICK, "GamingLoungeQuest:mining_two");
    }

    public ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(Material.FISHING_ROD)
        .setName("Der Miner [II]")
        .addLoreLine("Sammle ")
        .whenClicked("GamingLoungeQuest:mining_two")
        .build();
    }

    private static void click(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        p.closeInventory();
        p.getPersistentDataContainer().set(onQuest, PersistentDataType.INTEGER, 0);
        p.getPersistentDataContainer().set(Questst.mining, PersistentDataType.BOOLEAN, true);
        p.sendMessage("ok");
    }

    public static void complete(Player p) {
        MiniMessage mm = MiniMessage.miniMessage();
        p.getPersistentDataContainer().set(doneOnce, PersistentDataType.BOOLEAN, true);
        p.getPersistentDataContainer().set(lastDone, PersistentDataType.LONG, System.currentTimeMillis());
        p.getPersistentDataContainer().remove(Questst.mining);
        p.getPersistentDataContainer().remove(onQuest);
        p.sendMessage(mm.deserialize("Fertig"));
    }

}
