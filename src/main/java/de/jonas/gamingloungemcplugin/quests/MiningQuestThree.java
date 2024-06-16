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

public class MiningQuestThree {

    private static final ClickEvent CLICK = MiningQuestThree::click;

    public static final NamespacedKey onQuest = new NamespacedKey("gamingloungequest", "mining_three");
    public static final NamespacedKey lastDone = new NamespacedKey("gamingloungequest", "mining_three_last_done");
    public static final NamespacedKey doneOnce = new NamespacedKey("gamingloungequest", "mining_three_done_once");
    public static final NamespacedKey started = new NamespacedKey("gamingloungequest", "mining_three_started");

    public MiningQuestThree() {
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CLICK, "GamingLoungeQuest:mining_three");
    }

    public ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(Material.FISHING_ROD)
        .setName("Der Miner [III]")
        .addLoreLine("Sammle ")
        .whenClicked("GamingLoungeQuest:mining_three")
        .build();
    }

    private static void click(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        p.closeInventory();
        p.getPersistentDataContainer().set(onQuest, PersistentDataType.INTEGER, 0);
        p.getPersistentDataContainer().set(Questst.mining, PersistentDataType.BOOLEAN, true);
        p.getPersistentDataContainer().set(started, PersistentDataType.LONG, System.currentTimeMillis());
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

    public static void fail(Player p) {
        MiniMessage mm = MiniMessage.miniMessage();
        p.getPersistentDataContainer().remove(Questst.mining);
        p.getPersistentDataContainer().remove(onQuest);
        p.sendMessage(mm.deserialize("failed"));
    }

    
}
