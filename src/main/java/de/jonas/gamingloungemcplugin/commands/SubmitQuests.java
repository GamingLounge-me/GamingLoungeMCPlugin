package de.jonas.gamingloungemcplugin.commands;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import de.jonas.gamingloungemcplugin.quests.MiningQuestOne;
import de.jonas.stuff.commandapi.CommandAPICommand;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class SubmitQuests {

    MiniMessage mm = MiniMessage.miniMessage();

    public SubmitQuests() {

        new CommandAPICommand("gaminglounge:submitquests")
        .withPermission("gaminglounge.questssubmit")
        .executesPlayer((player, commandArguments) -> {

            PersistentDataContainer container = player.getPersistentDataContainer();
            Inventory inv = player.getInventory();
            if (container.has(MiningQuestOne.onQuest)){
                int count = getAmountOfItems(inv, Material.IRON_INGOT);
                if (count < 16) {
                    player.sendMessage(mm.deserialize("Ich benötige 16 Eisenbarren."));
                } else {
                    removeAmountOfItems(inv, Material.IRON_INGOT, 16);
                    MiningQuestOne.complete(player);
                }
            } // here next "else if" 
            else player.sendMessage(mm.deserialize("Du hast keine Quest, wo du etwas bei mir abgeben oder vorzeigen must."));

        })
        .register();

    }

        // methods

    private int getAmountOfItems(Inventory inv, Material material) {
        int count = 0;
        for (ItemStack a : inv.getContents()) {
            if (a == null ) continue;
            if (!a.getType().equals(material)) continue;
            count += a.getAmount();
        }
        return count;
    }

    private int getAmountOfItems(Inventory inv, ItemStack item) {
        int count = 0;
        for (ItemStack a : inv.getContents()) {
            if (a == null ) continue;
            if (!a.equals(item)) continue;
            count += a.getAmount();
        }
        return count;
    }

    private void removeAmountOfItems(Inventory inv, Material material, int count) {
        int removed = 0;
        for (ItemStack a : inv.getContents()) {
            if (a == null ) continue;
            if (!a.getType().equals(material)) continue;
            if (removed >= count) return;
            if (count - removed < a.getAmount()) {
                a.setAmount(a.getAmount() - (count - removed));
                return;   
            }
            removed += a.getAmount();
            a.setAmount(0);
        }
    }

    private void removeAmountOfItems(Inventory inv, ItemStack item, int count) {
        int removed = 0;
        for (ItemStack a : inv.getContents()) {
            if (a == null ) continue;
            if (!a.equals(item)) continue;
            if (removed >= count) return;
            if (count - removed < a.getAmount()) {
                a.setAmount(a.getAmount() - (count - removed));
                return;  
            }
            removed += a.getAmount();
            a.setAmount(0);
        }
    }

}
