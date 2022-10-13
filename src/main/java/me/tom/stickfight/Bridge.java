package me.tom.stickfight;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Bridge {
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
    private void checkFreeLines(){
        FreeLines.clear();
        Location iterateStart = new Location(firstBlock.getWorld(), 150, 275, 50);
        for (int i = 1; i<=totalPlaying; i++){
            iterateStart.setZ(iterateStart.getZ()*i);
            if(iterateStart.getBlock().getType().equals(Material.AIR)){
                FreeLines.add(i);
            }
        }
    }
    public int getPlaying(){
        return currentlyPlaying;
    }
    public int getFreeLine(){
        checkFreeLines();
        return FreeLines.get(0);
    }
    public void newBridge(Player player){
        totalPlaying++;
        currentlyPlaying++;
        firstBlock= new Location(player.getWorld(), 150, 275, getFreeLine()*50);
        BridgeLocation.put(currentlyPlaying, firstBlock);
        for(int i = 1; i<= 21; i++){
            firstBlock.getBlock().setType(Material.SANDSTONE);
            firstBlock.setX(firstBlock.getBlockX() + i);
        }
    }
    public void removeBridge(Player player){
        Location remove = BridgeLocation.get(PlayerBridge.get(player));

    }
}
