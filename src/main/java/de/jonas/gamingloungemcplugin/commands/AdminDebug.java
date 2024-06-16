package de.jonas.gamingloungemcplugin.commands;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.jonas.gamingloungemcplugin.quests.FishingQuestOne;
import de.jonas.gamingloungemcplugin.quests.MiningQuestOne;
import de.jonas.stuff.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;

public class AdminDebug {

    public AdminDebug() {

        new CommandAPICommand("gaminglounge:debug")
            .withPermission("gaminglounge.debug")
                .withSubcommand(new CommandAPICommand("fishing_one")
                .executesPlayer((player, commandArguments) -> {
                    PersistentDataContainer container = player.getPersistentDataContainer();
                    if (container.has(FishingQuestOne.onQuest)) {
                        player.sendMessage(Component.text(container.get(FishingQuestOne.onQuest, PersistentDataType.INTEGER)));   
                    }
                }))
                .withSubcommand(new CommandAPICommand("reset")
                .executesPlayer((player, commandArguments) -> {
                    PersistentDataContainer container = player.getPersistentDataContainer();
                    container.remove(FishingQuestOne.doneOnce);
                })
                )
                .withSubcommand(new CommandAPICommand("hasMiningOne")
                .executesPlayer((player, commandArguments) -> {
                    if (player.getPersistentDataContainer().has(MiningQuestOne.onQuest)) {
                        player.sendMessage("true");
                    } else player.sendMessage("false");
                })
                )
            .register();

    }

    
}
