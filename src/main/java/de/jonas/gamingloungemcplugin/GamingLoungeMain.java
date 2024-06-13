package de.jonas.gamingloungemcplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.jonas.gamingloungemcplugin.Listener.QuestsListener;
import de.jonas.gamingloungemcplugin.commands.AdminDebug;
import de.jonas.gamingloungemcplugin.commands.OpenMenu;
import de.jonas.gamingloungemcplugin.commands.OpenQuests;
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
        new AdminDebug();
        new OpenSmith();
        new OpenMenu();
        new OpenQuests();
    }

    @Override
    public void onEnable() {

        CommandAPI.onEnable();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new QuestsListener(), this);

    }

    public void onDiable() {

        CommandAPI.onDisable();

    }
    
}
