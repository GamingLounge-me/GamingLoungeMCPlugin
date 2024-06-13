package de.jonas.gamingloungemcplugin.quests;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.stuff.Stuff;
import de.jonas.stuff.interfaced.ClickEvent;
import de.jonas.stuff.utility.ItemBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class FishingQuestOne {

    private static final ClickEvent CLICK = FishingQuestOne::click;

    public static final NamespacedKey onQuest = new NamespacedKey("gamingloungequest", "fishing_one");
    public static final NamespacedKey lastDone = new NamespacedKey("gamingloungequest", "fishing_one_last_done");
    public static final NamespacedKey doneOnce = new NamespacedKey("gamingloungequest", "fishing_one_done_once");

    public FishingQuestOne() {
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CLICK, "GamingLoungeQuest:fishing_one");
    }

    public ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(Material.FISHING_ROD)
        .setName("Der Angler [I]")
        .addLoreLine("Angle 20 Fische")
        .whenClicked("GamingLoungeQuest:fishing_one")
        .build();
    }

    private static void click(InventoryClickEvent e) {
        MiniMessage mm = MiniMessage.miniMessage();
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        p.closeInventory();
        p.getPersistentDataContainer().set(onQuest, PersistentDataType.INTEGER, 0);
        p.sendMessage("ok");
    }

    public static void complete(Player p) {
        MiniMessage mm = MiniMessage.miniMessage();
        p.getPersistentDataContainer().set(doneOnce, PersistentDataType.BOOLEAN, true);
        p.getPersistentDataContainer().set(lastDone, PersistentDataType.LONG, System.currentTimeMillis());
        p.sendMessage(mm.deserialize("Fertig"));
    }

}

