package me.tom.stickfight.events;

import me.tom.stickfight.Main;
import me.tom.stickfight.commands.AcceptChallenge;
import me.tom.stickfight.Playing;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class PlayerMove implements Listener {

    AcceptChallenge acceptChallenge;
    public PlayerMove(AcceptChallenge aC){
        acceptChallenge = aC;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent pE){
        Player p = pE.getPlayer();
        //Playing.sendActionbar(p, "SNCIES");
        if(!acceptChallenge.currentlyPlaying.contains(p)){
            return;
        }
        if(pE.getFrom().getBlockY() != pE.getTo().getBlockY()){
            if(pE.getTo().getBlockY() < 140){
                Playing play = new Playing(acceptChallenge, acceptChallenge.p, acceptChallenge.ta);
                if(acceptChallenge == p){
                    Playing.addToAttack();

                } else {
                    Playing.addToDefender();
                }
                Playing.teleportToStart();
            }
        }
        BukkitRunnable refreshActionbar = new BukkitRunnable() {
            @Override
            public void run() {
                if(Playing.maxPoints() >= 10){
                    acceptChallenge.p.sendMessage(Playing.maxPoints() +"");
                    cancel();
                }
                Playing.sendActionbar(acceptChallenge.p, "§6Attacker: §7" +Playing.getPunkteAttacker() + " | §cDefender: §7" + Playing.getPunkteDefender());
                Playing.sendActionbar(acceptChallenge.ta, "§6Attacker: §7" +Playing.getPunkteAttacker() + " §a| §cDefender: §7" + Playing.getPunkteDefender());
            }
        };
        refreshActionbar.runTaskTimer(Main.plugin, 0L, 5L);
    }
}
