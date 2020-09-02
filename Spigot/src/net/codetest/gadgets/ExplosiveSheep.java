package net.codetest.gadgets;

import net.codetest.Main;
import net.codetest.utils.ParticleEffect;
import net.codetest.utils.ParticleUtils;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ExplosiveSheep implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Sheep sheep = player.getWorld().spawn(player.getLocation().add(0, 2, 0), Sheep.class);
            sheep.setNoDamageTicks(100000);
            sheep.setColor(DyeColor.RED);
            final boolean[] colorSet = {true};
            final int[] explosionDelay = {0};
            ParticleUtils.playHelix(sheep.getLocation(), 18, ParticleEffect.FLAME);
            BukkitRunnable run = new BukkitRunnable() {
                @Override
                public void run() {
                    if(colorSet[0]) {
                        sheep.setColor(DyeColor.RED);
                        colorSet[0] = false;
                    } else {
                        sheep.setColor(DyeColor.WHITE);
                        colorSet[0] = true;
                        player.playSound(player.getLocation(), Sound.FUSE, 1, 1);
                    }

                    if(explosionDelay[0] != 20) {
                        explosionDelay[0]++;

                    } else {
                        cancel();
                        player.playSound(player.getLocation(), Sound.EXPLODE, 4, 2);
                        sheep.remove();
                        ParticleUtils.display(ParticleEffect.EXPLOSION_HUGE, sheep.getLocation(), 10);
                        if(player.getLocation().distance(sheep.getLocation()) < 5) {
                            double x = ( player.getLocation().getX() - sheep.getLocation().getX() ) / 3;
                            double z = ( player.getLocation().getZ() - sheep.getLocation().getZ() ) / 3;

                            player.setVelocity(new Vector(x, 0.68, z));
                        }
                    }
                }
            };
            run.runTaskTimer(Main.getInstance(), 8, 5);
        }

        return false;
    }
}
