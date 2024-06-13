package de.jonas.gamingloungemcplugin.commands;

import de.jonas.gamingloungemcplugin.gui.Menu;
import de.jonas.stuff.commandapi.CommandAPICommand;

public class OpenMenu {
    
    public OpenMenu() {

        new CommandAPICommand("gaminglounge:open_menu")
            .withAliases("menu")
            .executesPlayer((player, commandArguments) -> {
                player.openInventory(new Menu(player).getInventory());
            })
            .register();
    }

}
