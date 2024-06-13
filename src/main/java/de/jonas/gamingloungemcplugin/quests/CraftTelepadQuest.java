package de.jonas.gamingloungemcplugin.quests;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.Questst;
import de.jonas.gamingloungemcplugin.customitems.Telepad;
import de.jonas.stuff.Stuff;
import de.jonas.stuff.interfaced.ClickEvent;
import de.jonas.stuff.utility.ItemBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class CraftTelepadQuest {

    private static final ClickEvent CLICK = CraftTelepadQuest::click;

    public static final NamespacedKey onQuest = new NamespacedKey("gamingloungequest", "craft_telepad");
    public static final NamespacedKey lastDone = new NamespacedKey("gamingloungequest", "telepad_last_done");
    public static final NamespacedKey doneOnce = new NamespacedKey("gamingloungequest", "telepad_done_once");

    public CraftTelepadQuest() {
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CLICK, "GamingLoungeQuest:craft_telepad");
    }

    public ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(Material.BEACON)
        .setName("Craft Telepad")
        .whenClicked("GamingLoungeQuest:craft_telepad")
        .build();
    }

    private static void click(InventoryClickEvent e) {
        MiniMessage mm = MiniMessage.miniMessage();
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        p.closeInventory();
        p.getPersistentDataContainer().set(onQuest, PersistentDataType.INTEGER, 0);
        p.getPersistentDataContainer().set(Questst.fish, PersistentDataType.BOOLEAN, true);
        p.sendMessage(mm.deserialize("<gray>[<green>Gilbert</green>]</gray> Gehe zur Schmiede und Stelle ein <gold>Telepad</gold> her."));
    }

    public static void complete(Player p) {
        MiniMessage mm = MiniMessage.miniMessage();
        p.getPersistentDataContainer().set(doneOnce, PersistentDataType.BOOLEAN, true);
        p.getPersistentDataContainer().set(lastDone, PersistentDataType.LONG, System.currentTimeMillis());
        p.getPersistentDataContainer().set(Questst.fish, PersistentDataType.BOOLEAN, false);
        p.sendMessage(mm.deserialize("<gray>[<green>Gilbert</green>]</gray> Du hast erfolgreich ein <gold>Telepad</gold> hergestellt, hier ist deine Belohnung."));
        p.getInventory().addItem(Telepad.getItem());
    }

}
