package me.tom.stickfight;

import me.tom.stickfight.Main;
import me.tom.stickfight.commands.AcceptChallenge;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Playing {
    AcceptChallenge acceptChallenge;
    static Player attack;
    static Player def;
    public Playing(AcceptChallenge aC, Player attacker, Player defender){
        acceptChallenge = aC;
        attack = attacker;
        def = defender;
    }
    static int punkteAttacker = 0;
    static int punkteDefender = 0;
    public static void addToAttack(){
        punkteAttacker++;
        attack.sendMessage(Main.prefix + "§aDu hast jetzt " + punkteAttacker + " Punkte!");
        def.sendMessage(Main.prefix +"§c"+ attack.getName() + " hat nun "+ punkteAttacker + " Punkte!");
    }
    public static void addToDefender(){
        punkteDefender++;
        def.sendMessage(Main.prefix + "§aDu hast jetzt " + punkteDefender + " Punkte!");
        attack.sendMessage(Main.prefix +"§c"+ attack.getName() + " hat nun "+ punkteDefender + " Punkte!");
    }
    public static int getPunkteAttacker(){
        return punkteAttacker;
    }
    public static int getPunkteDefender(){
        return punkteDefender;
    }
    public static void teleportToStart(){
        Location challenger = new Location(attack.getWorld(), -169, 151, 42);
        Location defender = new Location(def.getWorld(), -153, 151, 42);
        attack.teleport(challenger);
        def.teleport(defender);
    }
    @Deprecated
    public static void sendActionbar(final Player player, final String message){
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }
    public static int maxPoints(){
        if(punkteAttacker > punkteDefender){
            return punkteAttacker;
        } else {
            return punkteDefender;
        }
    }
}
