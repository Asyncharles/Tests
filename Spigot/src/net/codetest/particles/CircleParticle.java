package net.codetest.particles;


import net.codetest.utils.ParticleEffect;
import net.codetest.utils.ParticleUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CircleParticle implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if(event.getBlockPlaced().getType() != null) {
            if(event.getBlockPlaced().getType() == Material.COAL_BLOCK) {
                event.setCancelled(true);
                ParticleUtils.playCircle(ParticleEffect.FLAME, event.getBlockPlaced().getLocation().add(0.5, 0, 0.5), 2f, 1);
            }
        }

    }


}
