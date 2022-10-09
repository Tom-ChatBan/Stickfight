package me.tom.stickfight.commands;

import me.tom.stickfight.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AcceptChallenge implements CommandExecutor {
    ChallengePlayer challengePlayer;
    public ArrayList<Player> currentlyPlaying = new ArrayList<Player>();
    public AcceptChallenge(ChallengePlayer cP){
        challengePlayer = cP;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cDu musst ein Spieler sein, um diesen Befehl nutzen zu können!");
            return false;
        }
        Player ta = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("AcceptChallenge")){
            if (args.length != 1) {
                ta.sendMessage(Main.prefix + "§cUngültige Argumente! §Syntax:/acceptChallenge [PlayerName]");
                return false;
            }
            Player p = ta.getServer().getPlayerExact(args[0]);
            if(p == null){
                ta.sendMessage(Main.prefix + "§cDieser Spieler ist nicht online");
                return false;
            }
           if(challengePlayer.containsPlayer(p, ta)){
               Location challenger = new Location(p.getWorld(), -169, 151, 42);
               Location defender = new Location(p.getWorld(), -153, 151, 42);
                p.teleport(challenger);
                ta.teleport(defender);
               ItemStack stick = new ItemStack(Material.STICK, 1);
               ItemMeta iM = stick.getItemMeta();
               iM.addEnchant(Enchantment.KNOCKBACK, 1, true);
               stick.setItemMeta(iM);
                //p.getInventory().addItem(stick);
                //ta.getInventory().addItem(stick);
               p.sendMessage("stick");
                p.getInventory().setItemInMainHand(stick);
                ta.getInventory().setItemInMainHand(stick);

                currentlyPlaying.add(p);
                currentlyPlaying.add(ta);
           } else {
               ta.sendMessage(Main.prefix + "§cDieser Spieler hat dich nicht zu einem Sickfight herausgefordert! Fordere ihn mit:/ChallengePlayer [PlayerName] heraus!");
                return false;
           }
        }
        return false;
    }
}
