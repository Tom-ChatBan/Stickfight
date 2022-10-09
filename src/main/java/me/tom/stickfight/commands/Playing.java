package me.tom.stickfight.commands;

import me.tom.stickfight.Main;
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
        attack.sendMessage(Main.prefix + "§aDu hast jetzt " + punkteAttacker + " §Punkte!");
        def.sendMessage(Main.prefix +"§a"+ attack.getName() + " hat nun "+ punkteAttacker + " Punkte!");
    }
    public static void addToDefender(){
        punkteDefender++;
        def.sendMessage(Main.prefix + "§aDu hast jetzt " + punkteDefender + " §Punkte!");
        attack.sendMessage(Main.prefix +"§a"+ attack.getName() + " hat nun "+ punkteDefender + " Punkte!");
    }
    public static int getPunkteAttacker(){
        return punkteAttacker;
    }
    public static int getPunkteDefender(){
        return punkteDefender;
    }
}
