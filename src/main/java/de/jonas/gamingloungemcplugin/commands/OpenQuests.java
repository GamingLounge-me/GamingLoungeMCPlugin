package de.jonas.gamingloungemcplugin.commands;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import de.jonas.gamingloungemcplugin.Questst;
import de.jonas.stuff.commandapi.CommandAPICommand;
import de.jonas.stuff.utility.PagenationInventory;

public class OpenQuests {

        public OpenQuests() {

        new CommandAPICommand("gaminglounge:open_quests")
            .withPermission("gaminglounge.debug")
            .executesPlayer((player, commandArguments) -> {
                List<ItemStack> list = new Questst(player).getQuests();
                player.openInventory(new PagenationInventory(list).getInventory());
            })
            .register();
    }
    
}
