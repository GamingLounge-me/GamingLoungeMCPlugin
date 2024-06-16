package de.jonas.gamingloungemcplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.customitems.Telepad;
import de.jonas.gamingloungemcplugin.quests.CraftTelepadQuest;
import de.jonas.stuff.Stuff;
import de.jonas.stuff.interfaced.ClickEvent;
import de.jonas.stuff.utility.PagenationInventory;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Events {

    private static final ClickEvent CRAFT_TELEPAD = Events::craftTelepadI;
    private static final ClickEvent CANCEL_CLICK_EVENT = Events::cancelInventoryClickEventI;
    private static final ClickEvent CLOSE_CLICK_EVENT = Events::closeInventoryOnClickI;
    private static final ClickEvent OPEN_MOBILE_TELEPAD = Events::openMobileTelepadI;

    public Events() {
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CANCEL_CLICK_EVENT, "GamingLoungeplugin:cancel_click_event");
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CRAFT_TELEPAD, "GamingLoungePlugin:craft_telepad");
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(CLOSE_CLICK_EVENT, "GamingLoungePlugin:close_click_event");
        Stuff.INSTANCE.itemBuilderManager.addClickEvent(OPEN_MOBILE_TELEPAD, "GamingLoungePlugin:open_mobile_telepad");
    }

    public static void cancelInventoryClickEventI(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof PagenationInventory) {
            e.setCancelled(true);
        }
    }

    public static void craftTelepadI(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        Inventory inv = p.getInventory();
        if (p.getInventory().firstEmpty() == -1) noInvSpace(p);
        else if (
            inv.contains(Material.DIAMOND, 4) &&
            inv.contains(Material.COMPASS, 1) &&
            inv.contains(Material.ENDER_EYE, 1) &&
            removeItem(inv, 4, Material.DIAMOND) &&
            removeItem(inv, 1, Material.COMPASS) &&
            removeItem(inv, 1, Material.ENDER_EYE)
            ) {
                inv.addItem(Telepad.getItem());
                if (
                    p.getPersistentDataContainer().has(CraftTelepadQuest.onQuest) &&
                    p.getPersistentDataContainer().get(CraftTelepadQuest.onQuest, PersistentDataType.BOOLEAN) == true
                ) CraftTelepadQuest.complete(p);
            }
        else noMaterials(p);
    }

    public static void closeInventoryOnClickI(InventoryClickEvent e) {
        e.setCancelled(true);
        e.getWhoClicked().closeInventory();
    }

    public static void openMobileTelepadI(InventoryClickEvent e) {
        e.setCancelled(true);
        e.getWhoClicked().closeInventory();
        Bukkit.dispatchCommand(e.getWhoClicked(), "pad");
    }

    public static void craft_chunkloader(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        Inventory inv = p.getInventory();
        if (p.getInventory().firstEmpty() == -1) noInvSpace(p);
        else if (
            inv.contains(Material.NETHER_STAR, 1) &&
            inv.contains(Material.REDSTONE_BLOCK, 16) &&
            removeItem(inv, 1, Material.NETHER_STAR) &&
            removeItem(inv, 16, Material.REDSTONE_BLOCK)
            ) Bukkit.getScheduler().runTaskLater(GamingLoungeMain.INSTANCE, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wildloaders:loader give " + p.getName() +" normal_loader 1"), 0);
        else noMaterials(p);
    }

    // Remove item

    public boolean removeItem(Inventory inv, int amount, ItemStack item) {
        if (inv.contains(item, amount)) {
            for (ItemStack a : inv.getContents()) {
                if (a == null) continue;
                if (a.equals(item)) {
                    if (a.getAmount() < amount) {
                        amount -= a.getAmount();
                        a.setAmount(0);
                        continue;
                    }
                    if (a.getAmount() >= amount) {
                        a.setAmount(a.getAmount() - amount);
                        return true;
                    }
                }
            }
        }   
        return false;
    }

    public static boolean removeItem(Inventory inv, int amount, Material material) {
        if (inv.contains(material, amount)) {
            for (ItemStack a : inv.getContents()) {
                if (a == null) continue;
                if (a.getType().equals(material)) {
                    if (a.getAmount() < amount) {
                        amount -= a.getAmount();
                        a.setAmount(0);
                        continue;
                    }
                    if (a.getAmount() >= amount) {
                        a.setAmount(a.getAmount() - amount);
                        return true;
                    }
                }
            }
        }   
        return false;
    }

    // Error messages

    public static void noInvSpace(Player p) {
        MiniMessage mm = MiniMessage.miniMessage();
        p.sendMessage(mm.deserialize("<red>Du hast nicht genügend Platz im Inventar.</red>"));
    }

    public static void noMaterials(Player p) {
        MiniMessage mm = MiniMessage.miniMessage();
        p.sendMessage(mm.deserialize("<red>Du hast nicht genügend Materialien im Inventar.</red>"));
    }

}
