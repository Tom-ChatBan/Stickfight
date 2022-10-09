package me.tom.stickfight.events;

import me.tom.stickfight.commands.AcceptChallenge;
import me.tom.stickfight.Playing;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    AcceptChallenge acceptChallenge;
    public PlayerMove(AcceptChallenge aC){
        acceptChallenge = aC;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent pE){
        Player p = pE.getPlayer();
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
    }
}
