package net.codetest.gadgets;

import net.codetest.utils.ParticleEffect;
import net.codetest.utils.ParticleUtils;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;


import net.minecraft.server.v1_8_R3.WorldBorder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


import java.lang.reflect.Field;
import java.util.*;


public class PointerGadget implements Listener {

    private Set<Material> transparent = new HashSet<>();

    public PointerGadget() {
        transparent.add(Material.STONE);
        transparent.add(Material.AIR);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Action action = event.getAction();
        Player p = event.getPlayer();
        if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if(p.getItemInHand() != null) {
                if(p.getItemInHand().getType() == Material.BLAZE_ROD) {
                    Block block = p.getTargetBlock(transparent, 300);
                    Location finalLoc = block.getLocation().add(0.5,1, 0.5);
                    for(int i = 10; i > 1; i--) {
                        ParticleUtils.playCircle(ParticleEffect.REDSTONE, finalLoc, i, 0.2);
                    }
                    WorldBorder wb = new WorldBorder();
                    wb.world = ((CraftWorld) p.getWorld()).getHandle();
                    wb.setCenter(finalLoc.getX(), finalLoc.getZ());
                    wb.setSize(0.5d);
                    PacketPlayOutWorldBorder packetPlayOutWorldBorder = new PacketPlayOutWorldBorder(wb, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packetPlayOutWorldBorder);
                }
            }
        }

    }
}


