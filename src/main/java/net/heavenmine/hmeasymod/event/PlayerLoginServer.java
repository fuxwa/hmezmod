package net.heavenmine.hmeasymod.event;

import net.heavenmine.hmeasymod.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.util.List;

public class PlayerLoginServer implements Listener {
    private final Main main;

    public PlayerLoginServer(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        YamlConfiguration messageFile = YamlConfiguration.loadConfiguration(new File(main.getDataFolder(), "message.yml"));
        List<String> whitelistMessage = messageFile.getStringList("whitelist-message");
        String message = "";
        for(String text : whitelistMessage) {
            message = message + text;
        }
        if (!event.getPlayer().isWhitelisted()) {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
