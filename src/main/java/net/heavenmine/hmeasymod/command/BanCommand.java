package net.heavenmine.hmeasymod.command;

import net.heavenmine.hmeasymod.Main;
import net.heavenmine.hmeasymod.file.ConfigFile;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class BanCommand implements CommandExecutor {

    private final Main main;

    public BanCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigFile configFile = new ConfigFile(main);
//        YamlConfiguration messageFile = YamlConfiguration.loadConfiguration(new File(main.getDataFolder(), "message.yml"));

        String prefix = configFile.getPrefix();
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&aUse: /ban <player> <reason>"));
            return false;
        }

        String playerName = args[0];
        String reason = (args.length > 1) ? String.join(" ", Arrays.copyOfRange(args, 1, args.length)) : "Không có lý do.";


        BanList banList = Bukkit.getBanList(BanList.Type.NAME);
        banList.addBan(playerName, reason, null, sender.getName());


        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&aNgười chơi &e" + playerName + " &ađã bị cấm truy cập vào server."));
        return true;
    }
}
