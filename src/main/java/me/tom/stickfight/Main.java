package me.tom.stickfight;

import me.tom.stickfight.commands.AcceptChallenge;
import me.tom.stickfight.commands.ChallengePlayer;
import me.tom.stickfight.events.PlayerMove;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Bridge bridge;
    public static Main plugin;
    public static String prefix = "§7[§6Stick-Fight§7]§f";
    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage(prefix + "§aStickfight Plugin geladen!");
        bridge = new Bridge();
        ChallengePlayer chP = new ChallengePlayer();
        AcceptChallenge aC = new AcceptChallenge(chP);
        this.cmd("ChallengePlayer", chP);
        this.cmd("AcceptChallenge", aC);
        this.event(new PlayerMove(aC));
    }
    private void cmd(String name, CommandExecutor executor){Bukkit.getPluginCommand(name).setExecutor(executor);}
    private void event(Listener listener){Bukkit.getPluginManager().registerEvents(listener, this);}

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
