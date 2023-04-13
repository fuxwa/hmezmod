package net.heavenmine.hmeasymod;

import net.heavenmine.hmeasymod.command.BanCommand;
import net.heavenmine.hmeasymod.event.PlayerLoginServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        reloadConfig();
        createMessageFile();

        getServer().getPluginManager().registerEvents(new PlayerLoginServer(this), this);

        getCommand("ban").setExecutor(new BanCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void createMessageFile() {
        File itemsFile = new File(getDataFolder(), "message.yml");
        if (!itemsFile.exists()) {
            itemsFile.getParentFile().mkdirs();
            try (InputStream in = getResource("message.yml")) {
                Files.copy(in, itemsFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
