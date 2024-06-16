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

public class MiningQuestOne {
    
    private static final ClickEvent CLICK = MiningQuestOne::click;

    public static final NamespacedKey onQuest = new NamespacedKey("gamingloungequest", "mining_one");
    public static final NamespacedKey lastDone = new NamespacedKey("gamingloungequest", "mining_one_last_done");
    public static final NamespacedKey doneOnce = new NamespacedKey("gamingloungequest", "mining_one_done_once");

    public MiningQuestOne() {
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CLICK, "GamingLoungeQuest:mining_one");
    }

    public ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(Material.FISHING_ROD)
        .setName("Der Miner [I]")
        .addLoreLine("Samle 16 Eisen (Gescmolzen)")
        .addLoreLine("Dieses wird dir endfernt!")
        .whenClicked("GamingLoungeQuest:mining_one")
        .build();
    }

    private static void click(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        p.closeInventory();
        p.getPersistentDataContainer().set(onQuest, PersistentDataType.BOOLEAN, true);
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
