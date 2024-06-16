package de.jonas.gamingloungemcplugin.quests;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.customitems.Telepad;
import de.jonas.stuff.Stuff;
import de.jonas.stuff.interfaced.ClickEvent;
import de.jonas.stuff.utility.ItemBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class CraftTelepadQuest {

    private static final ClickEvent CLICK = CraftTelepadQuest::click;

    public static final NamespacedKey onQuest = new NamespacedKey("gamingloungequest", "craft_telepad");

    public CraftTelepadQuest() {
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CLICK, "GamingLoungeQuest:craft_telepad");
    }

    public ItemStack getItem() {
        return new ItemBuilder()
        .setMaterial(Material.BEACON)
        .setName("Stelle Telepad her")
        .addLoreLine("Gehe zur schmiede und stelle ein Telepad her.")
        .whenClicked("GamingLoungeQuest:craft_telepad")
        .build();
    }

    private static void click(InventoryClickEvent e) {
        MiniMessage mm = MiniMessage.miniMessage();
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        p.closeInventory();
        p.getPersistentDataContainer().set(onQuest, PersistentDataType.BOOLEAN, true);
        p.sendMessage(mm.deserialize("<gray>[<green>Gilbert</green>]</gray> Gehe zur Schmiede und Stelle ein <gold>Telepad</gold> her."));
    }

    public static void complete(Player p) {
        MiniMessage mm = MiniMessage.miniMessage();
        p.sendMessage(mm.deserialize("<gray>[<green>Gilbert</green>]</gray> Du hast erfolgreich ein <gold>Telepad</gold> hergestellt, hier ist deine Belohnung."));
        p.getInventory().addItem(Telepad.getItem());
    }

}
