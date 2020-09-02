package net.codetest.vehicule;


import net.codetest.Main;

import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Hypetrain implements CommandExecutor, Listener {

    /**
     * MOVE TO TRAIN CLASS FOR THE OPTIMISED VERSION
     */

    private Player p;
    private Vehicle headMinecart;
    private int index;
    private List<Triple<Integer, Vehicle, Player>> trainMembers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            headMinecart = (Vehicle) player.getWorld().spawnEntity(player.getLocation(), EntityType.MINECART);
            headMinecart.setPassenger(player);
            headMinecart.setVelocity(new Vector(0, 1.5, 0));
            headMinecart = (Vehicle) player.getVehicle();
            p = player.getPlayer();
            Main.getInstance().setPlayer(player);
            Move startTrain = new Move();
            startTrain.runTaskTimer(Main.getInstance(), 10, 10);
            player.sendMessage("§6Welcome aboard the train !");
            Location newSpawnLoc = Main.getInstance().getPlayer().getLocation();
            Vehicle e = (Vehicle) player.getWorld().spawnEntity(newSpawnLoc, EntityType.MINECART);
            e.setVelocity(new Vector(0, 0, 0));
            e = (Vehicle) player.getVehicle();
            trainMembers.add(Triple.of(0 + 1, e, null));
        }

        return false;
    }

    @EventHandler
    public void trainOwnerClick(PlayerInteractAtEntityEvent event) {

        Player player = event.getPlayer();
        Entity target = event.getRightClicked();
        Optional<Triple<Integer, Vehicle, Player>> current = trainMembers.parallelStream().filter(p -> p.getRight() == player).findAny();
        if(!current.isPresent()) {
            if(trainMembers.size() <= 9) {
                Location newSpawnLoc = Main.getInstance().getPlayer().getLocation();
                newSpawnLoc.subtract(trainMembers.size(), 0, trainMembers.size());
                Vehicle e = (Vehicle) player.getWorld().spawnEntity(newSpawnLoc, EntityType.MINECART);
                e.setPassenger(player);
                e.setVelocity(new Vector(0, 0, 0));
                e = (Vehicle) player.getVehicle();
                trainMembers.add(Triple.of(trainMembers.size() + 1, e, player.getPlayer()));
            } else {
                player.sendMessage("§cToo many people on the train");
            }
        }

    }

    @EventHandler
    public void trainOwnerLeave(VehicleExitEvent event) {
        Player player = (Player) event.getExited().getPassenger();
        if(player == p) {
            if(trainMembers.isEmpty()) {
                event.getVehicle().remove();
                return;
            }
            trainMembers.forEach((follower) -> {
                follower.getMiddle().eject();
                follower.getMiddle().remove();
                follower.getRight().sendMessage("§cThe train was destroyed");
            });
            trainMembers.clear();
        } else if(trainMembers.parallelStream().anyMatch(p -> p.getRight() == player)) {
            Optional<Triple<Integer, Vehicle, Player>> current = trainMembers.parallelStream().filter(p -> p.getRight() == player).findFirst();
            for (Triple<Integer, Vehicle, Player> trainMember : trainMembers) {
                if(trainMember.getLeft() !=0 && trainMember.getLeft() > current.get().getLeft()) {
                    trainMembers.remove(trainMember);
                    Triple.of(trainMember.getLeft() - 1, trainMember.getMiddle(), trainMember.getRight());
                }
            }

        }
    }

    class Move extends BukkitRunnable {

        public Move() {
        }

        @Override
        public void run() {
            if(headMinecart.isEmpty() || headMinecart == null) {
                cancel();
            }
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
            headMinecart.setVelocity(vector);
            if(!trainMembers.isEmpty()) {
                index = 0;
                BukkitRunnable moveFollowers = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(trainMembers.isEmpty()) {
                            cancel();
                            return;
                        }
                        trainMembers.get(index).getMiddle().setVelocity(vector);
                        index++;
                        if(index == trainMembers.size()) {
                            cancel();
                        }
                    }
                };
                moveFollowers.runTaskTimer(Main.getInstance(), 10, 10);
            }


        }
    }


}
