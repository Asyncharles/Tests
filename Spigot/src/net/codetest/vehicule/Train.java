package net.codetest.vehicule;

import net.codetest.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


import java.util.ArrayList;
import java.util.Stack;

public class Train implements Listener {

    private ArrayList<Vehicle> followers = new ArrayList<>();
    private Stack<Vehicle> stack = new Stack<>();

    @EventHandler
    public void playerClick(PlayerInteractEvent event) {

        Player p = event.getPlayer();
        Action action = event.getAction();
        ItemStack current = event.getItem();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (current.getType() == Material.MINECART) {
                Vehicle entity = (Vehicle) p.getWorld().spawnEntity(p.getLocation().subtract(1, 0, 1), EntityType.MINECART);
                entity.setPassenger(p);
                for (int i = 1; i < 5; i++) {
                    Vehicle vehicle = (Vehicle) entity.getWorld().spawnEntity(entity.getLocation().subtract(i, 0, i), EntityType.MINECART);
                    followers.add(vehicle);
                }
                Bukkit.broadcastMessage("§6Spawned HYPED TRAIN");
                BukkitRunnable headRun = new BukkitRunnable() {
                    @Override
                    public void run() {
                        final Vector vector = p.getLocation().getDirection();
                        entity.setVelocity(vector);
                        BukkitRunnable followerRun = new BukkitRunnable() {
                            int index = 0;
                            @Override
                            public void run() {
                                if (index != followers.size()) {
                                    followers.get(index).setVelocity(vector);
                                    index++;
                                } else {
                                    cancel();
                                }
                            }
                        };
                        followerRun.runTaskTimer(Main.getInstance(), 12, 12);
                    }
                };
                headRun.runTaskTimer(Main.getInstance(), 10, 10);
            }

        /*if(entity.getType() == EntityType.MINECART) {
            Bukkit.broadcastMessage("§6Spawned HYPED TRAIN");
            Vehicle vehicle = (Vehicle) entity.getWorld().spawnEntity(entity.getLocation().subtract(1, 0, 1), EntityType.MINECART);
            followers.add(vehicle);

            BukkitRunnable headRun = new BukkitRunnable() {
                @Override
                public void run() {
                    double pitch, yaw, x, y, z;
                    pitch = ((p.getLocation().getPitch() + 90) * Math.PI) / 180;
                    yaw  = ((p.getLocation().getYaw() + 90)  * Math.PI) / 180;
                    x = Math.sin(pitch) * Math.cos(yaw);
                    y = Math.sin(pitch) * Math.sin(yaw);
                    z = Math.cos(pitch);
                    if(x < 0) x = x - 1.7;
                    if(x > 0) x = x + 1.7;
                    if(y < 0) y = y - 1.7;
                    if(y > 0) y = y + 1.7;
                    z = z + 0.4;
                    Vector vector = new Vector(x, z, y);
                    entity.setVelocity(vector);
                    BukkitRunnable followerRun = new BukkitRunnable() {
                        @Override
                        public void run() {
                            followers.forEach(c -> {
                                c.setVelocity(vector);
                            });
                        }
                    };
                    followerRun.runTaskLater(Main.getInstance(), 20);
                }
            };
            headRun.runTaskTimer(Main.getInstance(), 10, 10);
        } */

        }


    }
}
