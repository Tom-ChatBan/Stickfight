package me.tom.stickfight;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Bridge {
    private World playerWorld;
    private int currentlyPlaying;
    private int totalPlaying;
    private Location firstBlock;
    private ArrayList<Integer> FreeLines;
    private HashMap<Integer, Location> BridgeLocation;
    private HashMap<Player, Integer> PlayerBridge;

    Bridge(){
        totalPlaying = 0;
        currentlyPlaying = 0;
        FreeLines = new ArrayList<>();
        BridgeLocation = new HashMap<>();
        PlayerBridge = new HashMap<>();
    }
    private boolean checkFreeLines(){
        boolean oneFree = false;
        FreeLines.clear();
        Location iterateStart = new Location(playerWorld, 150, 275, 50);
        for (int i = 1; i<=totalPlaying; i++){
            iterateStart.setZ(iterateStart.getZ()*i);
            if(iterateStart.getBlock().getType().equals(Material.AIR)){
                FreeLines.add(i);
                oneFree = true;
            }
        }
        return oneFree;
    }
    public int getPlaying(){
        return currentlyPlaying;
    }
    public int getFreeLine(){
        if(checkFreeLines()){
            return FreeLines.get(0);
        } else {
            return currentlyPlaying +1;
        }
    }
    public int newBridge(Player player){
        playerWorld = player.getWorld();
        totalPlaying++;
        currentlyPlaying++;
        firstBlock= new Location(player.getWorld(), 150, 275, getFreeLine()*50);
        player.sendMessage("DONE");
        BridgeLocation.put(currentlyPlaying, firstBlock);
        int xPos = firstBlock.getBlockX();
        for(int i = xPos; i<= xPos+20; i++){
            firstBlock.getBlock().setType(Material.SANDSTONE);
            player.sendMessage(firstBlock.getBlockX() +"");
            firstBlock.setX(i);
        }
        return firstBlock.getBlockZ();
    }
    public void removeBridge(Player player){
        Location remove = BridgeLocation.get(PlayerBridge.get(player));
    }
}
