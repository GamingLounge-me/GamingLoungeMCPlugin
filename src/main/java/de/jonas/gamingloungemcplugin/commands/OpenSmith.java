package de.jonas.gamingloungemcplugin.commands;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import de.jonas.gamingloungemcplugin.GamingLoungeMain;
import de.jonas.stuff.utility.PagenationInventory;
import dev.jorel.commandapi.CommandAPICommand;

public class OpenSmith {

    public OpenSmith() {

        new CommandAPICommand("gaminglounge:open_smith")
            .withPermission("gaminglounge.debug")
            .executesPlayer((player, commandArguments) -> {
                List<ItemStack> list = GamingLoungeMain.INSTANCE.customitemsindex.getAll();  
                player.openInventory(new PagenationInventory(list).getInventory());
            })
            .register();

    }
    
}
