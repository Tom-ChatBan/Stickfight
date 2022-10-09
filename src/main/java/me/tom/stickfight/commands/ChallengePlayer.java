package me.tom.stickfight.commands;

import me.tom.stickfight.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ChallengePlayer implements CommandExecutor {
    HashMap<Player, Player> stickAssociation = new HashMap<Player, Player>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cDu musst ein Spieler sein, um diesen Befehl zu nutzen!");
            return false;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("ChallengePlayer")){
            if(args.length != 1){
                p.sendMessage(Main.prefix + "§cUngültige Argumente! §7Syntax;/ChallengePlayer [PlayerName]");
                return false;
            }
            Player t = p.getServer().getPlayerExact(args[0]); //könnte auch try/catch nutzen
            if(t == null){
                p.sendMessage(Main.prefix + "§cDieser Spieler ist nicht online!");
                return false;
            }
            if(stickAssociation.containsKey(p)){
                p.sendMessage(Main.prefix + "§cDu hast bereits eine Herausforderung an §7" + stickAssociation.get(p).getName() +" §c gesendet!");
            }
            stickAssociation.put(p, t);
            p.sendMessage(Main.prefix + "§aDu hast §7" + t.getName() + " §a zum Stickfight herausgefordert!");
            t.sendMessage(Main.prefix + "§aDu wurdest von §7" + p.getName() + "§a zu einem Stickfight herausgefordert!");
        }
        return false;
    }
    public boolean containsPlayer(Player pl, Player ta){
        if(stickAssociation.containsKey(pl) && stickAssociation.get(pl) == ta){
            return true;
        } else {
            return false;
        }
    }
}
