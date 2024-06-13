package de.jonas.gamingloungemcplugin;

import org.bukkit.plugin.java.JavaPlugin;

import de.jonas.gamingloungemcplugin.commands.OpenMenu;
import de.jonas.gamingloungemcplugin.commands.OpenSmith;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;

public class GamingLoungeMain extends JavaPlugin{

    public static GamingLoungeMain INSTANCE;
    public CustomItemsIndex customitemsindex;

    @Override
    public void onLoad() {

        INSTANCE = this;
        this.customitemsindex = new CustomItemsIndex();
        new Events();

        if (!CommandAPI.isLoaded()) CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
        new OpenSmith();
        new OpenMenu();
    }

    @Override
    public void onEnable() {

        CommandAPI.onEnable();

    }

    public void onDiable() {

        CommandAPI.onDisable();

    }
    
}
